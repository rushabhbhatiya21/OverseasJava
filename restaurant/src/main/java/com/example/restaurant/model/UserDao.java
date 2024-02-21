package com.example.restaurant.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.geom.Area;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String email;

    private String password;

    private String restaurantName;

    private String contactNumber;

    private String address;

    private Role role;

    @ManyToOne
    @JoinColumn(referencedColumnName = "areaId", name = "areadId")
    private AreaDao area;

    @ManyToOne
    @JoinColumn(referencedColumnName = "cityId", name = "cityId")
    private CityDao city;

    public enum Role {
        ADMIN,
        USER
    }

    @Override
    public String toString() {
        return address + ", " + area.getAreaName() + ", " + city.getCityName();
    }
}
