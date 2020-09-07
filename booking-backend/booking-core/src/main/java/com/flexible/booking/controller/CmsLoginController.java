package com.flexible.booking.controller;

import com.flexible.booking.dto.request.AuthenticationRequest;
import com.flexible.booking.dto.response.AuthenticationResponse;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.service.CmsUserService;
import com.flexible.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cms")
public class CmsLoginController {
    @Autowired
    @Qualifier("adminPanelAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private CmsUserService cmsUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse createAuthToken(@Valid @RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new ApiForbiddenException("Incorrect username or password.");
        }

        UserDetails userDetails = cmsUserService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(userDetails.getUsername(), jwt);
    }
}
