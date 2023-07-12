package com.gosoft.assignment.ecommerce.controller;

import com.gosoft.assignment.ecommerce.model.dao.TbUser;
import com.gosoft.assignment.ecommerce.model.dto.ResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;
import com.gosoft.assignment.ecommerce.security.ManageToken;
import com.gosoft.assignment.ecommerce.security.model.JwtResponse;
import com.gosoft.assignment.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AuthenController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity register(@RequestBody UserRequestDTO dto) {
        try {
            TbUser tbUser = userService.getByUsername(dto.getUsername());
            if (tbUser != null) {
                return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "DUPLICATE_USER_EN", "DUPLICATE_USER_TH"));
            } else {
                TbUser createUser = userService.createProfile(dto);
                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/user/{userId}")
                        .buildAndExpand(createUser.getId()).toUri();
                return ResponseEntity.created(location).body(new ResponseDTO(HttpStatus.CREATED.value(), "REGISTER_USER_EN", "REGISTER_USER_TH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.getStackTrace().toString()));
        }

    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity login(@RequestBody UserRequestDTO dto) {
        try {
            TbUser tbUser = userService.getByUsername(dto.getUsername());
            if (tbUser != null) {
                String accessToken = ManageToken.generateTokenFromUsername(tbUser.getUsername());
                if (userService.matchesPassword(dto.getPassword(), tbUser.getPassword())) {
                    return ResponseEntity.ok().body(
                            new JwtResponse(
                                    accessToken,
                                    "Bearer",
                                    tbUser.getRefreshToken(),
                                    tbUser.getId(),
                                    tbUser.getUsername(),
                                    tbUser.getRoles().stream().map(r -> r.getName()).toList()
                            )
                    );
                } else {
                    return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "INVALID_PASSWORD_EN", "INVALID_PASSWORD_TH"));
                }

            } else {
                return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_USER_EN", "NOT_FOUND_USER_TH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.getStackTrace().toString()));
        }
    }
}
