package com.gosoft.assignment.ecommerce.service.impl;

import com.gosoft.assignment.ecommerce.model.entity.TbRole;
import com.gosoft.assignment.ecommerce.repo.RoleRepository;
import com.gosoft.assignment.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<TbRole> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public TbRole create(TbRole entity) {
        return repository.save(entity);
    }

    @Override
    public List<TbRole> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<TbRole> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public TbRole getById(Long id) {
        return repository.findById(id).orElse(new TbRole());
    }

    @Override
    public TbRole update(TbRole entity) {
        return repository.save(entity);
    }

    @Override
    public Boolean delete(TbRole entity) {
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
}
