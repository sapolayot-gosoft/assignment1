package com.gosoft.assignment.ecommerce.controller;

import com.gosoft.assignment.ecommerce.model.dto.ResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.request.ProductRequestDTO;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;
import com.gosoft.assignment.ecommerce.model.entity.TbProduct;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
//@RequestMapping("/product")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/product", produces = "application/json")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO dto) {
        try {
            TbProduct product = productService.createFullProduct(dto);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "SAVE DATA", product.toDTO(), "SAVE SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }


    @GetMapping(path = "/products", produces = "application/json")
    public ResponseEntity getListProduct() {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "GET DATA", productService.getAll().stream().map(product -> product.toDTO()).toList(), "GET SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }

    }

    @GetMapping(path = "/product/{id}", produces = "application/json")
    public ResponseEntity getProduct(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "GET DATA", productService.getById(id).toDTO(), "GET SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @PutMapping(path = "/product", produces = "application/json")
    public ResponseEntity updateProduct(@RequestBody ProductRequestDTO dto) {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "UPDATE DATA", productService.updateAll(dto).toDTO(), "GET SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @DeleteMapping(path = "/product/{id}", produces = "application/json")
    public ResponseEntity deleteProductById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "DELETE DATA", productService.deleteById(id), "DELETE SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }
}
