package com.flexible.booking.repository;

import com.flexible.booking.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Integer countByName(String name);

    @Query("SELECT s FROM Store s WHERE s.canBook = TRUE ORDER BY s.name")
    List<Store> findAllStores();
}