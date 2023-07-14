package com.gosoft.assignment.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.dto.response.ProductResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table
@Data
@Cacheable
@AllArgsConstructor
@NoArgsConstructor
public class TbProduct extends BaseEntity {

    @Column
    private String productNo;

    @Column
    private String SKU;

    @Column
    private Double price;

    @Column
    private String name;

    @Column
    private String detail;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "tbProduct", cascade = CascadeType.ALL)
    private List<TbOrderDetail> tbOrderDetailList;

    public ProductResponseDTO toDTO(){
        return new ProductResponseDTO(id, productNo, SKU, price, name, detail);
    }

}
