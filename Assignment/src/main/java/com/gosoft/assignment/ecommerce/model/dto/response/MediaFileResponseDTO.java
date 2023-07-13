package com.gosoft.assignment.ecommerce.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MediaFileResponseDTO {

    private String name;

    private String url;

    private String contentType;

    private String type;

    private Long size;

    private MediaFileUserResponseDTO user;

}


