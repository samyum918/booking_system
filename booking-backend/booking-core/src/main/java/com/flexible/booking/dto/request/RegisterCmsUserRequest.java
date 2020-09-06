package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterCmsUserRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
