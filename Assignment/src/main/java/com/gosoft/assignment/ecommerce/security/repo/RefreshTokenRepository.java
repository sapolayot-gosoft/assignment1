package com.gosoft.assignment.ecommerce.security.repo;

import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.security.dao.TbRefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<TbRefreshToken, Long> {

    TbRefreshToken findByRefreshToken(String refreshToken);

    TbRefreshToken findByTbUser(TbUser user);

    @Modifying
    @Transactional
    @Query("UPDATE TbRefreshToken rt SET rt.refreshToken = :refreshToken WHERE rt.tbUser = :tbUser")
    Integer deleteRefreshTokenByTbUser(@Param("refreshToken") String refreshToken, @Param("tbUser") TbUser tbUser);
}
