package com.fstack.phong_tro_fstack.base.converter;

import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.dto.compostitekeydto.UserRoleKeyDTO;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import com.fstack.phong_tro_fstack.base.entity.compositekey.UserRoleKey;
import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {
    public UserRoleEntity toEntity(UserRoleDTO userRoleDTO) {
        Long idUser= userRoleDTO.getUserRoleKeyDTO().getIdUser(); 
        Long idRole= userRoleDTO.getUserRoleKeyDTO().getIdRole();
        UserRoleKey userRoleKey= new UserRoleKey();
        userRoleKey.setIdUser(idUser);
        userRoleKey.setIdRole(idRole);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserRoleKey(userRoleKey);
        userRoleEntity.setCreatedAt(userRoleDTO.getCreatedAt());
        return userRoleEntity;
    }

    public UserRoleDTO toDTO(UserRoleEntity userRoleEntity) {
        Long idUser=userRoleEntity.getUserRoleKey().getIdUser();
        Long idRole=userRoleEntity.getUserRoleKey().getIdRole();
        UserRoleKeyDTO userRoleKeyDTO=new UserRoleKeyDTO();
        userRoleKeyDTO.setIdUser(idUser);
        userRoleKeyDTO.setIdRole(idRole);
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoleKeyDTO(userRoleKeyDTO);
        userRoleDTO.setCreatedAt(userRoleEntity.getCreatedAt());
        return userRoleDTO;
    }
}
