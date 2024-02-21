<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultant Dashboard</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/styles.css" rel="stylesheet">
    <script>
        $(document).ready(function () {
            // Function to handle status dropdown change
            $('tbody#appointmentTable').on('change', 'select.status-dropdown', function () {
                const appointmentId = $(this).closest('tr').find('td:eq(0)').text();
                const newStatus = $(this).val();

                // Call the function to update status
                updateStatus(appointmentId, newStatus);
            });
        });

        // Function to update the status using AJAX
        function updateStatus(appointmentId, newStatus) {
            // Implement your AJAX request logic here
            $.ajax({
                type: 'POST',
                url: "${pageContext.request.contextPath}/consultant/updateAppointment",
                data: {
                    appointmentId: appointmentId,
                    status: newStatus
                },
                success: function (data) {
                    // Handle success
                    console.log("Status updated successfully");
                },
                error: function (error) {
                    // Handle error
                    console.error("Error updating status", error);
                }
            });
        }
    </script>
</head>
<body>

<!-- Appointments Table -->
<div class="container mt-5">
    <h2>My Appointments</h2>
    <table class="table table-striped mt-3 table-hover" id="appointmentTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Consultant ID</th>
            <th>Student Name</th>
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
                    <td>
                        <label>
                            <select class="form-select status-dropdown" onchange="updateStatus(${appointment.id}, this.value)">
                                <!-- Option for Pending status -->
                                <option value="PENDING" ${appointment.status.name() == 'PENDING' ? 'selected' : ''}>Pending</option>
                                <!-- Option for Accepted status -->
                                <option value="APPROVED" ${appointment.status.name() == 'APPROVED' ? 'selected' : ''}>Accepted</option>
                            </select>
                        </label>
                    </td>
                    <td>${appointment.consultantId.id}</td>
                    <td>${appointment.studentId.username}</td>
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

    <div class="container bottom-buttons">
        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/consultant/loadConsultantPage" class="btn btn-primary">Back To Index</a>
        </div>
    </div>

</div>

<!-- Logout Button -->
<div class="position-absolute top-0 end-0 m-3">
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger logout-button">Logout</a>
</div>


</body>
</html>
