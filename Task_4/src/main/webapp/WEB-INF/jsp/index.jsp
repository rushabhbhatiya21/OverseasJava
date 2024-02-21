<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello JSP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<nav class="custom-navbar">
    <div class="container">
        <a class="navbar-brand" href="#">User Management</a>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/getUsers">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/form">Add New User</a></li>
        </ul>
    </div>
</nav>
<div class="container mt-4">
    <h1>Hello ${username}</h1>
</div>
</body>
</html>
