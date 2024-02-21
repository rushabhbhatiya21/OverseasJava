package com.example.restaurant.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subCategory")
public class SubCategoryDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    private String subCategoryName;

    private String subCategoryDescription;

    @ManyToOne
    @JoinColumn(referencedColumnName = "categoryId", name = "categoryId")
    private CategoryDao category;

    @Override
    public String toString() {
        return subCategoryName + ", " + category.getCategoryName();
    }
}
