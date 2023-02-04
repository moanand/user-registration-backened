package com.registration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer uid;
    @NotEmpty(message = "Username is required")
    @Size(min = 5, max = 10, message = "Username must be between 5 and 10 characters")
    private String username;
    @NotNull(message = "Title is required")
    private String title;
    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters long")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters long")
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date should be past date")
    private LocalDate birthDate;
    @NotEmpty(message = "Email is required")
    @Email(message = "Please enter valid email")
    private String email;
    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Please enter 10-digit mobile number")
    private String phone;
    @NotNull(message = "Address must not be null")
    @Valid
    private Address address;
    @NotEmpty(message = "Password is required")
    private String password;
    private String confirmPassword;
    @AssertTrue(message = "Please accept terms & condtions to proceed")
    @JsonProperty
    private boolean isTncAccepted;
}
