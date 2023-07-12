package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.dao.TbRole;

import java.util.List;

public interface RoleService {

    List<TbRole> getByName (String name);


}
