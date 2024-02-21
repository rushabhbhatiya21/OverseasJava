package com.example.task_5_1.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@Repository
public class UserDao {

//    private final List<UserDetails> APPLICATION_USERS = Arrays.asList(
//            new org.springframework.security.core.userdetails.User(
//                    "Rushabh",
//                    "Rush123",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
//            ),
//            new org.springframework.security.core.userdetails.User(
//                    "Mitul",
//                    "Mitul123",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//            )
//    );
//
//    public UserDetails findUserByName(String username) {
//        return APPLICATION_USERS
//                .stream()
//                .filter(u -> u.getUsername().equals(username))
//                .findFirst()
//                .orElseThrow(() -> new UsernameNotFoundException("No user found!"));
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // specify the EnumType
    @Column(nullable = false)
    private Role role; // Use the Role enum type for role

    @Column
    private Date createdAt;

    // Define the Role enum
    public enum Role {
        USER,
        ADMIN
    }

    public UserDao() {
        this.createdAt = new Date();
    }

    public UserDao(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = Role.valueOf(role);
        this.createdAt = new Date();
    }
}
