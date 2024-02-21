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
            <label for="offerName">Offer Name</label>
            <input class="form-control mt-3" id="offerName" name="offerName" type="text" required>
        </div>
        <div class="padding-md-top">
            <label for="offerDescription">Offer Description</label>
            <textarea class="form-control mt-3" rows="4" id="offerDescription" name="offerDescription" required></textarea>
        </div>
        <div class="padding-md-top">
            <label for="offerDiscount">Discount (%)</label>
            <input class="form-control mt-3" id="offerDiscount" name="offerDiscount" type="number" required>
        </div>
       <div class="padding-md-top">
            <label for="startTime">Start Time</label>
            <input class="form-control mt-3" id="startTime" name="startTime" type="datetime-local" required>
        </div>
       <div class="padding-md-top">
            <label for="endTime">End Time</label>
            <input class="form-control mt-3" id="endTime" name="endTime" type="datetime-local" required>
        </div>
    </div>
    <div class="save-btn-div position-relative">
        <button class="btn btn-primary position-absolute bottom-0 end-0" id="submitOffer" type="button">Save</button>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#categoryName').change(function () {
            $('.categoryNames').hide();
            $('#subCategoryName').val("");
            $('.' + $(this).val()).show();
        })
        $('#submitOffer').click(function() {
            let formIsValid = true;

            // Check validity of input fields
            $('#categoryName, #subCategoryName, #offerName, #offerDescription, #offerDiscount, #startTime, #endTime').each(function () {
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

            const jsonData = {
                categoryName: $('#categoryName').val(),
                subCategoryName: $('#subCategoryName').val(),
                offerName: $('#offerName').val(),
                offerDescription: $('#offerDescription').val(),
                offerDiscount: $('#offerDiscount').val(),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val()
            };
            // Call the sendData function with the city object
            sendData(jsonData, 'submitOffer', 'manageOffers');
        });
    });
</script>
