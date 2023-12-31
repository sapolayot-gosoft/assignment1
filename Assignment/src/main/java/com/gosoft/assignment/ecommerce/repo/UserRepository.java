package com.gosoft.assignment.ecommerce.repo;

import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<TbUser> {

    TbUser findByUsername(String username);
}
