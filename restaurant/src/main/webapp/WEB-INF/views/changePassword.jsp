<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/styles.css" rel="stylesheet">
    <script>
        $(document).ready(function () {
            $('#changePasswordForm').submit(function (event) {
                event.preventDefault();

                // Get form values
                const oldPassword = $('#oldPassword').val();
                const newPassword = $('#newPassword').val();

                // Make AJAX request
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath}/auth/changePassword',
                    data: {
                        oldPassword: oldPassword,
                        newPassword: newPassword
                    },
                    success: function (response) {
                        // Display success message to the user
                        $('#resultMessage').text(response).removeClass('text-danger').addClass('text-success');
                        // Clear form fields
                        $('#id, #oldPassword, #newPassword').val('');
                    },
                    error: function (xhr, status, error) {
                        // Display error message to the user
                        $('#resultMessage').text('Failed to change password: ' + xhr.responseText).removeClass('text-success').addClass('text-danger');
                    }
                });
            });

            // Function to go back to the original URL
            $('#goBackButton').click(function () {
                // Use window.history to navigate back
                window.history.back();
            });
        });
    </script>

</head>
<body>

<div class="container mt-5">
    <h2>Change Password</h2>
    <form id="changePasswordForm">
        <div class="mb-3">
            <label for="oldPassword" class="form-label">Old Password:</label>
            <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
        </div>
        <div class="mb-3">
            <label for="newPassword" class="form-label">New Password:</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" required>
        </div>
        <button type="submit" class="btn btn-primary">Change Password</button>

        <!-- Button to go back to the original URL -->
        <button type="button" id="goBackButton" class="btn btn-secondary">Go Back</button>
    </form>

    <!-- Display result message -->
    <div id="resultMessage" class="mt-3"></div>
</div>

</body>
</html>
