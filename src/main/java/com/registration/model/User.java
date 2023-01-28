package com.registration.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private int sid;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private String address;
    private String password;
    private String confirmPassword;
    private boolean isTncAccepted;
}
