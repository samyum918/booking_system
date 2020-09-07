package com.flexible.booking.repository;

import com.flexible.booking.model.CmsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CmsUserRepository extends JpaRepository<CmsUser, Integer> {
    Optional<CmsUser> findByUsername(String username);
}
