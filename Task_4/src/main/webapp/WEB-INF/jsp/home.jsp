<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>--%>
<%--    <script>--%>
<%--        $(document).ready(function () {--%>
<%--            // Function to fetch user data via AJAX--%>
<%--            function getUsers() {--%>
<%--                $.ajax({--%>
<%--                    url: '/getUsers',--%>
<%--                    method: 'GET',--%>
<%--                    success: function (data) {--%>
<%--                        // console.log(data[4].name);--%>
<%--                        $('#userTable tbody').empty();--%>
<%--                        $.each(data, function (index, user) {--%>
<%--                            // console.log(user.name);--%>
<%--                            const row = '<tr>' +--%>
<%--                                '<td>' + user.id + '</td>' +--%>
<%--                                '<td>' + user.name + '</td>' +--%>
<%--                                '<td>' + user.department + '</td>' +--%>
<%--                                '<td>' + user.created_at + '</td>' +--%>
<%--                                '</tr>';--%>
<%--                            console.log(row);--%>
<%--                            $('#userTable').append(row);--%>
<%--                        });--%>
<%--                    },--%>
<%--                    error: function (xhr, status, error) {--%>
<%--                        console.error('Error fetching users:', error);--%>
<%--                    }--%>
<%--                });--%>
<%--            }--%>

<%--            // Call getUsers function when the page loads--%>
<%--            getUsers();--%>
<%--        });--%>
<%--    </script>--%>
</head>
<body>
<div class="container">
    <h1 class="mt-5">User List</h1>
    <table class="table table-striped mt-3 table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Department</th>
            <th>Created At</th>
        </tr>
        </thead>
        <tbody id="userTable">
        <!-- User data will be dynamically added here -->
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.department}</td>
                <td>${user.created_at}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="container">
        <div class="mt-3">
            <a href="form" class="btn btn-primary">Add New User</a>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Back To Index</a>
        </div>
    </div>

</div>
</body>
</html>
