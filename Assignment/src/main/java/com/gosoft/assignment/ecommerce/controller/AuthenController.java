package com.gosoft.assignment.ecommerce.controller;

import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.model.dto.ResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;
import com.gosoft.assignment.ecommerce.security.ManageToken;
import com.gosoft.assignment.ecommerce.security.dao.TbRefreshToken;
import com.gosoft.assignment.ecommerce.security.model.dto.response.JwtResponse;
import com.gosoft.assignment.ecommerce.security.model.dto.response.TokenRefreshResponse;
import com.gosoft.assignment.ecommerce.security.service.RefreshTokenService;
import com.gosoft.assignment.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AuthenController {

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity register(@RequestBody UserRequestDTO dto) {
        try {
            TbUser tbUser = userService.getByUsername(dto.getUsername());
            if (tbUser != null) {
                return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "DUPLICATE_USER_EN", null, "DUPLICATE_USER_TH"));
            } else {
                TbUser createUser = userService.createProfile(dto);
                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/user/{userId}").buildAndExpand(createUser.getId()).toUri();
                return ResponseEntity.created(location).body(new ResponseDTO(HttpStatus.CREATED.value(), "REGISTER_USER_EN", null, "REGISTER_USER_TH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }

    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity login(@RequestBody UserRequestDTO dto) {
        try {
            TbUser tbUser = userService.getByUsername(dto.getUsername());
            if (tbUser != null) {
                if (userService.matchesPassword(dto.getPassword(), tbUser.getPassword())) {
                    String accessToken = ManageToken.generateTokenFromUsername(tbUser.getUsername());
                    TbRefreshToken refreshToken = refreshTokenService.createRefreshTokenByUser(tbUser);
                    return ResponseEntity.ok().body(new JwtResponse(accessToken, "Bearer", refreshToken.getRefreshToken(), tbUser.getId(), tbUser.getUsername(), tbUser.getRoles().stream().map(r -> r.getName()).toList()));
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "INVALID_PASSWORD_EN", null, "INVALID_PASSWORD_TH"));
                }

            } else {
                return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_USER_EN", null, "NOT_FOUND_USER_TH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @PostMapping(path = "/refreshToken", produces = "application/json")
    public ResponseEntity refreshToken(@RequestHeader("refreshToken") String refreshToken) {
        try {
            TbRefreshToken tbRefreshToken = refreshTokenService.getByRefreshToken(refreshToken);
            if (tbRefreshToken == null) {
                return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "REFRESH_TOKEN_NOT_DATABASE_EN", null, "REFRESH_TOKEN_NOT_DATABASE_TH"));
            }
            if (refreshTokenService.verifyRefreshTokenExpire(tbRefreshToken)) {
                String accessToken = ManageToken.generateTokenFromUsername(tbRefreshToken.getTbUser().getUsername());
                return ResponseEntity.ok().body(new TokenRefreshResponse(accessToken, tbRefreshToken.getRefreshToken()));
            } else {
                return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "REFRESH_TOKEN_EXPIRED_EN", null, "REFRESH_TOKEN_EXPIRED_TH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @PostMapping(path = "/logout", produces = "application/json")
    public ResponseEntity refreshToken() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getCredentials() instanceof String) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "TOKEN EXPIRED", null, "TOKEN EXPIRED"));
            }
            TbUser tbUser = (TbUser) auth.getCredentials();
            refreshTokenService.deleteRefreshTokenByUser(tbUser);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "LOGOUT_SUCCESS_EN", null, "LOGOUT_SUCCESS_TH"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));

        }

    }
}
