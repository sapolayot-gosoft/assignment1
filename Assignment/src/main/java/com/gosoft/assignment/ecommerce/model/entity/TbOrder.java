package com.gosoft.assignment.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.dto.response.OrderResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.response.ProductResponseDTO;
import com.gosoft.assignment.ecommerce.model.tbEnum.TbOrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@Cacheable
public class TbOrder extends BaseEntity {

    @Column
    private String orderNo;

    @Column
    private TbOrderStatus orderStatus;

    @Column
    private Date orderDate;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "tbOrder", cascade = CascadeType.ALL)
    private List<TbOrderDetail> tbOrderDetail;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    private TbUser seller;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    private TbUser buyer;

    public OrderResponseDTO toDTO(){
        return new OrderResponseDTO(orderNo, orderStatus, orderDate, tbOrderDetail, buyer, seller);
    }

}
