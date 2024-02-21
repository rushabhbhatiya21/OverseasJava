<%--
  Created by IntelliJ IDEA.
  User: baps
  Date: 08-02-2024
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div style="padding: 20px">
        <h1>Welcome ${username}</h1>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
    </div>
</body>
</html>
