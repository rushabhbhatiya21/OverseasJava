<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<div class="dynamic-content">
    <div class="topWithButtons">
        <div>
            <button class="btn btn-dark" type="button">Copy</button>
            <button class="btn btn-info" type="button">CSV</button>
            <button class="btn btn-success" type="button">Excel</button>
            <button class="btn btn-danger" type="button">PDF</button>
            <button class="btn btn-primary" type="button" id="printBtn">Print</button>
        </div>
        <div>
            <button class="btn btn-primary" type="button" onclick="populate('addCity')">Add City</button>
        </div>
    </div>

    <div id="displayTableContainer" class="table-responsive">
        <div class="myTable">
            <table class="table table-bordered table-striped" id="wholeTable">
                <thead class="thead-dark">
                <tr align="center">
                    <th>City Id</th>
                    <th>City Name</th>
                    <th>City Description</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="mainTable">
                <c:choose>
                    <c:when test="${content.size() > 0}">
                        <c:forEach var="city" items="${content}" varStatus="ind">
                            <tr align="center">
                                <td>${(pageNo - 1) * size + ind.count}</td>
                                <td>${city.cityName}</td>
                                <td>${city.cityDescription}</td>
                                <td>
                                    <a class="btn btn-outline-info btn-sm editBtn" id="editButton">Edit</a>
                                    <a onclick="confirmDelete('deleteCity', ${city.cityId}, 'manageCity')" class="btn btn-outline-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr align="center">
                            <td colspan="4">No Cities available</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>


        <c:if test="${content.size() > 0 }">
            <div class="page-footer">
                <div class="d-flex align-items-center">
                    Showing ${content.size()} of ${totalElements}
                </div>
                <ul class="pagination mb-0">
                    <c:set var="currentPage" value="${currentPage}"/>
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a onclick="populateWithPage('manageCity', '${currentPage - 1}')" class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="0" end="${totalPages-1}" var="page">
                        <li class="page-item ${page + 1 == currentPage ? 'active' : ''}">
                            <a onclick="populateWithPage('manageCity', '${page+1}')" class="page-link">${page+1}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a onclick="populateWithPage('manageCity', '${currentPage + 1}')" class="page-link" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </div>
        </c:if>
    </div>

    <!-- Modal for Editing City -->
    <div class="modal" id="editCityModal" tabindex="-1" role="dialog" aria-labelledby="editCityModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editCityModalLabel">Edit City</h5>
                    <button type="button" onclick="closeEditModal()" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- Form for editing city details -->
                    <form id="editCityForm">
                        <div class="form-group">
                            <label for="editId">City Id:</label>
                            <input class="form-control" id="editId" name="cityId" disabled readonly/>
                        </div>
                        <div class="form-group">
                            <label for="editName">City Name:</label>
                            <input type="text" class="form-control" id="editName" name="cityName" required />
                        </div>
                        <div class="form-group">
                            <label for="editDescription">City Description:</label>
                            <textarea class="form-control" id="editDescription" name="cityDescription" rows="3" required></textarea>
                        </div>
                        <!-- Add other fields as needed -->
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeEditModal()" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="updateCity">Update</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script>
    function confirmDelete(endPoint, cityId, successUrl) {
        // Show the confirmation dialog
        const result = confirm("Are you sure you want to delete?");
        if (result) {
            // If the user clicks "OK" (true), proceed with the delete action
            deleteWithId(endPoint, cityId, successUrl);
        } else {
            // If the user clicks "Cancel" (false), do nothing
        }
    }

    $(document).ready(function() {
        $('.editBtn').click(function () {
            openEditModal();

            const row = $(this).closest('tr');

            // Get values from the row and populate the modal fields
            $("#editId").val(row.find('td:eq(0)').text());
            $("#editName").val(row.find('td:eq(1)').text());
            $("#editDescription").val(row.find('td:eq(2)').text());
        });

        $('#updateCity').click(function() {
            // If the form is not valid, return without making the AJAX request
            if (!validateProductForm) {
                toastr.error("All fields are required");
                return;
            }

            const bodyObj = {
                cityName: $('#editName').val(),
                cityDescription: $('#editDescription').val()
            };
            // Call the sendData function with the city object
            updateData(bodyObj, 'updateCity', 'manageCity');
        });

        $('.btn-dark').click(function() {
            copyTableContent();
        });

        $('.btn-info').click(function() {
            downloadCSV();
        });

        $('.btn-success').click(function () {
            downloadExcel();
        })

        $('#printBtn').click(function() {
            printTable();
        });

        // Attach the downloadPDF function to the click event of the PDF button
        $('.btn-danger').click(function() {
            downloadPDF();
        });
    });
</script>
