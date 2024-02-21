<%--
  Created by IntelliJ IDEA.
  User: baps
  Date: 14-02-2024
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultant Index</title>
    <link rel="stylesheet" href="/styles.css">
    <script src="/changePasswordJS"></script>
</head>
<body>
    <h2>Consultant Index</h2>
    <nav class="custom-navbar">
        <div class="container">
            <a class="navbar-brand" href="#">Student Management</a>
            <%--        <h2>User: ${username}</h2>--%>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/consultant/loadConsultantData">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/auth/loadChangePasswordForm">Change Password</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a> </li>
            </ul>
        </div>
    </nav>
</body>
</html>
