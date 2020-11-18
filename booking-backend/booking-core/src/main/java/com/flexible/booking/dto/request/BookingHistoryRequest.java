package com.flexible.booking.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BookingHistoryRequest {
    @NotBlank(message = "Username cannot be empty")
    String username;
}
