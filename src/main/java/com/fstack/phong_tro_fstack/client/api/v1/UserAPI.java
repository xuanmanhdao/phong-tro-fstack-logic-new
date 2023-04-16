package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.client.dto.UserDTO;
import com.fstack.phong_tro_fstack.client.service.UserService;
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

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserDTO dto) {
    UserDTO result = userService.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
    if (result.getId() == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    return ResponseEntity.ok(result);
  }
}
