function populate (endPoint) {
    $.ajax({
        url: "http://localhost:8080/api/user/" + endPoint,
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
        url: 'http://localhost:8080/api/user/' + endPoint, // Replace with your actual backend endpoint
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
function sendDataWithPhoto(formData, endPoint, successUrl) {
    // AJAX request to send data to the backend
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/api/user/' + endPoint,
        data: formData,
        contentType: false,
        processData: false,
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
        url: 'http://localhost:8080/api/user/' + endPoint + '/' + id,
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
})