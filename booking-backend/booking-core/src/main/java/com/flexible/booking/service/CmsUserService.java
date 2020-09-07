package com.flexible.booking.service;

import com.flexible.booking.dto.request.RegisterCmsUserRequest;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.model.CmsUser;
import com.flexible.booking.repository.CmsUserRepository;
import com.flexible.booking.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CmsUserService implements UserDetailsService {
    @Autowired
    private CmsUserRepository cmsUserRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public CmsUser register(RegisterCmsUserRequest request) {
        Optional<CmsUser> cmsUserOpt = cmsUserRepository.findByUsername(request.getUsername());
        if(cmsUserOpt.isPresent()) {
            throw new ApiForbiddenException("CMS User already exists.");
        }

        CmsUser cmsUser = ProjectUtils.transformFrom(request, CmsUser.class);
        cmsUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return cmsUserRepository.save(cmsUser);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return cmsUserRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }
}
