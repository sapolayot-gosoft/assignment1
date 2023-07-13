package com.gosoft.assignment.ecommerce.security.service;

import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.security.dao.TbRefreshToken;
import com.gosoft.assignment.ecommerce.security.repo.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${app.jwtRefreshExpirationInMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    RefreshTokenRepository repository;

    @Override
    public TbRefreshToken create(TbRefreshToken refreshToken) {
        return repository.save(refreshToken);
    }

    @Override
    public TbRefreshToken createRefreshTokenByUser(TbUser tbUser) {
        TbRefreshToken tbRefreshToken = repository.findByTbUser(tbUser);
        if (tbRefreshToken != null) {
            tbRefreshToken.setExpiryRefreshTokenDate(new Date(new Date().getTime() + refreshTokenDurationMs));
            tbRefreshToken.setRefreshToken(UUID.randomUUID().toString());
            repository.save(tbRefreshToken);
        } else {
            tbRefreshToken = create(new TbRefreshToken(tbUser, UUID.randomUUID().toString(), new Date(new Date().getTime() + refreshTokenDurationMs)));
        }
        return tbRefreshToken;
    }

    @Override
    public TbRefreshToken getByRefreshToken(String refreshToken) {
        return repository.findByRefreshToken(refreshToken);
    }

    @Override
    public TbRefreshToken getByUser(TbUser tbUser) {
        return repository.findByTbUser(tbUser);
    }

    @Override
    public Boolean verifyRefreshTokenExpire(TbRefreshToken refreshToken) {
        return refreshToken.getExpiryRefreshTokenDate().after(new Date());
    }

    @Override
    public Integer deleteRefreshTokenByUser(TbUser tbUser) {
        String expiredRefreshToken = tbUser.getRefreshToken().getRefreshToken() + "#expired";
        return repository.deleteRefreshTokenByTbUser(expiredRefreshToken, tbUser);
    }
}
