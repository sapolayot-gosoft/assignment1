package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.repo.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService <T extends BaseEntity> {

    T create(T entity) throws Exception;
    List<T> getAll() throws Exception;

    Page<T> getAll(Pageable pageable) throws Exception;

    T getById(Long id) throws Exception;

    T update(T entity) throws Exception;

    Boolean delete(T entity) throws Exception;

    Boolean deleteById(Long id) throws Exception;

}
