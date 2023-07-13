package com.gosoft.assignment.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDTO {

    private String username;

    private String fullName;

    private String phone;

    private String password;
}
