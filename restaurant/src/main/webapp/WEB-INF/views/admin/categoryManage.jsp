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
            <button class="btn btn-primary" type="button" onclick="populate('addCategory')">Add Category</button>
        </div>
    </div>

    <div id="displayTableContainer" class="table-responsive">
        <div class="myTable">
            <table class="table table-bordered table-striped" id="wholeTable">
                <thead class="thead-dark">
                <tr align="center">
                    <th>Category Id</th>
                    <th>Category Name</th>
                    <th>Category Description</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="mainTable">
                <c:choose>
                    <c:when test="${content.size() > 0}">
                        <c:forEach var="complaint" items="${content}" varStatus="ind">
                            <tr align="center">
                                <td>${(pageNo - 1) * size + ind.count}</td>
                                <td>${complaint.categoryName}</td>
                                <td>${complaint.categoryDescription}</td>
                                <td>
                                    <a href="#" class="btn btn-outline-info btn-sm" onclick="openEditModal(${complaint.categoryId}, ${complaint.categoryName}, ${complaint.categoryDescription})">Edit</a>
                                    <a onclick="deleteWithId('deleteCategory', ${complaint.categoryId}, 'manageCategory')" class="btn btn-outline-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr align="center">
                            <td colspan="4">No Categories available</td>
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
                        <a onclick="populateWithPage('manageCategory', '${currentPage - 1}')" class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="0" end="${totalPages-1}" var="page">
                        <li class="page-item ${page + 1 == currentPage ? 'active' : ''}">
                            <a onclick="populateWithPage('manageCategory', '${page+1}')" class="page-link">${page+1}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a onclick="populateWithPage('manageCategory', '${currentPage + 1}')" class="page-link" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </div>
        </c:if>
    </div>

    <!-- Modal for Editing Category -->
    <div class="modal" id="editCategoryModal" tabindex="-1" role="dialog" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editCategoryModalLabel">Edit Category</h5>
                    <button type="button" onclick="closeEditModal()" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- Form for editing category details -->
                    <form id="editCategoryForm">
                        <div class="form-group">
                            <label for="editId">Category Id:</label>
                            <input class="form-control" id="editId" name="categoryId" disabled readonly/>
                        </div>
                        <div class="form-group">
                            <label for="editName">Category Name:</label>
                            <input type="text" class="form-control" id="editName" name="categoryName" required />
                        </div>
                        <div class="form-group">
                            <label for="editDescription">Category Description:</label>
                            <textarea class="form-control" id="editDescription" name="categoryDescription" rows="3" required></textarea>
                        </div>
                        <!-- Add other fields as needed -->
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeEditModal()" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="updateCategory">Update</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
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
</script>
