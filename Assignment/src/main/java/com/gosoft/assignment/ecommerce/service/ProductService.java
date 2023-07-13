package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.dto.request.ProductRequestDTO;
import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.model.entity.TbProduct;
import com.gosoft.assignment.ecommerce.repo.BaseRepository;

public interface ProductService extends BaseService<TbProduct> {


    TbProduct createFullProduct(ProductRequestDTO dto);

    TbProduct updateAll(ProductRequestDTO dto);
}
