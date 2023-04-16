package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.client.dto.UserDTO;
import com.fstack.phong_tro_fstack.client.repository.UserRepository;
import com.fstack.phong_tro_fstack.client.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

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
}
