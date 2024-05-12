<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.webapps.Cat" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cat Dashboard</title>
    <style>
        .cat-image {
            width: 200px;
            height: 200px;
            margin: 10px;
            cursor: pointer;
        }
    </style>
    <script>
        function showCatDetails(catId) {
            // Simulate displaying details by showing an alert
            alert("Cat ID: " + catId); // You can replace this with your code to fetch and display details
        }
    </script>
</head>
<body>
<h2>Main Dashboard</h2>
<div class="pet-images">
    <% List<Cat> cats = (List<Cat>) request.getAttribute("cats");
        for (Cat cat : cats) { %>
    <img src="<%= cat.getImageUrl() %>" alt="<%= cat.getPetName() %>" class="cat-image" onclick="showCatDetails('<%= cat.getPetId() %>')">
    <% } %>
</div>
</body>
</html>








