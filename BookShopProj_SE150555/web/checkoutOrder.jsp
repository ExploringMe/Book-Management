<%@page import="assignment.shopping.BookDTO"%>
<%@page import="assignment.shopping.Cart"%>
<%@page import="assignment.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
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
            Cart cart = (Cart) request.getAttribute("ORDER_CART");
            if (cart != null) {

        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Detail ID</th>
                    <th>User ID</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Product ID</th>
                    <th>Order Date</th>
                    <th>Price</th>
                    <th>Order Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%                    
                    double total = 0;
                    for (BookDTO book : cart.getCart().values()) {
                        total += book.getOrderQuantity() * book.getProductPrice();
                %>
            <form action="MainController">
                <tr>
                    <td>
                        <input type="text" name="orderID" value="<%= book.getOrderID()%>" />
                    </td>
                    <td>
                        <input type="text" name="detailID" value="<%= book.getDetailID()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="text" name="userID" value="<%= user.getUserID()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="text" name="address" value="<%= user.getAddress()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="text" name="phone" value="<%= user.getPhone()%>" readonly=""/>
                    </td>
                    <td><%= book.getProductID()%> </td>
                    <td>
                        <input type="text" name="orderDate" value="<%= book.getOrderDate()%>" readonly=""/>
                    </td>
                    <td><%= book.getProductPrice()%></td>
                    <td><%= book.getOrderQuantity()%></td>
                </tr>
            </form>   
            <%
                }
            %>
        </tbody>
    </table>
            <h1> Total: <input type="text" name="total" value="<%= total%>" readonly=""/> </h1>
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
