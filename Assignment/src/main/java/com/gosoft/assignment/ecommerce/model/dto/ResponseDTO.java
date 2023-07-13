package com.gosoft.assignment.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ResponseDTO {

    private int code;

    private String subject;

    private Object data;

    private String message;
}
