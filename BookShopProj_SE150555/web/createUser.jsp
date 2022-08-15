<%@page import="assignment.user.UserDTO"%>
<%@page import="assignment.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Create new user!</h1>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>
        <form action="MainController" method="POST">
            User ID<input type="text" name="userID" required=""/>
            <%= userError.getUserIDEror()%></br>
            Full Name<input type="text" name="fullName" required=""/>
            <%= userError.getFullNameError()%></br>
            Password<input type="password" name="password" required=""/></br>
            Confirm<input type="password" name="confirm" required=""/>
            <%= userError.getConfirmError()%></br>
            Address<input type="text" name="address" required=""/></br>
            Phone<input type="text" name="phone" required=""/>
            <%= userError.getPhoneError()%></br>

            <input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/>
            <%= userError.getMessageError()%></br>
        </form>
        <a href="login.html">Go back to login page</a>
    </body>
</html>
