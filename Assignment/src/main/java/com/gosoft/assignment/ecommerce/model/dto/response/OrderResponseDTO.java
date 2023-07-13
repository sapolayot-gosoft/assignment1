package com.gosoft.assignment.ecommerce.model.dto.response;

import com.gosoft.assignment.ecommerce.model.entity.TbOrderDetail;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.model.tbEnum.TbOrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private String orderNo;
    private TbOrderStatus orderStatus;
    private Date orderDate;
    private List<TbOrderDetail> tbOrderDetail;
    private TbUser seller;
    private TbUser buyer;
}
