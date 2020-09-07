package com.flexible.booking.controller;

import com.flexible.booking.dto.request.RegisterUserRequest;
import com.flexible.booking.model.User;
import com.flexible.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/front/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterUserRequest request) {
        return userService.register(request);
    }

    @PatchMapping("/update/{id:\\d+}")
    public void update(@PathVariable Integer id) {

    }
}
