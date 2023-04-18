package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {
  List<UserRoleEntity> getAll();
  UserRoleDTO save (UserRoleDTO userRoleDTO);
}
