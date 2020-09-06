package com.flexible.booking.repository;

import com.flexible.booking.model.CmsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsUserRepository extends JpaRepository<CmsUser, Integer> {
    Integer countByUsername(String username);
}
