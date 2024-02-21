<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/styles.css" rel="stylesheet">

    <script>
        $(document).ready(function () {
            // let userId; // Declare userId outside the click event handler

            // Function to handle user selection
            $('tbody').on('click', 'tr', function () {
                // Remove active class from all rows
                $('tbody tr').removeClass('active');
                // Add active class to the clicked row
                $(this).addClass('active');

                // Extract user details from the selected row
                const userId = $(this).find('td:eq(0)').text();

                // Update the "Delete User" button href with user details
                let deleteUserUrl = "${pageContext.request.contextPath}/api/admin/deleteUserById?id=" + userId;
                $('#deleteUserButton').attr('href', deleteUserUrl);
            });

            // Get the <span> element that closes the modal
            const span = document.getElementsByClassName("close")[0];
            // Get the modal
            const modal = document.getElementById("myModal");

            // When the user clicks the button, open the modal
            $('#edit-button').click(function () {
                $('#editUserModal').css('display', 'block');

                // Extract consultantId from the selected row
                const consultantId = $('tbody tr.active').find('td:eq(0)').text();
                const consultantName = $('tbody tr.active').find('td:eq(1)').text();

                // Populate modal fields with consultant details
                $('#consultantId').val(consultantId);
                $('#consultantName').val(consultantName);


                // Set default values for start and end time
                const now = new Date();
                const formattedNow = now.toISOString().slice(0, 16); // Format: "YYYY-MM-DDTHH:mm"
                $('#startTime').val(formattedNow);

                // Calculate default end time (1 hour after start time)
                const oneHourLater = new Date(now.getTime() + 60 * 60 * 1000);
                const formattedOneHourLater = oneHourLater.toISOString().slice(0, 16);
                $('#endTime').val(formattedOneHourLater);
            });

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                $('#editUserModal').css('display', 'none');
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target === modal) {
                    $('#editUserModal').css('display', 'none');
                }
            }

            // Submit form with userId
            $('#editUserForm').submit(function (event) {
                // Prevent the default form submission
                event.preventDefault();

                // Manually trigger the form submission
                this.submit();
            });

            const myDiv = $(".container");

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
<div class="top-container">
    <div class="container">
        <h1 class="mt-5">User List</h1>
        <table class="table table-striped mt-5 table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Password</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody id="userTable">
            <!-- User data will be dynamically added here -->
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="container bottom-buttons">
            <div class="mt-3">
                <button id="edit-button" class="btn btn-warning" data-toggle="modal" data-target="#editUserModal">Book</button>
            </div>
            <div class="mt-3">
                <a href="${pageContext.request.contextPath}/student/loadStudentPage" class="btn btn-primary">Back To Index</a>
            </div>
        </div>
    </div>
<%--    <div>--%>
<%--        <a href="${pageContext.request.contextPath}/logout" class="mt-5 btn btn-danger logout-button">Logout</a>--%>
<%--    </div>--%>
</div>

<div class="modal" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <span class="close">&times;</span>

            <!-- Your edit user form goes here -->
            <form id="editUserForm" action="${pageContext.request.contextPath}/student/bookAppointment" method="post" enctype="multipart/form-data">
                <!-- Example: Consultant ID -->
                <div class="form-group">
                    <label for="consultantId">Consultant ID:</label>
                    <input type="text" class="form-control" id="consultantId" name="consultantId" readonly required/>
                </div>
                <!-- Example: Consultant Name -->
                <div class="form-group">
                    <label for="consultantName">Consultant Name:</label>
                    <input type="text" class="form-control" id="consultantName" name="consultantName" readonly required/>
                </div>

                <!-- Example: Start Time -->
                <div class="form-group">
                    <label for="startTime">Start Time:</label>
                    <input type="datetime-local" class="form-control" id="startTime" name="startTime" required>
                </div>

                <!-- Example: End Time -->
                <div class="form-group">
                    <label for="endTime">End Time:</label>
                    <input type="datetime-local" class="form-control" id="endTime" name="endTime" required>
                </div>

                <!-- Example: Description -->
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                </div>

                <br>
                <!-- Add your submit button -->
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
        </div>
    </div>
</div>

