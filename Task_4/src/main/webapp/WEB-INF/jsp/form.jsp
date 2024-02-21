<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1" %>
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
    <form method="post" action="${pageContext.request.contextPath}/addUser" class="mt-4">
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input id="name" name="name" type="text" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="department" class="form-label">Department:</label>
            <input id="department" name="department" type="text" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
