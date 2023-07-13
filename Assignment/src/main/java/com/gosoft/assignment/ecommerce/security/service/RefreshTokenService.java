package com.gosoft.assignment.ecommerce.security.service;

import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.security.dao.TbRefreshToken;
public interface RefreshTokenService {

    TbRefreshToken create(TbRefreshToken refreshToken);

    TbRefreshToken createRefreshTokenByUser(TbUser tbUser);

    TbRefreshToken getByRefreshToken(String refreshToken);

    TbRefreshToken getByUser(TbUser tbUser);

    Boolean verifyRefreshTokenExpire(TbRefreshToken refreshToken);

    Integer deleteRefreshTokenByUser(TbUser tbUser);

}
