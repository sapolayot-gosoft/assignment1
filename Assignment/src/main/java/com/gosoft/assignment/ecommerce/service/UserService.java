package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;
import com.gosoft.assignment.ecommerce.repo.BaseRepository;

public interface UserService extends BaseService<TbUser> {

    TbUser create (TbUser user);

    TbUser createProfile (UserRequestDTO user);

    TbUser getByUsername(String username);

    TbUser updateAll(UserRequestDTO dto, TbUser user);

    Boolean matchesPassword(String password, String encodedPassword);

}
