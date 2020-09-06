package com.flexible.booking.service;

import com.flexible.booking.dto.request.RegisterUserRequest;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.model.User;
import com.flexible.booking.repository.UserRepository;
import com.flexible.booking.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public User register(RegisterUserRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if(userOpt.isPresent()) {
            throw new ApiForbiddenException("Email already exists.");
        }

        User user = ProjectUtils.transformFrom(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }
}
