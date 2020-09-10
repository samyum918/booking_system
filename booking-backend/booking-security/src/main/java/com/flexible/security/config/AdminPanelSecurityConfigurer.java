package com.flexible.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AdminPanelSecurityConfigurer extends BaseSecurityConfigurer {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/cms/**").authorizeRequests()
            .antMatchers("/cms/login"
//                        , "/cms/user/register"
                        ).permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .cors()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
            .accessDeniedHandler(restfulAccessDeniedHandler)
            .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    @Bean(name = "adminPanelAuthenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
