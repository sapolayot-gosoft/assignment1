package com.gosoft.assignment.ecommerce.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class JwtResponse {
    private String accessToken;
    private String type;
    private String refreshToken;
    private Long id;
    private String username;
    private List<String> roles;
}
