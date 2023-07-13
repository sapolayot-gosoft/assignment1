package com.gosoft.assignment.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductRequestDTO {
    private Long id;
    private String SKU;
    private Double price;
    private String name;
    private String detail;
}
