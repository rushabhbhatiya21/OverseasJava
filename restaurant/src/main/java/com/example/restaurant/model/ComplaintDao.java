package com.example.restaurant.model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "complaints")
public class ComplaintDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;

    private String complaintSubject;

    private String complaintDescription;

    private Status status;

    public enum Status {
        PENDING,
        RESOLVED
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId", name = "userId")
    private UserDao restaurant;

    private Date createdAt;

    private Date modifiedOn;
}
