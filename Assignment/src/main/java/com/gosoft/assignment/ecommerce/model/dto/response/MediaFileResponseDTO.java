package com.gosoft.assignment.ecommerce.model.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MediaFileResponseDTO {

    private String name;

    private String url;

    private String contentType;

    private String type;

    private MediaFileUserResponseDTO user;

}


