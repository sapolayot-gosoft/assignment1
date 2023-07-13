package com.gosoft.assignment.ecommerce.service.impl;

import com.gosoft.assignment.ecommerce.model.dto.request.OrderRequestDTO;
import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.model.entity.TbOrderDetail;
import com.gosoft.assignment.ecommerce.model.entity.TbProduct;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.model.tbEnum.TbOrderStatus;
import com.gosoft.assignment.ecommerce.repo.OrderRepository;
import com.gosoft.assignment.ecommerce.service.BaseService;
import com.gosoft.assignment.ecommerce.service.OrderService;
import com.gosoft.assignment.ecommerce.service.ProductService;
import com.gosoft.assignment.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements BaseService<TbOrder>, OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public TbOrder create(TbOrder entity) {
        return repository.save(entity);
    }

    @Override
    public List<TbOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<TbOrder> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public TbOrder getById(Long id) {
        return repository.findById(id).orElse(new TbOrder());
    }

    @Override
    public TbOrder update(TbOrder entity) {
        return repository.save(entity);
    }

    @Override
    public Boolean delete(TbOrder entity) {
        try {
            repository.delete(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TbOrder purchaseOrder(OrderRequestDTO dto, TbUser buyer) {
        TbOrder order = new TbOrder();
        order.setOrderDate(new Date());
        order.setBuyer(buyer);
        order.setOrderNo(UUID.randomUUID().toString());
        List<TbOrderDetail> tbOrderDetailList = dto.getListProduct().stream().map(product -> {
            TbOrderDetail orderDetail = new TbOrderDetail();
            try {
                TbProduct tbProduct = productService.getById(product.getProductId());
                orderDetail.setTbProduct(tbProduct);
                orderDetail.setQty(product.getQty());
                orderDetail.setTbOrder(order);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return orderDetail;
        }).toList();
        order.setTbOrderDetail(tbOrderDetailList);
        order.setOrderStatus(TbOrderStatus.waiting);
        try {
            TbUser seller = userService.getById(dto.getSellerId());
            order.setSeller(seller);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return create(order);
    }

    @Override
    public TbOrder updateStatus(Long id, String orderStatus) {
        TbOrder tbOrder = getById(id);
        tbOrder.setOrderStatus(TbOrderStatus.valueOf(orderStatus));
        return update(tbOrder);
    }
}
