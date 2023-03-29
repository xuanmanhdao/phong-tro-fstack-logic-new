package com.fstack.phong_tro_fstack.test.service;

import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import com.fstack.phong_tro_fstack.base.helper.Common;
import com.fstack.phong_tro_fstack.test.repository.RoleRepository;
import com.fstack.phong_tro_fstack.test.repository.UserRepository;
import com.fstack.phong_tro_fstack.test.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserRoleEntity> getAll(){
        return userRoleRepository.findAll();
    }

    public UserRoleEntity save(UserRoleEntity userRoleEntity){
        userRoleEntity.setUserEntity(userRepository.findOneById(userRoleEntity.getUserRoleKey().getIdUser()));
        userRoleEntity.setRoleEntity(roleRepository.findOneById(userRoleEntity.getUserRoleKey().getIdRole()));
        userRoleEntity.setCreatedAt(Common.getCurrenDate());
        return userRoleRepository.save(userRoleEntity);
    }
}
