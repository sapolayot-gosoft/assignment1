package com.gosoft.assignment.ecommerce.repo;

import com.gosoft.assignment.ecommerce.model.entity.TbRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<TbRole> {

    List<TbRole> findByName(String name);
}
