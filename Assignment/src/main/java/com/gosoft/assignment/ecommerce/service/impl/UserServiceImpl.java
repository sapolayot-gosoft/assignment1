package com.gosoft.assignment.ecommerce.service.impl;

import com.gosoft.assignment.ecommerce.model.entity.TbRole;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;
import com.gosoft.assignment.ecommerce.repo.UserRepository;
import com.gosoft.assignment.ecommerce.service.RoleService;
import com.gosoft.assignment.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
    public List<TbUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<TbUser> getAll(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public TbUser getById(Long id) {
        return userRepository.findById(id).orElse(new TbUser());
    }

    @Override
    public TbUser update(TbUser entity) {
        return userRepository.save(entity);
    }

    @Override
    public Boolean delete(TbUser entity) {
        try {
            userRepository.delete(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
    public TbUser updateAll(UserRequestDTO dto, TbUser tbUser) {
        tbUser.setFullName(dto.getFullName());
        tbUser.setPhone(dto.getPhone());
        tbUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        return update(tbUser);
    }

    @Override
    public Boolean matchesPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }


}
