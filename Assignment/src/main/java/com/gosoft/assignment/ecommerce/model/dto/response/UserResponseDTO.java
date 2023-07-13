package com.gosoft.assignment.ecommerce.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponseDTO {

    private Long id;

    private String username;

    private String fullName;

    private String phone;

    private String profileImage;
}
