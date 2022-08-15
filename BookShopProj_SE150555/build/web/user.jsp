<%@page import="assignment.user.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="assignment.shopping.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null || !"US".equals(user.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
            String bookSearch = (String) request.getParameter("bookSearch");
            if (bookSearch == null) {
                bookSearch = "";
            }
        %>
        <h1>Hello <%= user.getFullName()%></h1>
        <h1> Welcome To きせて Book Store!</h1>

        <a href="MainController?action=Logout">Logout</a>

        <form action="MainController">
            Shopping<input type="text" name="bookSearch" value="<%= bookSearch%>"/>
            <input type="submit" name="action" value="Book Search"/>
        </form>

        <form action="MainController">
            <input type="submit" name="action" value="View Cart"/>
        </form>

        <%
            List<BookDTO> list = (List<BookDTO>) request.getAttribute("LIST_BOOK");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
    <%
        String message = (String) request.getAttribute("SHOPPING_MESSAGE");
        if (message == null) {
            message = "";
        }
    %>
    <%= message%>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Book ID</th>
                    <th>image</th>
                    <th>Book Name</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Left Over</th>
                    <th>Book Order Number</th>
                    <th>Add To Cart</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (BookDTO book : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <input type="text" name="productID" value="<%= book.getProductID()%>" readonly=""/>
                    </td>
                    <td>
                        <img src=" <%= book.getImage() %>" style=" width: 177px"/>
                    </td>
                    <td>
                        <input type="text" name="productName" value="<%= book.getProductName()%>" readonly=""/>
                    </td>
                    <td><%= book.getCategory()%></td>
                    <td><%= book.getDescription()%></td>
                    <td>
                        <input type="text" name="productPrice" value="<%= book.getProductPrice()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="text" name="productQuantity" value="<%= book.getProductQuantity()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="number" name="orderQuantity" value="1"/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Add to Cart"/>
                        <input type="hidden" name="bookSearch" value="<%=bookSearch%>"/>
                    </td>
                </tr>
            </form>

            <%
                }
            %>

        </tbody>
    </table>
    <%
        }
    %>
    <%
        }
    %>
</body>
</html>
