package com.flexible.booking.config;

import com.flexible.booking.service.CmsUserService;
import com.flexible.booking.service.UserService;
import com.flexible.security.config.AdminPanelSecurityConfigurer;
import com.flexible.security.config.FrontEndSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class CoreSecurityConfigurer {
    @Configuration
    @Order(1)
    public static class FrontEndSecurityConfig extends FrontEndSecurityConfigurer {
        @Autowired
        private UserService userService;

        @Bean(name = "userDetailsService")
        public UserDetailsService userDetailsService() {
            return username -> userService.loadUserByUsername(username);
        }
    }

    @Configuration
    @Order(2)
    public static class AdminPanelSecurityConfig extends AdminPanelSecurityConfigurer {
        @Autowired
        private CmsUserService cmsUserService;

        @Bean(name = "cmsUserDetailsService")
        public UserDetailsService userDetailsService() {
            return username -> cmsUserService.loadUserByUsername(username);
        }
    }
}
