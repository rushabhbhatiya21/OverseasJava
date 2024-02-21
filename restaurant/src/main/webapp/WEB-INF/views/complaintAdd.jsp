<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Complaint</title>
    <!-- Add your CSS files or styles here -->
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .dynamic-content {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .dynamic-main-div {
            text-align: center;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
        }

        select,
        input,
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ced4da;
            border-radius: 4px;
            background-color: #f8f9fa;
            font-size: 14px;
        }

        select {
            height: 38px;
        }

        .save-btn-div {
            position: relative;
        }

        #submitComplaint {
            position: absolute;
            bottom: 0;
            right: 0;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
        }
    </style>
    <!-- Add your JavaScript libraries or scripts here -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script src="/js/dynamicContent.js"></script>
</head>
<body>

<div class="dynamic-content">
    <div class="dynamic-main-div">
        <h2>Add Complaint</h2>
        <div class="padding-md-top">
            <label for="restaurantName">Restaurant Name</label>
            <select class="form-select mt-3" id="restaurantName" name="restaurantName" required>
                <option value="" selected disabled>Select Restaurant</option>
                <c:forEach var="restaurant" items="${restaurants}">
                    <option value="${restaurant.userId}">${restaurant.restaurantName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="padding-md-top">
            <label for="complaintSubject">Complaint Subject</label>
            <input class="form-control mt-3" id="complaintSubject" name="complaintSubject" type="text" required>
        </div>
        <div class="padding-md-top">
            <label for="complaintDescription">Complaint Description</label>
            <textarea class="form-control mt-3" rows="4" id="complaintDescription" name="complaintDescription" required></textarea>
        </div>
    </div>
    <div class="save-btn-div position-relative">
        <button class="btn btn-primary position-absolute bottom-0 end-0" id="submitComplaint" type="button">Save</button>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#submitComplaint').click(function() {
            // If the form is not valid, return without making the AJAX request
            if (!validateProductForm) {
                toastr.error("Please fill in all fields");
                return;
            }

            const jsonData = {
                restaurantId: $('#restaurantName').val(),
                complaintSubject: $('#complaintSubject').val(),
                complaintDescription: $('#complaintDescription').val(),
            };

            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/api/submitComplaint', // Replace with your actual backend endpoint
                contentType: 'application/json',  // Set Content-Type header to application/json
                data: JSON.stringify(jsonData),  // Convert dataObject to JSON string
                success: function(response) {
                    // Handle success response from the server
                    toastr.success(response);
                    window.location.href = 'http://localhost:8080/logout';
                },
                error: function(error) {
                    // Handle error response from the server
                    console.error('Error:', error);
                    toastr.error(error);
                    window.location.href = 'http://localhost:8080/logout';
                }
            });
        });
    });
</script>

<!-- Add your other scripts or libraries here -->

</body>
</html>
