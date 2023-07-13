package com.gosoft.assignment.ecommerce.repo;

import com.gosoft.assignment.ecommerce.model.entity.TbProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<TbProduct> {
}
