package com.devdutt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = "user name is required")
    private String name;
    @Email(message = "Invalid email id")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number")
    private String mobile;

    private String gender;
    @Min(18)
    @Max(60)
    private int age;

    private String nationality;
}
