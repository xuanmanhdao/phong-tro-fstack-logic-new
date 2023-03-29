package com.fstack.phong_tro_fstack.test.controller;

import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import com.fstack.phong_tro_fstack.test.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/test/user-role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public List<UserRoleEntity> getAll(){
        return userRoleService.getAll();
    }

    @PostMapping
    public UserRoleEntity save(@RequestBody UserRoleEntity userRoleEntity){
        return userRoleService.save(userRoleEntity);
    }

}
