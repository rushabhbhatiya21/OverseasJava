package com.example.overseas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "appointment")
public class AppointmentDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private Date createdOn;

    private Date modifiedOn;

    private Status status;

    private String startTime;

    private String endTime;

    public enum Status {
        PENDING,
        APPROVED
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "studentId")
    private UserDao studentId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "consultantId")
    private UserDao consultantId;
}
