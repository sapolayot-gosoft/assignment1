package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.dao.TbRole;
import com.gosoft.assignment.ecommerce.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;

    @Override
    public List<TbRole> getByName(String name) {
        return repository.findByName(name);
    }
}
