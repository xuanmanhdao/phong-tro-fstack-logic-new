package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.dto.compostitekeydto.UserRoleKeyDTO;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.base.helper.Common;
import com.fstack.phong_tro_fstack.client.dto.UserDTO;
import com.fstack.phong_tro_fstack.client.output.UserRoleResponse;
import com.fstack.phong_tro_fstack.client.repository.UserRepository;
import com.fstack.phong_tro_fstack.client.service.UserRoleService;
import com.fstack.phong_tro_fstack.client.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserRoleService userRoleService;

  @Override
  public UserDTO findByEmailAndPassword(String email, String password) {
    List<Object[]> queryResults = userRepository.findByEmailAndPassword(email, password);
    UserDTO result = new UserDTO();
    for (Object[] objects : queryResults) {
      result.setId((Long) objects[0]);
      result.setEmail((String) objects[1]);
      result.setFullName((String) objects[2]);
    }

    return result;
  }

  @Override
  public UserRoleResponse save(UserDTO dto) {
    UserEntity checkEmailExist = userRepository.findByEmail(dto.getEmail());
    if (checkEmailExist == null) {
      UserEntity convertEntity = new UserEntity();
      convertEntity.setBalance((float) 0);
      convertEntity.setEmail(dto.getEmail());
      convertEntity.setFullName(dto.getFullName());
      convertEntity.setPassword(dto.getPassword());
      convertEntity.setCreatedAt(Common.getCurrenDate());
      UserEntity userEntity = userRepository.save(convertEntity);

      Long idUserAfterSave = userEntity.getId();
      Long idRole = 3L;
      UserRoleKeyDTO userRoleKeyDTO = new UserRoleKeyDTO(idUserAfterSave, idRole);

      UserRoleDTO userRoleDTO = new UserRoleDTO();
      userRoleDTO.setUserRoleKeyDTO(userRoleKeyDTO);
      userRoleDTO.setCreatedAt(Common.getCurrenDate());

      UserRoleDTO resultSaveRole = userRoleService.save(userRoleDTO);

      UserRoleResponse result = new UserRoleResponse();
      result.setIdUser(idUserAfterSave);
      result.setIdRole(idRole);
      result.setEmail(userEntity.getEmail());
      result.setFullName(userEntity.getFullName());
      result.setRoleName("Client");
      result.setCreatedAt(resultSaveRole.getCreatedAt());
      return result;
    }
    return null;
  }
}
