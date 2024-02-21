<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .registration-container {
            margin-top: 100px;
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
            padding: 20px;
            border: 1px solid #dfe3e8;
            border-radius: 5px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .registration-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .btn {
            margin-top: 10px;
        }
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="registration-container">
        <h2>User Registration</h2>
        <form id="registrationForm" action="${pageContext.request.contextPath}/api/register/registerAdmin" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
                <span class="error-message" id="emailError"></span>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <span class="error-message" id="passwordError"></span>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Register</button>
        </form>
    </div>
</div>

<script>
    $(document).ready(function() {
        $("#registrationForm").submit(function(event) {
            if (!validateForm()) {
                event.preventDefault(); // Prevent form submission if validation fails
            }
        });

        function validateForm() {
            let isValid = true;

            // Email validation
            const email = $("#email").val();
            if (!isValidEmail(email)) {
                $("#emailError").text("Enter a valid email address.");
                isValid = false;
            } else {
                $("#emailError").text("");
            }

            // Password validation (you can add more rules)
            const password = $("#password").val();
            if (password.length < 8) {
                $("#passwordError").text("Password must be at least 8 characters.");
                isValid = false;
            } else {
                $("#passwordError").text("");
            }

            // Add similar validation logic for other fields

            return isValid;
        }

        function isValidEmail(email) {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return emailRegex.test(email);
        }
    });
</script>
</body>
</html>
