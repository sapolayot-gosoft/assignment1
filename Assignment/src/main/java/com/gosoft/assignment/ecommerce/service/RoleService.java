package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.model.entity.TbRole;
import com.gosoft.assignment.ecommerce.repo.BaseRepository;

import java.util.List;

public interface RoleService extends BaseService<TbRole>{

    List<TbRole> getByName (String name);


}
