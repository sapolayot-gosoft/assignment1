package com.gosoft.assignment.ecommerce.repo;

import com.gosoft.assignment.ecommerce.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseRepository <T extends BaseEntity> extends JpaRepository<T, Long> {
}
