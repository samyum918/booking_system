package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterUserRequest {
    @NotBlank(message = "First Name cannot be empty.")
    String firstName;
    @NotBlank(message = "Last Name cannot be empty.")
    String lastName;
    @NotBlank(message = "Phone cannot be empty.")
    String phone;
    String gender;
    @NotBlank(message = "Email Name cannot be empty.")
    @Email(message = "Email format incorrect.")
    String email;
    @NotBlank(message = "Username cannot be empty.")
    String username;
    @NotBlank(message = "Password cannot be empty.")
    String password;
}
