package com.example.restaurant.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "area")
public class AreaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areaId;

    private String areaName;

    private String areaDescription;

    @ManyToOne
    @JoinColumn(referencedColumnName = "cityId", name = "cityId")
    private CityDao city;
}
