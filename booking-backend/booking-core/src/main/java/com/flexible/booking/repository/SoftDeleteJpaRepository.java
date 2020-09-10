package com.flexible.booking.repository;

import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.SoftDeleteBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface SoftDeleteJpaRepository<T extends SoftDeleteBaseModel, ID> extends JpaRepository<T, ID> {
    default void softDelete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        entity.setDeleteDate(LocalDateTime.now());
        save(entity);
    }

    default void softDeleteById(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        this.softDelete(findById(id).orElseThrow(() -> new ApiResourceNotFoundException("Record does not exist.")));
    }
}
