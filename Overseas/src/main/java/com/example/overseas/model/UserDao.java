package com.example.overseas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    private boolean isVerified;

    private boolean isAvailable;

    private String profile;

    private String specification;

    private String action;

    private Role role;

    public enum Role {
        ADMIN,
        CONSULTANT,
        STUDENT
    }
}
