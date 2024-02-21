<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form id="registrationForm" action="${pageContext.request.contextPath}/api/register/registerUser" method="post">
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
            <div class="form-group">
                <label for="restaurantName">Restaurant Name:</label>
                <input type="text" class="form-control" id="restaurantName" name="restaurantName" required>
                <span class="error-message" id="restaurantNameError"></span>
            </div>
            <div class="form-group">
                <label for="contactNumber">Contact Number:</label>
                <input type="text" class="form-control" id="contactNumber" name="contactNumber" required>
                <span class="error-message" id="contactNumberError"></span>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address" name="address" required>
                <span class="error-message" id="addressError"></span>
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                <select class="form-control" id="city" name="city" onchange="" required>
                    <option value="" selected disabled>Select City</option>
                    <c:forEach var="city" items="${cities}">
                        <option value="${city.cityName}">${city.cityName}</option>
                    </c:forEach>
                </select>
                <span class="error-message" id="cityError"></span>
            </div>
            <div class="form-group">
                <label for="area">Area:</label>
                <select class="form-control" id="area" name="area" required>
                    <option value="" selected disabled>Select Area</option>
                    <c:forEach var="area" items="${areas}">
                        <option class="${area.city.cityName} areas" value="${area.areaName}">${area.areaName}</option>
                    </c:forEach>
                </select>
                <span class="error-message" id="areaError"></span>
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

        $('#city').change(function () {
            $('.areas').hide();
            $('#area').val("");
            $('.' + $(this).val()).show();
        })

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
            const password = $("#password").val().trim();
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
