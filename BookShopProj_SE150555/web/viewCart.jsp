 <%@page import="assignment.user.UserDTO"%>
<%@page import="assignment.shopping.BookDTO"%>
<%@page import="assignment.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null || !"US".equals(user.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
        %>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
        %>
        <h1>Your CART is empty! Fill it with some book</h1>
        <%
        } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>Book Order Number</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Modify</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for (BookDTO book : cart.getCart().values()) {
                        total += book.getOrderQuantity() * book.getProductPrice();
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <input type="text" name="productID" value="<%= book.getProductID()%>" readonly=""/>
                    </td>
                    <td><%= book.getProductName()%></td>
                    <td>
                        <input type="number" name="orderQuantity" value="<%= book.getOrderQuantity()%>"/>
                    </td>
                    <td><%= book.getProductPrice()%></td>
                    <td><%= book.getOrderQuantity() * book.getProductPrice()%></td>
                    <td>
                        <input type="submit" name="action" value="Modify"/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Remove"/> 
                    </td>
                </tr>
            </form>   
            <%
                }
            %>
        </tbody>
    </table>
    <h1> Total: <%= total%> </h1>

    <form action="MainController">
        <input type="submit" name="action" value="Check Out"/> 
    </form>
    <%
        String message = (String) request.getAttribute("SHOPPING_MESSAGE");
        if (message == null) {
            message = "";
        }
    %>
    <%= message%></br>
    <%
        }
    %>
    <a href="user.jsp">Add more</a>
</body>
</html>
