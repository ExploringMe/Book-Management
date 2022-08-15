<%@page import="java.util.List"%>
<%@page import="assignment.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Hello Admin:<%= loginUser.getFullName()%></h1>

        <a href="MainController?action=Logout">Logout</a>

        <form action="MainController">
            Search<input type="text" name="search" value="<%= search%>"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <%
            List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Password</th>
                    <th>phone</th>
                    <th>address</th>
                    <th>Create Date</th>
                    <th>Role ID</th>
                    <th>Delete</th>
                    <th>Update</th>

                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserDTO user : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td><%= user.getUserID()%></td>
                    <td>
                        <input type="text" name="fullName" value="<%= user.getFullName()%>"/>
                    </td>
                    <td><%= user.getPassword()%></td>
                    <td>
                        <input type="text" name="phone" value="<%= user.getPhone()%>"/>
                    </td>
                    <td>
                        <input type="text" name="address" value="<%= user.getAddress()%>"/>
                    </td>
                    <td><%= user.getCreateDate()%></td>
                    <td>
                        <input type="text" name="roleID" value="<%= user.getRoleID()%>"/>
                    </td>
                    <td>
                        <a href="MainController?userID=<%= user.getUserID()%>&action=Delete&search=<%= search%>" >Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="userID" value="<%= user.getUserID()%>"/>
                        <input type="hidden" name="search" value="<%=search%>"/>
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
        String error_massage = (String) session.getAttribute("ERROR_MESSAGE");
        if (error_massage == null) {
            error_massage = "";
        }
    %>
    <h1> <%= error_massage%> </h1>
    <%
        }
    %>


</body>
</html>
