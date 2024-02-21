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

                // Define an array of field names
                const fieldNames = ['Id', 'Name', 'Password', 'Email', 'Phone Number', 'Address', 'Self Bio', 'Photo'];

                // Iterate through the field names
                $.each(fieldNames, (index, fieldName) => {
                    if (index === 2)
                        return true;
                    const fieldValue = $('tbody tr.active').find('td:eq(' + (index) + ')').text();

                    // Populate modal fields with user details
                    const sanitizedFieldName = fieldName.replace(/\s+/g, ''); // Remove spaces
                    const inputField = $('#edit' + sanitizedFieldName);

                    // Check if the input field exists before setting its value
                    if (inputField.length) {
                        inputField.val(fieldValue);
                    }
                });
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
                <th>Phone number</th>
                <th>Address</th>
                <th>Self bio</th>
                <th>Photo</th>
                <th>Role</th>
                <th>Created At</th>
            </tr>
            </thead>
            <tbody id="userTable">
            <!-- User data will be dynamically added here -->
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.address}</td>
                    <td>${user.selfBio}</td>
                    <td><img src="data:image/png;base64,${user.photoString}" alt="Photo"/></td>
                    <td>${user.role}</td>
                    <td>${user.createdAt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="container bottom-buttons">
            <div class="mt-3">
                <a href="${pageContext.request.contextPath}/api/admin/signup" class="btn btn-success">Add User</a>
                <button id="edit-button" class="btn btn-warning" data-toggle="modal" data-target="#editUserModal">Edit User</button>
                <a id="deleteUserButton" href="" class="btn btn-dark">Delete User</a>
            </div>
            <div class="mt-3">
                <a href="${pageContext.request.contextPath}/api/admin/adminIndex" class="btn btn-primary">Back To Index</a>
            </div>
        </div>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/logout" class="mt-5 btn btn-danger logout-button">Logout</a>
    </div>
</div>

<div class="modal" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <span class="close">&times;</span>

            <!-- Your edit user form goes here -->
            <form id="editUserForm" action="${pageContext.request.contextPath}/api/admin/editUser" method="post" enctype="multipart/form-data">
                <!-- Hidden input to store selectedUserId -->
                <!-- Example: Name -->
                <div class="form-group">
                    <label for="editId">Id:</label>
                    <input type="text" class="form-control" id="editId" name="editId" readonly required/>
                </div>

                <!-- Example: Name -->
                <div class="form-group">
                    <label for="editName">Name:</label>
                    <input type="text" class="form-control" id="editName" name="name" required>
                </div>

                <!-- Example: Password -->
<%--                <div class="form-group">--%>
<%--                    <label for="editPassword">Password:</label>--%>
<%--                    <input type="password" class="form-control" id="editPassword" name="password" required>--%>
<%--                </div>--%>

                <!-- Example: Email -->
                <div class="form-group">
                    <label for="editEmail">Email:</label>
                    <input type="email" class="form-control" id="editEmail" name="email" required>
                </div>

                <!-- Example: Phone Number -->
                <div class="form-group">
                    <label for="editPhoneNumber">Phone Number:</label>
                    <input type="tel" class="form-control" id="editPhoneNumber" name="phoneNumber" required>
                </div>

                <!-- Example: Address -->
                <div class="form-group">
                    <label for="editAddress">Address:</label>
                    <textarea class="form-control" id="editAddress" name="address" rows="3" required></textarea>
                </div>

                <!-- Example: Self Bio -->
                <div class="form-group">
                    <label for="editSelfBio">Self Bio:</label>
                    <textarea class="form-control" id="editSelfBio" name="selfBio" rows="3" required></textarea>
                </div>

                <!-- Example: Photo -->
                <div class="form-group">
                    <label for="editPhoto">Photo:</label>
                    <input type="file" class="form-control" id="editPhoto" name="photo" accept="image/*">
                </div>

                <!-- Add your submit button -->
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
