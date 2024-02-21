<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="dynamic-content">
    <div class="dynamic-main-div">
        <h2>Add City</h2>

        <div class="padding-md-top">
            <label for="cityName">City Name</label>
            <input class="form-control mt-3" id="cityName" name="cityName" type="text" required>
        </div>
        <div class="padding-md-top">
            <label for="cityDescription">City Description</label>
            <textarea class="form-control mt-3" rows="4" id="cityDescription" name="cityDescription" required></textarea>
        </div>
    </div>
    <div class="save-btn-div position-relative">
        <button class="btn btn-primary position-absolute bottom-0 end-0" id="submitCity" type="button">Save</button>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#submitCity').click(function() {

            // If the form is not valid, return without making the AJAX request
            if (!validateProductForm()) {
                toastr.error("Please fill in all fields");
                return;
            }

            const bodyObj = {
                cityName: $('#cityName').val(),
                cityDescription: $('#cityDescription').val()
            };
            // Call the sendData function with the city object
            sendData(bodyObj, 'submitCity', 'manageCity');
        });
    });
</script>
