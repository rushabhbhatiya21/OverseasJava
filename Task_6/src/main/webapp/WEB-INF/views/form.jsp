<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-5">Add New User</h1>
    <form method="post" action="${pageContext.request.contextPath}/api/home/addUser" class="mt-4">
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input id="name" name="name" type="text" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input id="password" name="password" type="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="userRole" class="form-label">Role:</label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="role" id="userRole" value="USER" checked>
                <label class="form-check-label" for="userRole">User</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="role" id="adminRole" value="ADMIN">
                <label class="form-check-label" for="adminRole">Admin</label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
