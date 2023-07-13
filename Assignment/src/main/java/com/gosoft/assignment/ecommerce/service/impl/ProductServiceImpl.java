package com.gosoft.assignment.ecommerce.service.impl;

import com.gosoft.assignment.ecommerce.model.BaseEntity;
import com.gosoft.assignment.ecommerce.model.dto.request.ProductRequestDTO;
import com.gosoft.assignment.ecommerce.model.entity.TbProduct;
import com.gosoft.assignment.ecommerce.repo.ProductRepository;
import com.gosoft.assignment.ecommerce.service.BaseService;
import com.gosoft.assignment.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public TbProduct create(TbProduct entity) {
        return repository.save(entity);
    }

    @Override
    public List<TbProduct> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<TbProduct> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public TbProduct getById(Long id) {
        return repository.findById(id).orElse(new TbProduct());
    }

    @Override
    public TbProduct update(TbProduct entity) {
        return repository.save(entity);
    }

    @Override
    public Boolean delete(TbProduct entity) {
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
    public TbProduct createFullProduct(ProductRequestDTO dto) {
        return create(new TbProduct(UUID.randomUUID().toString(), dto.getSKU(),dto.getPrice(), dto.getName(), dto.getDetail(), new ArrayList<>()));
    }

    @Override
    public TbProduct updateAll(ProductRequestDTO dto) {
        TbProduct tbProduct = getById(dto.getId());
        tbProduct.setName(dto.getName());
        tbProduct.setSKU(dto.getSKU());
        tbProduct.setDetail(dto.getDetail());
        tbProduct.setPrice(dto.getPrice());
        return update(tbProduct);
    }
}
