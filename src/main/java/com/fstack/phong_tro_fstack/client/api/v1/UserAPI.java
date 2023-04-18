package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.client.dto.UserDTO;
import com.fstack.phong_tro_fstack.client.output.UserRoleResponse;
import com.fstack.phong_tro_fstack.client.service.UserRoleService;
import com.fstack.phong_tro_fstack.client.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rest/user")
public class UserAPI {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRoleService userRoleService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserDTO dto) {
    UserDTO result = userService.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
    if (result.getId() == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    return ResponseEntity.ok(result);
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDTO dto) {
    UserRoleResponse result = userService.save(dto);
    if (result != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Tạo tài khoản thất bại");
  }

  @AllArgsConstructor
  class Message {
    private String message;
  }
}
