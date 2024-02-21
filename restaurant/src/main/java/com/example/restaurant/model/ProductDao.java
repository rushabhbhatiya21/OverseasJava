package com.example.restaurant.model;

import com.example.restaurant.utils.ImageUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productDescription;

    private double productPrice;

    private double discountedProductPrice;

    @Basic(fetch = FetchType.LAZY)
    @Column(length = 16000000)
    private byte[] productPhoto;

    @ManyToOne
    @JoinColumn(referencedColumnName = "categoryId", name = "categoryId")
    private CategoryDao category;

    @ManyToOne
    @JoinColumn(referencedColumnName = "subCategoryId", name = "subCategoryId")
    private SubCategoryDao subCategory;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId", name = "userId")
    private UserDao restaurant;


    public void setPhoto(MultipartFile photo) {
        try {
            this.productPhoto = ImageUtils.compressImage(photo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPhotoString() {
        return Base64.getEncoder().encodeToString(ImageUtils.decompressImage(productPhoto));
    }
}
