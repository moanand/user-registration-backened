package com.registration.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sid;
	@NotBlank(message = "UserName is mandatory")
    private String username;
	@NotBlank(message = "First Name is mandatory")
    private String firstName;
	@NotBlank(message = "Last Name is mandatory")
    private String lastName;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email is mandatory")
    private String email;
	@Digits(fraction = 0, integer = 10,message = "Please Enter correct number")
    private long phone;
	@NotBlank(message = "Please enter your address")
    private String address;
	@NotBlank(message = "Password is mandatory")
    private String password;
	@NotBlank(message = "Please enter confirm password")
    private String confirmPassword;
    private boolean isTncAccepted;
}
