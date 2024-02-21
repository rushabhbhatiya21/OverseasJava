<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .active{
            background-color: #007bff;
        }

    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            // Function to handle user selection
            $('tbody').on('click', 'tr', function () {
                // Remove active class from all rows
                $('tbody tr').removeClass('active');
                // Add active class to the clicked row
                $(this).addClass('active');

                // Extract user details from the selected row
                const userId = $(this).find('td:eq(0)').text();

                // Update the "Delete User" button href with user details
                let deleteUserUrl = "${pageContext.request.contextPath}/api/home/deleteUserById?id=" + userId;
                $('#deleteUserButton').attr('href', deleteUserUrl);
            });
                var myDiv = $(".container");

                $(document).on("click", function (event) {
                    // Check if the click is outside of the specified div
                    if (!myDiv.is(event.target) && myDiv.has(event.target).length === 0) {
                        // Blur the div or apply your desired styling
                        $('tbody tr').removeClass('active');
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h1 class="mt-5">User List</h1>
    <table class="table table-striped mt-3 table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Role</th>
            <th>Created At</th>
        </tr>
        </thead>
        <tbody id="userTable">
        <!-- User data will be dynamically added here -->
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>${user.createdAt}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="container">
        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/api/home/form" class="btn btn-primary">Add New User</a>
            <a id="deleteUserButton" href="" class="btn btn-dark">Delete User</a>
            <a href="${pageContext.request.contextPath}/api/home/adminIndex" class="btn btn-primary">Back To Index</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Logout</a>
        </div>
    </div>

</div>
</body>
</html>
