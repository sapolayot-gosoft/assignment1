package com.gosoft.assignment.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gosoft.assignment.ecommerce.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
@Data
@Cacheable
public class TbOrderDetail extends BaseEntity {

    @Column
    private Integer qty;

    @ManyToOne(cascade = CascadeType.ALL)
    private TbProduct tbProduct;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private TbOrder tbOrder;
}
