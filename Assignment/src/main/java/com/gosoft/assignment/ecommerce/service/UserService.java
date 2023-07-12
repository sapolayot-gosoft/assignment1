package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.dao.TbUser;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;

public interface UserService {

    TbUser create (TbUser user);

    TbUser createProfile (UserRequestDTO user);

    TbUser getByUsername(String username);

    Boolean matchesPassword(String password, String encodedPassword);

}
