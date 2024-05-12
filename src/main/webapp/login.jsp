<%--
  Created by IntelliJ IDEA.
  User: Aravind
  Date: 5/12/2024
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
</head>
<body>
<h2>User Login</h2>
<%-- Display error message if login failed --%>
<% String error = request.getParameter("error");
    if (error != null && error.equals("invalid")) { %>
<p style="color: red;">Invalid username or password. Please try again.</p>
<% } %>
<form action="login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
