Objective:
* Create Hotel Management portal to develop a streamlined system for hotel and menu registration.
* Enhancing data accuracy and accessibility within the system.
Outcomes:
* Reduction in the time taken to register new hotels and items, leading to increased efficiency.
* By mandating required fields for registration, the system ensures that essential information is consistently captured for each registration.
Approach:
* Since it was given that we have to implement it in spring boot I created the models which are require, jsp page, etc. etc. normal java project pipeline.

/*Requirement to run application*/
Postgres connection mentioned in application.properties file should be altered and have to create a database named hrms.
Run the application one time which will create all the tables for models.	
Now add dummy data to table [area, city, category, sub-category, product, complain, offer, user]
To login: Navigate to http://localhost:8080/api/loginPage
To register new user: http://localhost:8080/api/register/registerUser
To register new admin: http://localhost:8080/api/register/registerAdmin
