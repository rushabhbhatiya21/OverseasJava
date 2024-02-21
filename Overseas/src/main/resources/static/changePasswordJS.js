$(document).ready(function () {
    $('#changePasswordForm').submit(function (event) {
        event.preventDefault();

        // Get form values
        const oldPassword = $('#oldPassword').val();
        const newPassword = $('#newPassword').val();

        // Make AJAX request
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/auth/changePassword',
            data: {
                oldPassword: oldPassword,
                newPassword: newPassword
            },
            success: function (response) {
                // Display success message to the user
                $('#resultMessage').text(response).removeClass('text-danger').addClass('text-success');
                // Clear form fields
                $('#id, #oldPassword, #newPassword').val('');
            },
            error: function (xhr, status, error) {
                // Display error message to the user
                $('#resultMessage').text('Failed to change password: ' + xhr.responseText).removeClass('text-success').addClass('text-danger');
            }
        });
    });

    // Function to go back to the original URL
    $('#goBackButton').click(function () {
    // Use window.history to navigate back
    window.history.back();
});
});
