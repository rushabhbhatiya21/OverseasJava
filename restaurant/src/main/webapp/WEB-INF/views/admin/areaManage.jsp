<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
            <button class="btn btn-primary" type="button" onclick="populate('addArea')">Add Area</button>
        </div>
    </div>

    <div id="displayTableContainer">
        <div class="myTable">
            <table class="table table-bordered table-striped" id="wholeTable">
                <thead>
                <tr align="center">
                    <th>Area Id</th>
                    <th>Area Name</th>
                    <th>City Name</th>
                    <th>Area Description</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="mainTable">
                <c:choose>
                    <c:when test="${content.size() > 0}">
                        <c:forEach var="area" items="${content}" varStatus="ind">
                            <tr align="center">
                                <td>${(pageNo - 1) * size + ind.count} </td>
                                <td>${area.areaName}</td>
                                <td>${area.city.cityName}</td>
                                <td>${area.areaDescription}</td>
                                <td><a href="editArea/${area.areaId}" class="btn btn-outline-info">Edit</a>
                                    <a onclick="deleteWithId('deleteArea', ${area.areaId}, 'manageArea')" class="btn btn-outline-danger">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr align="center">
                            <td colspan="5">No Areas available</td>
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
                        <a onclick="populateWithPage('manageArea', '${currentPage - 1}')" class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="0" end="${totalPages-1}" var="page">
                        <li class="page-item ${page + 1 == currentPage ? 'active' : ''}">
                            <a onclick="populateWithPage('manageArea', '${page+1}')" class="page-link">${page+1}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a onclick="populateWithPage('manageArea', '${currentPage + 1}')" class="page-link" aria-label="Next">
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
