<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Index</title>
    <link rel="stylesheet" href="/styles.css">
    <script src="/changePasswordJS"></script>

</head>
<body>
<h2>Student Index</h2>
<nav class="custom-navbar">
    <div class="container">
        <a class="navbar-brand" href="#">My Profile</a>
<%--        <h2>User: ${username}</h2>--%>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/student/loadStudentData">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/auth/loadChangePasswordForm">Change Password</a></li>
            <li><a href="${pageContext.request.contextPath}/logout">Logout</a> </li>
        </ul>
    </div>
</nav>
</body>
</html>
