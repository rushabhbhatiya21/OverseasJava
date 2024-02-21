<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.25/jspdf.plugin.autotable.min.js"></script>
    <script src="/js/dynamicContent.js"></script>
    <script src="/js/utils.js"></script>
    <script src="/js/userDynamicContent.js"></script>
    <link rel="stylesheet" href="/dynamicPages.css">
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<div>
    <nav>
        <div class="top-left">
            <img class="food-order-img margin-sm-right" src="/icons/food-order.png" alt="foodOrderIcon">
            <span class="poppins-bold h1-font-size">FOOD ORDER</span>
        </div>
        <div class="top-right">
            <img class="profile-img" src="/icons/profile.png" alt="/icons/profile.png">
        </div>
    </nav>

    <div class="main-cont">
        <div class="main-left-cont">
            <div class="main-left-headers" id="dashboard">
                <div class="main-left-header-card">
                    <i class='fa-solid fa-desktop margin-sm-right'></i>
                    <span>Dashboard</span>
                </div>
            </div>
            <div class="main-left-content-card" id="manageProducts">
                <div class="main-left-content-card-left">
                    <i class="fa fa-building margin-sm-right"></i>
                    <span>Manage Products</span>
                </div>
                <div class="main-left-content-card-right">
                    <span class="padding-sm-right">+</span>
                </div>
            </div>
            <div class="main-left-content-card" id="manageOffers">
                <div class="main-left-content-card-left">
                    <i class="fa-solid fa-location-dot margin-sm-right"></i>
                    <span>Manage Offers</span>
                </div>
                <div class="main-left-content-card-right">
                    <span class="padding-sm-right">+</span>
                </div>
            </div>
            <div class="main-left-content-card" id="manageComplaint">
                <div class="main-left-content-card-left">
                    <i class="fa-solid fa-mug-saucer margin-sm-right"></i>
                    <span>Manage Complaint</span>
                </div>
                <div class="main-left-content-card-right">
                    <span class="padding-sm-right">+</span>
                </div>
            </div>
            <div class="main-left-headers" id="logout">
                <div class="main-left-header-card">
                    <i class='fa-solid fa-desktop margin-sm-right'></i>
                    <span>Logout</span>
                </div>
            </div>
        </div>
        <div class="main-right-cont">

        </div>
    </div>
</div>
</body>
</html>
