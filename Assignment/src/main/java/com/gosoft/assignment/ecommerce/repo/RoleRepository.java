package com.gosoft.assignment.ecommerce.repo;

import com.gosoft.assignment.ecommerce.model.dao.TbRole;
import com.gosoft.assignment.ecommerce.model.dao.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<TbRole, Long> {

    List<TbRole> findByName(String name);
}
