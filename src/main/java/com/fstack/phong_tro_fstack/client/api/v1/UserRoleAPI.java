package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import com.fstack.phong_tro_fstack.client.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rest/user-role")
public class UserRoleAPI {
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public List<UserRoleEntity> getAll() {
        return userRoleService.getAll();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserRoleDTO userRoleDTO) {
        UserRoleDTO userRoleDTOAfterSave = userRoleService.save(userRoleDTO);
        // Trả về response với STATUS CODE = 200 (OK)
        // Body sẽ chứa thông tin về đối tượng.
        return ResponseEntity.ok().body(userRoleDTOAfterSave);
    }
}
