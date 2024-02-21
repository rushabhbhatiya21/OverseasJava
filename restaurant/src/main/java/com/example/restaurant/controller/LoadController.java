package com.example.restaurant.controller;

import com.example.restaurant.model.AreaDao;
import com.example.restaurant.model.CityDao;
import com.example.restaurant.model.ComplaintDao;
import com.example.restaurant.model.UserDao;
import com.example.restaurant.service.AdminService;
import com.example.restaurant.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class LoadController {

    private final AdminService adminService;
    private final ComplaintService complaintService;

    @Autowired
    public LoadController(AdminService adminService, ComplaintService complaintService) {
        this.adminService = adminService;
        this.complaintService = complaintService;
    }

    @GetMapping("/loadAdminRegisterPage")
    public String loadAdminRegPage() {
        return "admin/registerAdmin";
    }

    @GetMapping("/loadUserRegisterPage")
    public String registerPage(Model model) {
        List<CityDao> cities = adminService.findAllCities();
        List<AreaDao> areas = adminService.findAllAreas();
        model.addAttribute("cities", cities);
        model.addAttribute("areas", areas);
        return "registerUser";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/addComplaint")
    public String addComplaint(Model model) {
        List<UserDao> restaurants = adminService.findAllRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "complaintAdd";
    }

    @PostMapping("/submitComplaint")
    @ResponseBody
    public ResponseEntity<String> submitComplaint(@RequestBody Map<String, String> jsonData) {
        if (complaintService.existsComplaintBySubject(jsonData.get("complaintSubject"))) {
            return new ResponseEntity<>("Complaint already exists with that subject", HttpStatus.BAD_REQUEST);
        }
        else {
            ComplaintDao complaint = new ComplaintDao();
            complaint.setComplaintSubject(jsonData.get("complaintSubject"));
            complaint.setComplaintDescription(jsonData.get("complaintDescription"));
            complaint.setCreatedAt(new Date());

            Optional<UserDao> restaurant = adminService.findUserById(Long.valueOf(jsonData.get("restaurantId")));
            complaint.setRestaurant(restaurant.get());

            complaint.setStatus(ComplaintDao.Status.PENDING);

            complaintService.saveComplaint(complaint);

            return ResponseEntity.ok("Complaint created!");
        }
    }
}