<!-- Appointments Table -->
<div class="container mt-5">
    <h2>My Appointments</h2>
    <table class="table table-striped mt-3 table-hover" id="appointmentTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Student ID</th>
            <th>Consultant Name</th>
            <th>Description</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Creation On</th>
            <th>Modified On</th>
        </tr>
        </thead>
        <tbody>
        <!-- Check if the appointments list is not empty -->
        <c:if test="${not empty appointments}">
            <!-- Iterate through your appointments and display the data -->
            <c:forEach items="${appointments}" var="appointment">
                <tr>
                    <td>${appointment.id}</td>
                    <td>${appointment.status}</td>
                    <td>${appointment.studentId.id}</td>
                    <td>${appointment.consultantId.username}</td>
                    <td>${appointment.description}</td>
                    <td>${appointment.startTime}</td>
                    <td>${appointment.endTime}</td>
                    <td>${appointment.createdOn}</td>
                    <td>${appointment.modifiedOn}</td>
                </tr>
            </c:forEach>
        </c:if>
        <!-- Display a message if the appointments list is empty -->
        <c:if test="${empty appointments}">
            <tr>
                <td colspan="8">No appointments available</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <!-- Edit Appointment Button -->
    <div class="container bottom-buttons">
        <div class="mt-3">
            <a id="editAppointmentButton" href="" class="btn btn-warning">Edit Appointment</a>
        </div>
    </div>
</div>

<!-- Logout Button -->
<div class="position-absolute top-0 end-0 m-3">
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger logout-button">Logout</a>
</div>

</body>
</html>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>User List</title>--%>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <link href="/styles.css" rel="stylesheet">--%>

<%--    <script>--%>
<%--        $(document).ready(function () {--%>
<%--            // Function to handle user selection for users--%>
<%--            $('tbody#userTable').on('click', 'tr', function () {--%>
<%--                // Remove active class from all user rows--%>
<%--                $('tbody#userTable tr').removeClass('active');--%>
<%--                // Add active class to the clicked row--%>
<%--                $(this).addClass('active');--%>

<%--                // Extract user details from the selected row--%>
<%--                const userId = $(this).find('td:eq(0)').text();--%>

<%--                // Update the "Delete User" button href with user details--%>
<%--                let deleteUserUrl = "${pageContext.request.contextPath}/api/admin/deleteUserById?id=" + userId;--%>
<%--                $('#deleteUserButton').attr('href', deleteUserUrl);--%>
<%--            });--%>

<%--            // Function to handle user selection for appointments--%>
<%--            $('tbody#appointmentTable').on('click', 'tr', function () {--%>
<%--                // Remove active class from all appointment rows--%>
<%--                $('tbody#appointmentTable tr').removeClass('active');--%>
<%--                // Add active class to the clicked row--%>
<%--                $(this).addClass('active');--%>

<%--                // Extract appointment details from the selected row--%>
<%--                const appointmentId = $(this).find('td:eq(0)').text();--%>

<%--                // Update the "Edit Appointment" button href with appointment details--%>
<%--                let editAppointmentUrl = "${pageContext.request.contextPath}/student/editAppointment?id=" + appointmentId;--%>
<%--                $('#editAppointmentButton').attr('href', editAppointmentUrl);--%>
<%--            });--%>

<%--            // Your existing JavaScript code--%>
<%--        });--%>
<%--    </script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="top-container">--%>
<%--    <div class="container">--%>
<%--        <h1 class="mt-5">User List</h1>--%>

<%--        <!-- User Table -->--%>
<%--        <table class="table table-striped mt-5 table-hover">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>ID</th>--%>
<%--                <th>Name</th>--%>
<%--                <th>Password</th>--%>
<%--                <th>Email</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody id="userTable">--%>
<%--            <!-- User data will be dynamically added here -->--%>
<%--            <c:forEach items="${users}" var="user">--%>
<%--                <tr>--%>
<%--                    <td>${user.id}</td>--%>
<%--                    <td>${user.username}</td>--%>
<%--                    <td>${user.password}</td>--%>
<%--                    <td>${user.email}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>

