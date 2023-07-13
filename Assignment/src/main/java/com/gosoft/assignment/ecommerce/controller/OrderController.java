package com.gosoft.assignment.ecommerce.controller;

import com.gosoft.assignment.ecommerce.model.dto.ResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.request.OrderRequestDTO;
import com.gosoft.assignment.ecommerce.model.dto.request.ProductRequestDTO;
import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.model.entity.TbProduct;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.service.OrderService;
import com.gosoft.assignment.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@RequestMapping("/order")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/order", produces = "application/json")
    public ResponseEntity createOrder(@RequestBody OrderRequestDTO dto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            TbOrder order = orderService.purchaseOrder(dto, (TbUser) auth.getCredentials());
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "SAVE DATA", order.toDTO(), "SAVE SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }


    @GetMapping(path = "/orders", produces = "application/json")
    public ResponseEntity getListOrder() {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "GET DATA", orderService.getAll().stream().map(order -> order.toDTO()).toList(), "GET SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }

    }

    @GetMapping(path = "/order/{id}", produces = "application/json")
    public ResponseEntity getOrder(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "GET DATA", orderService.getById(id).toDTO(), "GET SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @PutMapping(path = "/order/status", produces = "application/json")
    public ResponseEntity updateStatusOrder(@RequestBody Map<String, Object> dto) {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "UPDATE DATA", orderService.updateStatus(Long.valueOf((Integer)dto.get("id")), (String) dto.get("orderStatus")).toDTO(), "GET SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @DeleteMapping(path = "/order/{id}", produces = "application/json")
    public ResponseEntity deleteProductById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "DELETE DATA", orderService.deleteById(id), "DELETE SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }
}
