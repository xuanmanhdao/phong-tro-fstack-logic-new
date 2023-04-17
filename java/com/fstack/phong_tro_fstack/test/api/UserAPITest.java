package com.fstack.phong_tro_fstack.test.api;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.test.dto.UserDTO;
import com.fstack.phong_tro_fstack.test.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/test/user")
public class UserAPITest {
    @Autowired
    private UserServiceTest userServiceTest;

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        List<UserEntity> result = userServiceTest.findAllUsers();
        return ResponseEntity.ok(result);
    }
}
