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
    </div>
    <div id="displayTableContainer" class="table-responsive">
        <div class="myTable">
            <table class="table table-bordered table-striped" id="wholeTable">
                <thead class="thead-dark">
                <tr align="center">
                    <th>Offer Id</th>
                    <th>Restaurant Name</th>
                    <th>Category</th>
                    <th>Offer Name</th>
                    <th>Offer Description</th>
                    <th>Discount</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="mainTable">
                <c:choose>
                    <c:when test="${content.size() > 0}">
                        <c:forEach var="complaint" items="${content}" varStatus="ind">
                            <tr align="center">
                                <td>${(pageNo - 1) * size + ind.count}</td>
                                <td>${complaint.restaurant.restaurantName}</td>
                                <td>${complaint.subCategory.toString()}</td>
                                <td>${complaint.offerName}</td>
                                <td>${complaint.offerDescription}</td>
                                <td>${complaint.discount}</td>
                                <td>${complaint.startTime}</td>
                                <td>${complaint.endTime}</td>
                                <td>
<%--                                    <a href="editOffer/${offer.offerId}" class="btn btn-outline-info btn-sm">Edit</a>--%>
                                    <a onclick="deleteWithId('deleteOffer', ${complaint.offerId}, 'manageOffers')" class="btn btn-outline-danger btn-sm">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr align="center">
                            <td colspan="8">No Offers available</td>
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
                        <a onclick="populateWithPage('manageOffers', '${currentPage - 1}')" class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="0" end="${totalPages-1}" var="page">
                        <li class="page-item ${page + 1 == currentPage ? 'active' : ''}">
                            <a onclick="populateWithPage('manageOffers', '${page+1}')" class="page-link">${page+1}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a onclick="populateWithPage('manageOffers', '${currentPage + 1}')" class="page-link" aria-label="Next">
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