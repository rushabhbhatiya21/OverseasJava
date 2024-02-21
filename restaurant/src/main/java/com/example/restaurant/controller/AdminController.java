package com.example.restaurant.controller;

import com.example.restaurant.model.*;
import com.example.restaurant.service.CategoryService;
import com.example.restaurant.service.AdminService;
import com.example.restaurant.service.ComplaintService;
import com.example.restaurant.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.restaurant.security.Constants.PAGE_SIZE;

@Controller
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService service;
    private final CategoryService categoryService;
    private final OfferService offerService;
    private final ComplaintService complaintService;

    @Autowired
    public AdminController(AdminService service, CategoryService categoryService, OfferService offerService, ComplaintService complaintService) {
        this.service = service;
        this.categoryService = categoryService;
        this.offerService = offerService;
        this.complaintService = complaintService;
    }

    @GetMapping("/loadAdminIndex")
    public String loadAdminIndex() {
        return "admin/adminIndex";
    }

    @GetMapping("/loadAdminDashboard")
    public String loadAdminDashboard() {
        return "admin/adminDashboard";
    }

    @GetMapping("/manageCity/{pageNo}")
    public String manageCity(Model model, @PathVariable String pageNo) {
        Page<CityDao> cityPages = service.findAllCityPages(Integer.parseInt(pageNo), PAGE_SIZE);
        service.addAttributesToModel(model, pageNo, cityPages);
        return "admin/cityManage";
    }

    @GetMapping("/manageArea/{pageNo}")
    public String manageArea(Model model, @PathVariable String pageNo) {
        Page<AreaDao> areaPages = service.findAllAreaPages(Integer.parseInt(pageNo), PAGE_SIZE);
        service.addAttributesToModel(model, pageNo, areaPages);
        return "admin/areaManage";
    }

    @GetMapping("/manageCategory/{pageNo}")
    public String manageCategory(Model model, @PathVariable String pageNo) {
        Page<CategoryDao> categoryPages = categoryService.findAllCategoryPages(Integer.parseInt(pageNo), PAGE_SIZE);
        service.addAttributesToModel(model, pageNo, categoryPages);
        return "admin/categoryManage";
    }

    @GetMapping("/manageSubCategory/{pageNo}")
    public String manageSubCategory(Model model, @PathVariable String pageNo) {
        Page<SubCategoryDao> subCategoryPages = categoryService.findAllSubCategoryPages(Integer.parseInt(pageNo), PAGE_SIZE);
        service.addAttributesToModel(model, pageNo, subCategoryPages);
        return "admin/subCategoryManage";
    }

    @GetMapping("/manageOffers/{pageNo}")
    public String loadManageOffersAdmin(@PathVariable String pageNo, Model model) {
        Page<OfferDao> offerPages = offerService.findAllOffers(Integer.parseInt(pageNo) - 1, PAGE_SIZE);

        model.addAttribute("currentPage", Integer.parseInt(pageNo));
        model.addAttribute("no", offerPages.getNumber());
        model.addAttribute("totalPages", offerPages.getTotalPages());
        model.addAttribute("totalElements", offerPages.getTotalElements());
        model.addAttribute("size", offerPages.getSize());
        model.addAttribute("content", offerPages.getContent());

        return "admin/offersManage";
    }

    @GetMapping("/manageComplaints/{pageNo}")
    public String loadManageComplaints(@PathVariable String pageNo, Model model) {
        Page<ComplaintDao> offerPages = complaintService.findAllComplaintPages(Integer.parseInt(pageNo) - 1, PAGE_SIZE);

        model.addAttribute("currentPage", Integer.parseInt(pageNo));
        model.addAttribute("no", offerPages.getNumber());
        model.addAttribute("totalPages", offerPages.getTotalPages());
        model.addAttribute("totalElements", offerPages.getTotalElements());
        model.addAttribute("size", offerPages.getSize());
        model.addAttribute("content", offerPages.getContent());

        return "admin/complaintsManage";
    }

    @GetMapping("/addCity")
    public String addCity() {
        return "admin/cityRegistration";
    }

    @GetMapping("/addCategory")
    public String addCategory() {
        return "admin/categoryRegistration";
    }

    @GetMapping("/addSubCategory")
    public String addSubCategory(Model model) {
        List<CategoryDao> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "admin/subCategoryRegistration";
    }

    @PostMapping("/submitCity")
    @ResponseBody
    public ResponseEntity<String> submitCity(@RequestBody CityDao city) {
        System.out.println(city.getCityName());

        if (service.existsCityByName(city.getCityName())) {
            //city present
            return new ResponseEntity<>("City already exists", HttpStatus.BAD_REQUEST);
        }
        else {
            service.saveCity(city);
            return ResponseEntity.ok("City added successfully.");
        }
    }

    @PostMapping("/updateCity")
    @ResponseBody
    public ResponseEntity<String> updateCity(@RequestBody CityDao city) {
        if (service.existsCityByName(city.getCityName())) {
            System.out.println(city.getCityName());
            Optional<CityDao> updatedCity = service.findCityByCityName(city.getCityName());
            updatedCity.get().setCityName(city.getCityName());
            updatedCity.get().setCityDescription(city.getCityDescription());
            service.saveCity(updatedCity.get());
            return ResponseEntity.ok(city.getCityName() + " edited successfully");
        }
        else {
            return new ResponseEntity<>("City does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/submitCategory")
    @ResponseBody
    public ResponseEntity<String> submitCategory(@RequestBody CategoryDao category) {
        if (categoryService.existsCategoryByName(category.getCategoryName())) {
            //category present
            return new ResponseEntity<>("Category already exists", HttpStatus.BAD_REQUEST);
        }
        else {
            categoryService.saveCategory(category);
            return ResponseEntity.ok("Category added successfully.");
        }
    }

    @PostMapping("/submitArea")
    @ResponseBody
    public ResponseEntity<String> submitArea(@RequestBody Map<String, String> jsonData) {
        if (service.existsAreaByName(jsonData.get("areaName"))) {
            //area present
            Optional<AreaDao> area = service.findAreaByName(jsonData.get("areaName"));
            area.get().setAreaDescription(jsonData.get("areaDescription"));
            return ResponseEntity.ok("Area updated successfully.");
        }
        else {
            AreaDao area = new AreaDao();
            area.setAreaName(jsonData.get("areaName"));
            area.setAreaDescription(jsonData.get("areaDescription"));

            Optional<CityDao> city = service.findCityByName(jsonData.get("cityName"));
            area.setCity(city.get());

            service.saveArea(area);
            return ResponseEntity.ok("Area added successfully.");
        }
    }

    @PostMapping("/submitSubCategory")
    @ResponseBody
    public ResponseEntity<String> submitSubCategory(@RequestBody Map<String, String> jsonData) {
        if (categoryService.existsSubCategoryByName(jsonData.get("subCategoryName"))) {
            //category present
            return new ResponseEntity<>("Sub Category already exists", HttpStatus.BAD_REQUEST);
        }
        else {
            SubCategoryDao subCategory = new SubCategoryDao();
            subCategory.setSubCategoryName(jsonData.get("subCategoryName"));
            subCategory.setSubCategoryDescription(jsonData.get("subCategoryDescription"));

            Optional<CategoryDao> category = categoryService.findCategoryByName(jsonData.get("categoryName"));
            subCategory.setCategory(category.get());

            categoryService.saveSubCategory(subCategory);
            return ResponseEntity.ok("Sub Category added successfully.");
        }
    }

    //edit city

    //delete city
    @DeleteMapping("/deleteCity/{cityId}")
    @ResponseBody
    public ResponseEntity<String> deleteCity(@PathVariable String cityId) {
        try {
            service.deleteCityById(Long.valueOf(cityId));
            return ResponseEntity.ok("City deleted!");
        }
        catch (Exception e) {
            return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    @ResponseBody
    public ResponseEntity<String> categoryCity(@PathVariable String categoryId) {
        try {
            categoryService.deleteCategoryById(Long.valueOf(categoryId));
            return ResponseEntity.ok("Category deleted!");
        }
        catch (Exception e) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }

    //delete area
    @DeleteMapping("/deleteArea/{areaId}")
    @ResponseBody
    public ResponseEntity<String> deleteArea(@PathVariable String areaId) {
        try {
            service.deleteAreaById(Long.valueOf(areaId));
            return ResponseEntity.ok("Area deleted!");
        }
        catch (Exception e) {
            return new ResponseEntity<>("Area not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteSubCategory/{subCategoryId}")
    @ResponseBody
    public ResponseEntity<String> subCategoryCity(@PathVariable String subCategoryId) {
        try {
            categoryService.deleteSubCategoryById(Long.valueOf(subCategoryId));
            return ResponseEntity.ok("Category deleted!");
        }
        catch (Exception e) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteOffer/{offerId}")
    @ResponseBody
    public ResponseEntity<String> deleteOffer(@PathVariable String offerId) {
        try {
            offerService.deleteOfferById(Long.valueOf(offerId));
            return ResponseEntity.ok("Offer deleted successfully");
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error deleting offer", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/addArea")
    public String addArea() {
        return "admin/areaRegistration";
    }

    @GetMapping("/getAllCityNames")
    @ResponseBody
    public List<String> sendCityNames() {
        List<CityDao> cities = service.findAllCities();
        List<String> cityNames = new ArrayList<>();

        for(CityDao city : cities) {
            cityNames.add(city.getCityName());
        }

        return cityNames;
    }

    //edit area


    @GetMapping("/manageRestaurant/{pageNo}")
    public String manageRestaurant(@PathVariable String pageNo, Model model) {
        Page<UserDao> restaurantPages = (service.findAllUserPages(Integer.parseInt(pageNo), PAGE_SIZE));
        model.addAttribute("currentPage", Integer.parseInt(pageNo));
        model.addAttribute("no", restaurantPages.getNumber());
        model.addAttribute("totalPages", restaurantPages.getTotalPages());
        model.addAttribute("totalElements", restaurantPages.getTotalElements());
        model.addAttribute("size", restaurantPages.getSize());
        model.addAttribute("content", restaurantPages.getContent());

        return "admin/restaurantManage";
    }

    @DeleteMapping("/deleteRestaurant/{userId}")
    @ResponseBody
    public ResponseEntity<String> deleteRestaurant(@PathVariable String userId) {
        try {
            service.deleteRestaurantById(Long.valueOf(userId));
            return ResponseEntity.ok("Restaurant deleted!");
        }
        catch (Exception e) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateComplaint")
    @ResponseBody
    public ResponseEntity<String> updateComplaint(@RequestParam String complaintId, @RequestParam String status) {
        Optional<ComplaintDao> complaint = complaintService.findComplaintById(Long.valueOf(complaintId));
        if (complaint.isPresent()) {
            complaint.get().setModifiedOn(new Date());
            complaint.get().setStatus(ComplaintDao.Status.valueOf(status));
            complaintService.saveComplaint(complaint.get());
            return ResponseEntity.ok("Status changed");
        }
        return new ResponseEntity<>("Error occurred while updating complaint", HttpStatus.BAD_REQUEST);
    }
    
//    @GetMapping("/manageFeedback")
//    public String manageFeedback() {
//        return "cityRegistration";
//    }
}
