package com.gosoft.assignment.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderRequestDTO  {
    private Long sellerId;
    private List<OrderProductRequestDTO> listProduct;
}
