package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.converter.UserRoleConverter;
import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.entity.RoleEntity;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import com.fstack.phong_tro_fstack.base.helper.Common;
import com.fstack.phong_tro_fstack.client.repository.RoleRepository;
import com.fstack.phong_tro_fstack.client.repository.UserRepository;
import com.fstack.phong_tro_fstack.client.repository.UserRoleRepository;
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

    @Autowired
    private UserRoleConverter userRoleConverter;

    public List<UserRoleEntity> getAll() {
        return userRoleRepository.findAll();
    }

    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
        UserRoleEntity userRoleEntity = userRoleConverter.toEntity(userRoleDTO);
        UserEntity userEntity = userRepository.findOneById(userRoleDTO.getUserRoleKeyDTO().getIdUser());
        RoleEntity roleEntity = roleRepository.findOneById(userRoleDTO.getUserRoleKeyDTO().getIdRole());
        userRoleEntity.setUserEntity(userEntity);
        userRoleEntity.setRoleEntity(roleEntity);
        userRoleEntity.setCreatedAt(Common.getCurrenDate());

        UserRoleEntity userRoleEntityAfterSave = userRoleRepository.save(userRoleEntity);
        return userRoleConverter.toDTO(userRoleEntityAfterSave);
    }
}
