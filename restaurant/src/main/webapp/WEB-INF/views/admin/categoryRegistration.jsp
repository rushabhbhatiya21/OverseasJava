<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="dynamic-content">
    <div class="dynamic-main-div">
        <h2>Add Category</h2>

        <div class="padding-md-top">
            <label for="categoryName">Category Name</label>
            <input class="form-control mt-3" id="categoryName" name="categoryName" type="text" required>
        </div>
        <div class="padding-md-top">
            <label for="categoryDescription">Category Description</label>
            <textarea class="form-control mt-3" rows="4" id="categoryDescription" name="categoryDescription" required></textarea>
        </div>
    </div>
    <div class="save-btn-div position-relative">
        <button class="btn btn-primary position-absolute bottom-0 end-0" id="submitCategory" type="button">Save</button>
    </div>
</div>

<script>
    $(document).ready(function() {
            // If the form is not valid, return without making the AJAX request
            if (!validateProductForm) {
                toastr.error("All fields are required");
                return;
            }

            // Get values from the input fields
            const categoryName = $('#categoryName').val();
            const categoryDescription = $('#categoryDescription').val();

            const bodyObj = {
                categoryName: categoryName,
                categoryDescription: categoryDescription
            };
            // Call the sendData function with the city object
            sendData(bodyObj, 'submitCategory', 'manageCategory');
    });
</script>
