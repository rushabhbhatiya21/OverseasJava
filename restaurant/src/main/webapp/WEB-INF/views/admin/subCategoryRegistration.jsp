<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="dynamic-content">
    <div class="dynamic-main-div">
        <h2>Add Sub Category</h2>
        <div class="padding-md-top">
            <label for="categoryName">City Name</label>
            <select class="form-select mt-3" id="categoryName" name="categoryName" required>
                <option value="" selected disabled>Select Category</option>
                <c:forEach var="complaint" items="${categories}">
                    <option value="${complaint.categoryName}">${complaint.categoryName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="padding-md-top">
            <label for="subCategoryName">Sub Category Name</label>
            <input class="form-control mt-3" id="subCategoryName" name="subCategoryName" type="text" required>
        </div>
        <div class="padding-md-top">
            <label for="subCategoryDescription">Sub Category Description</label>
            <textarea class="form-control mt-3" rows="4" id="subCategoryDescription" name="subCategoryDescription" required></textarea>
        </div>
    </div>
    <div class="save-btn-div position-relative">
        <button class="btn btn-primary position-absolute bottom-0 end-0" id="submitSubCategory" type="button">Save</button>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#submitSubCategory').click(function() {
            // If the form is not valid, return without making the AJAX request
            if (!validateProductForm) {
                toastr.error("All fields are required");
                return;
            }

            const jsonData = {
                categoryName: $('#categoryName').val(),
                subCategoryName: $('#subCategoryName').val(),
                subCategoryDescription: $('#subCategoryDescription').val()
            };
            // Call the sendData function with the city object
            sendData(jsonData, 'submitSubCategory', 'manageSubCategory');
        });
    });
</script>