<%--        <!-- Book Appointment Button -->--%>
<%--        <div class="container bottom-buttons">--%>
<%--            <div class="mt-3">--%>
<%--                <button id="edit-button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editUserModal">Book</button>--%>
<%--            </div>--%>
<%--            <div class="mt-3">--%>
<%--                <a href="${pageContext.request.contextPath}/student/loadStudentPage" class="btn btn-primary">Back To Index</a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <!-- Edit User Modal -->--%>
<%--    <div class="modal" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">--%>
<%--        <!-- Your existing modal content -->--%>
<%--        <div class="modal-dialog" role="document">--%>
<%--            <div class="modal-content">--%>
<%--                <span class="close">&times;</span>--%>

<%--                <!-- Your edit user form goes here -->--%>
<%--                <form id="editUserForm" action="${pageContext.request.contextPath}/student/bookAppointment" method="post" enctype="multipart/form-data">--%>
<%--                    <!-- Example: Consultant ID -->--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="consultantId">Consultant ID:</label>--%>
<%--                        <input type="text" class="form-control" id="consultantId" name="consultantId" readonly required/>--%>
<%--                    </div>--%>
<%--                    <!-- Example: Consultant Name -->--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="consultantName">Consultant Name:</label>--%>
<%--                        <input type="text" class="form-control" id="consultantName" name="consultantName" readonly required/>--%>
<%--                    </div>--%>

<%--                    <!-- Example: Start Time -->--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="startTime">Start Time:</label>--%>
<%--                        <input type="datetime-local" class="form-control" id="startTime" name="startTime" required>--%>
<%--                    </div>--%>

<%--                    <!-- Example: End Time -->--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="endTime">End Time:</label>--%>
<%--                        <input type="datetime-local" class="form-control" id="endTime" name="endTime" required>--%>
<%--                    </div>--%>

<%--                    <!-- Example: Description -->--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="description">Description:</label>--%>
<%--                        <textarea class="form-control" id="description" name="description" rows="3" required></textarea>--%>
<%--                    </div>--%>

<%--                    <br>--%>
<%--                    <!-- Add your submit button -->--%>
<%--                    <button type="submit" class="btn btn-primary">Book Appointment</button>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<!-- Appointments Table -->--%>
<%--<div class="container mt-5">--%>
<%--    <h2>My Appointments</h2>--%>
<%--    <table class="table table-striped mt-3 table-hover" id="appointmentTable">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>ID</th>--%>
<%--            <th>Student ID</th>--%>
<%--            <th>Consultant ID</th>--%>
<%--            <th>Description</th>--%>
<%--            <th>Start Time</th>--%>
<%--            <th>End Time</th>--%>
<%--            <th>Creation On</th>--%>
<%--            <th>Modified On</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <!-- Check if the appointments list is not empty -->--%>
<%--        <c:if test="${not empty appointments}">--%>
<%--            <!-- Iterate through your appointments and display the data -->--%>
<%--            <c:forEach items="${appointments}" var="appointment">--%>
<%--                <tr>--%>
<%--                    <td>${appointment.id}</td>--%>
<%--                    <td>${appointment.studentId}</td>--%>
<%--                    <td>${appointment.consultantId}</td>--%>
<%--                    <td>${appointment.description}</td>--%>
<%--                    <td>${appointment.startTime}</td>--%>
<%--                    <td>${appointment.endTime}</td>--%>
<%--                    <td>${appointment.createdOn}</td>--%>
<%--                    <td>${appointment.modifiedOn}</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </c:if>--%>
<%--        <!-- Display a message if the appointments list is empty -->--%>
<%--        <c:if test="${empty appointments}">--%>
<%--            <tr>--%>
<%--                <td colspan="8">No appointments available</td>--%>
<%--            </tr>--%>
<%--        </c:if>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--    <!-- Edit Appointment Button -->--%>
<%--    <div class="container bottom-buttons">--%>
<%--        <div class="mt-3">--%>
<%--            <a id="editAppointmentButton" href="" class="btn btn-warning">Edit Appointment</a>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <!-- Logout Button -->--%>
<%--    <div class="d-flex justify-content-end mt-3">--%>
<%--        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger logout-button">Logout</a>--%>
<%--    </div>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>
