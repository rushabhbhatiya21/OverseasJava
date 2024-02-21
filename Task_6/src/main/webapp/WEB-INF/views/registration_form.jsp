<%--
  Created by IntelliJ IDEA.
  User: System 11
  Date: 07-02-2024
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .registration-container {
            margin-top: 100px;
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
            padding: 20px;
            border: 1px solid #dfe3e8;
            border-radius: 5px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .registration-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .btn {
            margin-top: 10px;
        }

        .form-check{
            margin-left: 50px;
        }
        .flag-container {
            position: absolute;
            top: 0;
            right: 20px;
            width: 40px;
            height: 100%;
            pointer-events: none; /* Allow clicks to pass through the flag container to the select box */
        }

        .flag-image {
            width: 100%;
            height: 100%;
            border-radius: 12px;
            object-fit: contain;
        }


    </style>
    <title>Employee Registration Form</title>
</head>

<body>
<div class="container">
    <div class="registration-container">
        <h2>Employee Registration</h2>
        <form action="${pageContext.request.contextPath}/api/admin/addUser" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="userRole" class="form-label">Role:</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="role" id="userRole" value="USER" checked>
                    <label class="form-check-label" for="userRole">User</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="role" id="adminRole" value="ADMIN">
                    <label class="form-check-label" for="adminRole">Admin</label>
                </div>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
            </div>

            <label for="country">Country:</label>
            <div class="form-group position-relative">
                <select class="form-control" id="country" name="country">
                    <!-- Add options for countries here -->
                </select>
                <div id="flag-container" class="flag-container">
                    <!-- Flag image will be displayed here -->
                </div>
            </div>

            <div class="form-group">
                <label for="state">State:</label>
                <select class="form-control" id="state" name="state">
                    <!-- Add options for states here -->
                </select>
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                <select class="form-control" id="city" name="city">
                    <!-- Add options for cities here -->
                </select>
            </div>

            <div class="form-group">
                <label for="education">Education:</label>
                <input type="text" class="form-control" id="education" name="education">
            </div>
            <div class="form-group">
                <label for="company">Company:</label>
                <input type="text" class="form-control" id="company" name="company">
            </div>
            <div class="form-group">
                <label for="photo">Photo:</label>
                <input type="file" class="form-control-file" id="photo" name="photo">
            </div>
            <div class="form-group">
                <label for="selfBio">Self-Bio:</label>
                <textarea class="form-control" id="selfBio" name="selfBio"></textarea>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Register</button>
            <a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/api/admin/getUsers">Back</a>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        let response; // Variable to store country and state API response

        // Fetch countries and states
        $.ajax({
            url: 'https://countriesnow.space/api/v0.1/countries/states',
            type: 'GET',
            success: function (responseData) {
                response = responseData; // Store the API response
                const countryDropdown = $('#country');

                // Add a default option
                countryDropdown.append($('<option>', {
                    value: '',
                    text: 'Select a country',
                    selected: true,
                    disabled: true
                }));

                $.each(response.data, function (index, country) {
                    countryDropdown.append($('<option>', {
                        value: country.name,
                        text: country.name
                    }));
                });
            },
            error: function (error) {
                console.error('Error fetching countries and states:', error);
            }
        });

        // Function to display the flag for the selected country
        function displayCountryFlag(selectedCountry) {
            const flagContainer = $('#flag-container');

            // Fetch the flag data
            $.ajax({
                url: 'https://countriesnow.space/api/v0.1/countries/flag/images',
                type: 'GET',
                success: function (flagData) {
                    // console.log(flagData);
                    const flags = flagData.data;

                    const flagObject = flags.filter(function (country) {
                        return (country.name === selectedCountry);
                    })
                    // console.log(flagUrl[0].flag);

                    const flagUrl = flagObject[0].flag;

                    // Display the flag image
                    if (flagUrl) {
                        // console.log(flagUrl);
                        flagContainer.html('<img class="flag-image" src="' + flagUrl + '" alt="' + selectedCountry + ' Flag" />');
                    } else {
                        flagContainer.empty(); // Clear the flag container if no flag found
                    }
                },
                error: function (error) {
                    console.error('Error fetching country flags:', error);
                }
            });
        }

        // Populate states based on the selected country
        $('#country').change(function () {
            const selectedCountry = $(this).val();
            displayCountryFlag(selectedCountry);

            // displayCountryFlag(selectedCountry);
            const stateDropdown = $('#state');

            // Clear existing options
            stateDropdown.empty();

            // Add a default option
            stateDropdown.append($('<option>', {
                value: '',
                text: 'Select a state',
                selected: true,
                disabled: true
            }));

            // Find the selected country in the response
            const selectedCountryData = response.data.find(function (country) {
                return country.name === selectedCountry;
            });

            if (selectedCountryData && selectedCountryData.states) {
                const states = selectedCountryData.states;
                // var stateDropdown = $('#state');
                $.each(states, function (index, state) {
                    stateDropdown.append($('<option>', {
                        value: state.name,
                        text: state.name
                    }));
                });
            }
        });

        // Populate cities based on the selected country and state
        $('#state').change(function () {
            const selectedCountry = $('#country').val();
            const selectedState = $(this).val();
            const cityDropdown = $('#city');

            // Clear existing options
            cityDropdown.empty();

            // Add a default option
            cityDropdown.append($('<option>', {
                value: '',
                text: 'Select a city',
                selected: true,
                disabled: true
            }));

            // Use the provided API to fetch cities within states
            $.ajax({
                url: 'https://countriesnow.space/api/v0.1/countries/state/cities',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ country: selectedCountry, state: selectedState }),
                success: function (cityData) {
                    const cities = cityData.data || [];
                    $.each(cities, function (index, city) {
                        cityDropdown.append($('<option>', {
                            value: city,
                            text: city
                        }));
                    });
                },
                error: function (error) {
                    console.error('Error fetching cities:', error);
                }
            });
        });
    });
</script>

</body>
</html>
