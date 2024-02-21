package com.example.restaurant.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "offers")
public class OfferDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    private String offerName;

    private String offerDescription;

    private double discount;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(referencedColumnName = "subCategoryId", name = "subCategoryId")
    private SubCategoryDao subCategory;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId", name = "userId")
    private UserDao restaurant;
}
