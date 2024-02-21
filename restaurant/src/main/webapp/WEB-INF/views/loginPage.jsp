<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%--    <link rel="stylesheet" href="styles.css">--%>
    <title>Login Form</title>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 mt-5">
            <div class="card">
                <div class="card-header">
                    <h4>Login</h4>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/api/loginPage" method="post">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>
                    <br>
                    <div class="padding-sm-bot-top">
                        <span>If you don't have an account</span>
                        <a href="${pageContext.request.contextPath}/api/loadUserRegisterPage">Go to Register</a>
                    </div>
                    <div class="padding-sm-bot-top">
                        <span>Have any complaints?</span>
                        <a href="${pageContext.request.contextPath}/api/addComplaint">Lodge your Complaint</a>
                    </div>
                    <div class="padding-sm-bot-top">
                        <span>Please give your feedback here</span>
                        <a href="${pageContext.request.contextPath}/api/loadUserRegisterPage">Feedback</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
