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
            <button class="btn btn-primary" type="button" onclick="populate('addProduct')">Add Product</button>
        </div>
    </div>

    <div id="displayTableContainer" class="table-responsive">
        <div class="myTable">
            <table class="table table-bordered table-striped" id="wholeTable">
                <thead class="thead-dark">
                <tr align="center">
                    <th>Product Id</th>
<%--                    <th>Product Name</th>--%>
                    <th>Category</th>
<%--                    <th>Sub Category Name</th>--%>
                    <th>Product Name</th>
                    <th>Product Description</th>
                    <th>Product Price</th>
                    <th>Product Photo</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="mainTable">
                <c:choose>
                    <c:when test="${content.size() > 0}">
                        <c:forEach var="complaint" items="${content}" varStatus="ind">
                            <tr align="center">
                                <td>${(pageNo - 1) * size + ind.count}</td>
<%--                                <td>${i.productName}</td>--%>
<%--                                <td>${i.category.categoryName}</td>--%>
                                <td>${complaint.subCategory.toString()}</td>
                                <td>${complaint.productName}</td>
                                <td>${complaint.productDescription}</td>
                                <td>${complaint.productPrice}</td>
                                <td><img src="data:image/png;base64,${complaint.photoString}" alt="product photo"></td>
                                <td>
                                    <a href="editProduct/${complaint.productId}" class="btn btn-outline-info btn-sm">Edit</a>
                                    <a onclick="deleteWithId('deleteProduct', ${complaint.productId}, 'manageProducts')" class="btn btn-outline-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr align="center">
                            <td colspan="8">No Products available</td>
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
                        <a onclick="populateWithPage('manageProducts', '${currentPage - 1}')" class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="0" end="${totalPages-1}" var="page">
                        <li class="page-item ${page + 1 == currentPage ? 'active' : ''}">
                            <a onclick="populateWithPage('manageProducts', '${page+1}')" class="page-link">${page+1}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a onclick="populateWithPage('manageProducts', '${currentPage + 1}')" class="page-link" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </div>
        </c:if>
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