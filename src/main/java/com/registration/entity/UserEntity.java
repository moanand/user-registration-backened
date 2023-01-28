package com.registration.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sid;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private String address;
    private String password;
    @Transient
    private String confirmPassword;
    private boolean isTncAccepted;

}
