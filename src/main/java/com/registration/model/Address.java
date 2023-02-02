package com.registration.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Address {
    private Integer aid;
    @NotEmpty(message = "Country is required field")
    private String country;
    @NotEmpty(message = "State is required field")
    private String state;
    @NotEmpty(message = "City is required field")
    private String city;
    @Size(min = 6, max = 6, message = "Please provide valid 6 digit pincode")
    private String pinCode;
}
