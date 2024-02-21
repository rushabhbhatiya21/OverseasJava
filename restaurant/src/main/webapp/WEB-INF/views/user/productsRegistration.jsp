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
            <select class="form-select mt-3" id="subCategoryName" name="subCategoryName" required>
                <option value="" selected disabled>Select Sub Category</option>
                <c:forEach var="complaint" items="${subCategories}">
                    <option class="${complaint.category.categoryName.replaceAll(' ', '')} categoryNames" value="${complaint.subCategoryName}">${complaint.subCategoryName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="padding-md-top">
            <label for="productName">Product Name</label>
            <input class="form-control mt-3" id="productName" name="productName" type="text" required>
        </div>
        <div class="padding-md-top">
            <label for="productDescription">Product Description</label>
            <textarea class="form-control mt-3" rows="4" id="productDescription" name="productDescription" required></textarea>
        </div>
        <div class="padding-md-top">
            <label for="productPrice">Product Price</label>
            <input class="form-control mt-3" id="productPrice" name="productPrice" type="number" required>
        </div>
        <div class="padding-md-top">
            <label for="productPrice">Product Photo</label>
            <input class="form-control mt-3" id="productPhoto" name="productPrice" type="file">
        </div>
    </div>
    <div class="save-btn-div position-relative">
        <button class="btn btn-primary position-absolute bottom-0 end-0" id="submitProduct" type="button">Save</button>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#categoryName').change(function () {
            $('.categoryNames').hide();
            $('#subCategoryName').val("");
            $('.' + $(this).val()).show();
        })
        $('#submitProduct').click(function() {
            let formIsValid = true;

            // Check validity of input fields
            $('#categoryName, #subCategoryName, #productName, #productDescription, #productPrice').each(function () {
                if (!this.checkValidity()) {
                    formIsValid = false;
                    return false;  // Exit the loop if a field is invalid
                }
            });

            // If the form is not valid, return without making the AJAX request
            if (!formIsValid) {
                toastr.error("All fields are required");
                return;
            }

            const fileInput = $('#productPhoto')[0];

            // Create FormData object
            const formData = new FormData();
            formData.append('categoryName', $('#categoryName').val());
            formData.append('subCategoryName', $('#subCategoryName').val());
            formData.append('productName', $('#productName').val());
            formData.append('productDescription', $('#productDescription').val());
            formData.append('productPrice', $('#productPrice').val());
            formData.append('productPhoto', fileInput.files[0]);
            // Call the sendData function with the city object
            sendDataWithPhoto(formData, 'submitProduct', 'manageProducts');
        });
    });
</script>
