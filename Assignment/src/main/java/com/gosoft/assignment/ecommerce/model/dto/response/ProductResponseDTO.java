package com.gosoft.assignment.ecommerce.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String productNo;
    private String SKU;
    private Double price;
    private String name;
    private String detail;
}
