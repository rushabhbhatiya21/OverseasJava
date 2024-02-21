package com.example.restaurant.controller;

import com.example.restaurant.model.*;
import com.example.restaurant.service.CategoryService;
import com.example.restaurant.service.OfferService;
import com.example.restaurant.service.ProductService;
import com.example.restaurant.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.restaurant.security.Constants.PAGE_SIZE;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final AdminService adminService;
    private final OfferService offerService;

    @Autowired
    public UserController(ProductService productService, CategoryService categoryService, AdminService adminService, OfferService offerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.adminService = adminService;
        this.offerService = offerService;
    }

    @GetMapping("/loadUserIndex")
    public String loadUserIndex() {
        return "userIndex";
    }

    @GetMapping("/loadUserDashboard")
    public String loadUserDashboard() {
        return "user/userDashboard";
    }

    @GetMapping("/manageProducts/{pageNo}")
    public String loadManageProducts(@PathVariable String pageNo, Model model) {
        Page<ProductDao> productPages = productService.findAllProductPages(
                Long.valueOf(Objects.requireNonNull(AdminService.getLoggedInUserDetails()).getUsername()),
                Integer.parseInt(pageNo) - 1,
                PAGE_SIZE);

        model.addAttribute("currentPage", Integer.parseInt(pageNo));
        model.addAttribute("no", productPages.getNumber());
        model.addAttribute("totalPages", productPages.getTotalPages());
        model.addAttribute("totalElements", productPages.getTotalElements());
        model.addAttribute("size", productPages.getSize());
        model.addAttribute("content", productPages.getContent());

        return "user/productsManage";
    }

    @GetMapping("/manageOffers/{pageNo}")
    public String loadManageOffers(@PathVariable String pageNo, Model model) {
        Page<OfferDao> offerPages = offerService.findOffersForRestaurant(
                Long.valueOf(Objects.requireNonNull(AdminService.getLoggedInUserDetails().getUsername())),
                Integer.parseInt(pageNo) - 1,
                PAGE_SIZE
        );

        model.addAttribute("currentPage", Integer.parseInt(pageNo));
        model.addAttribute("no", offerPages.getNumber());
        model.addAttribute("totalPages", offerPages.getTotalPages());
        model.addAttribute("totalElements", offerPages.getTotalElements());
        model.addAttribute("size", offerPages.getSize());
        model.addAttribute("content", offerPages.getContent());

        return "user/offersManage";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        List<CategoryDao> categories = categoryService.findAllCategories();
        List<SubCategoryDao> subCategories = categoryService.findAllSubCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("subCategories", subCategories);

        return "user/productsRegistration";
    }

    @GetMapping("/addOffer")
    public String addOffer(Model model) {
//        Long userId = Long.valueOf(Objects.requireNonNull(AdminService.getLoggedInUserDetails()).getUsername());
//        List<OfferDao> offers = offerService.findAllOffers(userId);
//
//        model.addAttribute("offers", offers);

        List<CategoryDao> categories = categoryService.findAllCategories();
        List<SubCategoryDao> subCategories = categoryService.findAllSubCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("subCategories", subCategories);

        return "user/offersRegistration";
    }


    @PostMapping("/submitProduct")
    @ResponseBody
    public ResponseEntity<String> submitProduct(@RequestParam("productPhoto") MultipartFile productPhoto,
                                                @RequestParam("categoryName") String categoryName,
                                                @RequestParam("subCategoryName") String subCategoryName,
                                                @RequestParam("productName") String productName,
                                                @RequestParam("productDescription") String productDescription,
                                                @RequestParam("productPrice") String productPrice
    ) {
        if (productService.existsProductByName(productName)) {
            //product present
            return new ResponseEntity<>("Product already exists.", HttpStatus.BAD_REQUEST);
        }
        else {
            ProductDao newProduct = new ProductDao();
            newProduct.setProductName(productName);
            newProduct.setProductDescription(productDescription);
            newProduct.setProductPrice(Double.parseDouble(productPrice));

            Optional<CategoryDao> category = categoryService.findCategoryByName(categoryName);
            newProduct.setCategory(category.get());

            Optional<SubCategoryDao> subCategory = categoryService.findSubCategoryByName(subCategoryName);
            newProduct.setSubCategory(subCategory.get());

            Long userId = Long.valueOf(Objects.requireNonNull(AdminService.getLoggedInUserDetails()).getUsername());

            newProduct.setRestaurant(adminService.findUserById(userId).get());

            newProduct.setPhoto(productPhoto);

            productService.saveProduct(newProduct);
            return ResponseEntity.ok("Product added successfully.");
        }
    }

    @PostMapping("/submitOffer")
    @ResponseBody
    public ResponseEntity<String> submitOffer(@RequestBody Map<String, String> jsonData) {
        if (offerService.existsOfferByName(jsonData.get("offerName"))) {
            return new ResponseEntity<>("Offer already exists!", HttpStatus.BAD_REQUEST);
        }
        else {
            OfferDao offer = new OfferDao();
            offer.setOfferName(jsonData.get("offerName"));
            offer.setOfferDescription(jsonData.get("offerDescription"));
            offer.setDiscount(Double.parseDouble(jsonData.get("offerDiscount")));

            Long userId = Long.valueOf(Objects.requireNonNull(AdminService.getLoggedInUserDetails()).getUsername());
            offer.setRestaurant(adminService.findUserById(userId).get());

            Optional<SubCategoryDao> subCategory = categoryService.findSubCategoryByName(jsonData.get("subCategoryName"));
            offer.setSubCategory(subCategory.get());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(jsonData.get("startTime"), formatter);
            LocalDateTime endTime = LocalDateTime.parse(jsonData.get("endTime"), formatter);
            offer.setStartTime(startTime);
            offer.setEndTime(endTime);

            offerService.saveOffer(offer);
            return ResponseEntity.ok("Offer saved!");
        }
    }

    @DeleteMapping("/deleteProduct/{productId}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        try {
            productService.deleteProduct(Long.valueOf(productId));
            return ResponseEntity.ok("Product deleted successfully.");
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error occurred deleting product!", HttpStatus.BAD_REQUEST);
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

}
