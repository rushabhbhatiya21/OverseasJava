function populate (endPoint) {
    $.ajax({
        url: "http://localhost:8080/api/admin/" + endPoint,
        method: "GET",
        success: function (htmlContent) {
            $('.main-right-cont').html(htmlContent);
        }
    })
}

function populateWithPage (endPoint, pageNo) {
    populate(endPoint + "/" + pageNo);
}

function sendData(objDao, endPoint, successUrl) {
    // AJAX request to send data to the backend
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/admin/' + endPoint, // Replace with your actual backend endpoint
        contentType: 'application/json',  // Set Content-Type header to application/json
        data: JSON.stringify(objDao),  // Convert dataObject to JSON string
        success: function(response) {
            // Handle success response from the server
            toastr.success(response);
            populateWithPage(successUrl, 1);
        },
        error: function(error) {
            // Handle error response from the server
            console.error('Error:', error);
            toastr.error(error);
            populateWithPage(successUrl, 1);
        }
    });
}

function deleteWithId(endPoint, id, successUrl) {
    $.ajax({
        url: 'http://localhost:8080/api/admin/' + endPoint + '/' + id,
        method: 'DELETE',
        success: function (response) {
            toastr.success(response);
            populateWithPage(successUrl, 1);
        },
        error: function (error) {
            toastr.error(error);
            console.log(error);
            populateWithPage(successUrl, 1);
        }
    });
}

function openEditModal() {
    // Open the modal
    $("#editCityModal").css("display", "block");
}
function closeEditModal() {
    $('#editCityModal').css("display", "none");
}

function updateData(obj, endPoint, successUrl) {
    // Get data from the form
    // const cityId = $("#editCityId").val();
    // const cityName = $("#editCityName").val();
    // const cityDescription = $("#editCityDescription").val();

    // Make AJAX call to update city
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/admin/' + endPoint,
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (response) {
            // Handle success, e.g., close the modal, update the table, etc.
            $("#editCityModal").css("display", "none");
            // Add logic to update the table or reload the content as needed
            toastr.success("Edit successful!");
            populateWithPage(successUrl, 1);
        },
        error: function (error) {
            // Handle error
            console.error("Error updating city:", error);
            toastr.error("Edit error");
            populateWithPage(successUrl, 1);
        }
    });
}

// Function to update the status using AJAX
function updateStatus(complaintId, newStatus) {
    // Implement your AJAX request logic here
    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/api/admin/updateComplaint",
        data: {
            complaintId: complaintId,
            status: newStatus
        },
        success: function (data) {
            // Handle success
            console.log("Status updated successfully");
            toastr.success("Status updated");
            populateWithPage('manageComplaints', 1);

        },
        error: function (error) {
            // Handle error
            console.error("Error updating status", error);
            toastr.error("Error updating status");
        }
    });
}

function validateProductForm() {
    const elementsToValidate = $("input[type='text'],textarea");

    let isValid = true;

    elementsToValidate.each(function () {
        const inputValue = $(this).val().trim();

        if (inputValue === "") {
            isValid = false;
        }
    });

    return isValid;
}

$(document).ready(function () {
    // Add click event listener to each card
    $('.main-left-content-card').click(function() {
        // Remove 'active' class from all cards
        $('.main-left-content-card').removeClass('active');
        // $('#dashboard').removeClass('active');
        // Add 'active' class to the clicked card
        $(this).addClass('active');

        const id = $(this).attr('id');
        populateWithPage(id, 1);
    });

    $('#logout').click(function () {
        window.location.href = "http://localhost:8080/logout";
    })
});