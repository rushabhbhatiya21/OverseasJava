<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role Selection</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <div class="col-md-6 offset-md-3">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Role Selection</h5>

                <form action="${pageContext.request.contextPath}/loadRegistrationForm" method="post">
                    <div class="form-group">
                        <label for="role">Role:</label>
                        <select id="role" name="role" class="form-control">
                            <option value="" disabled>--Select User--</option>
                            <option value="STUDENT" selected>Student</option>
                            <option value="CONSULTANT">Consultant</option>
                        </select>
                    </div>
                    <button id="nextButton" type="submit" class="btn btn-primary">Next</button>
                </form>

                <span>If you already have an account</span>
                <a href="${pageContext.request.contextPath}/login">Go to Login</a>

            </div>
        </div>
    </div>
</div>
</body>
</html>
