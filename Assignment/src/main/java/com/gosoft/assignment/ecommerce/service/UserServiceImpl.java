package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.dao.TbRole;
import com.gosoft.assignment.ecommerce.model.dao.TbUser;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;
import com.gosoft.assignment.ecommerce.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public TbUser create(TbUser user) {
        List<TbRole> roles = roleService.getByName("user");
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public TbUser createProfile(UserRequestDTO user) {
        return create(
                new TbUser(
                        user.getUsername(),
                        user.getPhone(),
                        passwordEncoder.encode(user.getPassword())
                )
        );
    }

    @Override
    public TbUser getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean matchesPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }


}
