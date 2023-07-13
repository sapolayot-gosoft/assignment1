package com.gosoft.assignment.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderProductRequestDTO {
    private Long productId;
    private Integer qty;
}
