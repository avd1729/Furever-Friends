<%--
  Created by IntelliJ IDEA.
  User: Aravind
  Date: 5/13/2024
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.webapps.SessionBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
</head>
<body>
<%
    SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
    String sessionId = request.getParameter("sessionId");
    String username = sessionBean.getUsername(sessionId);
    if (username != null) {
%>
<!-- Dashboard content -->
<% } else {
    // Session is invalid, redirect to login page
    response.sendRedirect("login.jsp");
}
%>
<p><a href="logout.jsp?sessionId=<%= sessionId %>">Logout</a></p>
</body>
</html>


