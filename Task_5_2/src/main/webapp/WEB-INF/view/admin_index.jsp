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
        <h2>Admin: ${username}</h2>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/api/home/getUsers">Home</a></li>
        </ul>
    </div>
</nav>
</body>
</html>
