package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.client.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  UserDTO findByEmailAndPassword(String email, String password);
}
