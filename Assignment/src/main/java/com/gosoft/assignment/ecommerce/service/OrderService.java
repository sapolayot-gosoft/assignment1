package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.dto.request.OrderRequestDTO;
import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.repo.BaseRepository;

public interface OrderService extends BaseService<TbOrder> {

    TbOrder purchaseOrder(OrderRequestDTO dto, TbUser buyer);

    TbOrder updateStatus(Long id, String orderStatus);

}
