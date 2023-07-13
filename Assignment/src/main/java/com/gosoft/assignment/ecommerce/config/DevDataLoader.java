package com.gosoft.assignment.ecommerce.config;

import com.gosoft.assignment.ecommerce.model.entity.TbRole;
import com.gosoft.assignment.ecommerce.repo.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DevDataLoader implements ApplicationRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(roleRepository.count() == 0){
            roleRepository.save(new TbRole("user"));
        }
    }
}
