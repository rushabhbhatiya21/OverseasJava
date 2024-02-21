<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="dynamic-content">
    <div class="dynamic-main-div">
        <h2>Add Area</h2>

        <div class="padding-md-top">
            <label for="cityName">City Name</label>
            <select class="form-select mt-3" id="cityName" name="cityName" required>
                <option value="" selected disabled>Select City</option>
                <!-- Loaded by ajax -->
            </select>
        </div>
        <div class="padding-md-top">
            <label for="areaName">Area Name</label>
            <input class="form-control mt-3" id="areaName" name="areaName" type="text" required>
        </div>
        <div class="padding-md-top">
            <label for="areaDescription">Area Description</label>
            <textarea class="form-control mt-3" rows="4" id="areaDescription" name="areaDescription" required></textarea>
        </div>
    </div>
    <div class="save-btn-div position-relative">
        <button class="btn btn-primary position-absolute bottom-0 end-0" id="submitArea" type="button">Save</button>
    </div>
</div>

<script>
    $(document).ready(function() {
        $.ajax({
            url: 'http://localhost:8080/api/admin/getAllCityNames',
            method: 'GET',
            success: function (listOfCities) {
                $.each(listOfCities, function(index, city) {
                    $('#cityName').append('<option value="' + city + '">' + city + '</option>');
                });
            }
        })
        $('#submitArea').click(function() {
            // If the form is not valid, return without making the AJAX request
            if (!validateProductForm) {
                toastr.error("All fields are required");
                return;
            }
            // Get values from the input fields
            const bodyObj = {
                cityName: $('#cityName').val(),
                areaName: $('#areaName').val(),
                areaDescription: $('#areaDescription').val()
            };
            // Call the sendData function with the city object
            sendData(bodyObj, 'submitArea', 'manageArea');
        });
    });
</script>
