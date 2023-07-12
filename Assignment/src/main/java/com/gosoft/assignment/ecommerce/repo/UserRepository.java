package com.gosoft.assignment.ecommerce.repo;

import com.gosoft.assignment.ecommerce.model.dao.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TbUser, Long> {

    TbUser findByUsername(String username);
}
