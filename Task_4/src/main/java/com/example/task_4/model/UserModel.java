package com.example.task_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String department;

    @Column
    private Date created_at;

    public UserModel() {
        this.created_at = new Date();
    }

    public UserModel(String name) {
        this.name = name;
        this.created_at = new Date();
    }

    public UserModel(String name, String department) {
        this.name = name;
        this.department = department;
        this.created_at = new Date();
    }
}