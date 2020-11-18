package com.flexible.booking.model;

import com.flexible.booking.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseModel implements UserDetails {
    @NotBlank(message = "First Name cannot be empty.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Phone cannot be empty.")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Email Name cannot be empty.")
    @Email(message = "Email format incorrect.")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Username cannot be empty.")
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> gaList = new ArrayList<>();
        gaList.add(() -> "USER");
        return gaList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
