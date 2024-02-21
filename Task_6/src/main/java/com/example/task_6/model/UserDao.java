package com.example.task_6.model;

import com.example.task_6.security.UserInfoDetails;
import jakarta.persistence.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserDao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String email;

        private String password;

        private String phoneNumber;

        @Embedded
        private Address address;

        @Column(nullable = true)
        private String education;

        @Column(nullable = true)
        private String company;

        @Basic(fetch = FetchType.LAZY)
        @Column(length=16000000, nullable = true)
        private byte[] photo;

        @Column(nullable = true)
        private String selfBio;

        @Enumerated(EnumType.STRING) // specify the EnumType
        @Column(nullable = false)
        private Role role; // Use the Role enum type for role

        @Column
        private Date createdAt;

        public UserDao() {
            this.createdAt = new Date();
        }

    // Define the Role enum
        public enum Role {
            USER,
            ADMIN
        }

        public UserDao(String name, String email, String password,
                       String phoneNumber, Address address, String education, MultipartFile photo,
                       String company, String selfBio, String role) {
            this.name = name;
            this.role = Role.valueOf(role);
            this.email = email;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.education = education;
            try {
                this.photo = ImageUtils.compressImage(photo.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.company = company;
            this.selfBio = selfBio;
            this.createdAt = new Date();
        }

    public void setPhoto(MultipartFile photo) {
        try {
            this.photo = ImageUtils.compressImage(photo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String  getPhotoString() {
        return Base64.getEncoder().encodeToString(ImageUtils.decompressImage(photo));
    }

    @Getter
    @Setter
    @Embeddable
    public static class Address {
            private String city;
            private String state;
            private String country;

            public Address(String city, String state, String country) {
                this.city=city;
                this.state=state;
                this.country=country;
            }

            public Address() {

            }

            public String toString() {
                return getCity() + ", " + getState() + ", " + getCountry();
            }
    }
}
