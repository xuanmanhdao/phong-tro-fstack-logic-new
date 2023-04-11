package com.fstack.phong_tro_fstack.admin.users.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fstack.phong_tro_fstack.admin.users.moders.RoleDtoModel;
import com.fstack.phong_tro_fstack.admin.users.moders.UserDtoModel;
import com.fstack.phong_tro_fstack.admin.users.moders.UserLogin;
import com.fstack.phong_tro_fstack.admin.users.moders.UserLoginDTO;
import com.fstack.phong_tro_fstack.admin.users.service.UserService;
import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;
import com.fstack.phong_tro_fstack.client.service.UserRoleService;

@RestController
@RequestMapping("/user-role")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private UserRoleService userRoleService;
	
	@GetMapping("/getUserRole/id={id}")
	public UserDtoModel getUserRole(@PathVariable long id){
		return userService.getUserRole(id);
	}
	
	
	@GetMapping("/getAllUserRole")
	public List<UserDtoModel> getListUserRole(){
		return userService.getListUserRole();
	}
	
	@PostMapping("/save-user-role")
	public ResponseEntity<Object> saveUserRole(@RequestBody UserRoleDTO userRoleDTO){
		
//		 UserRoleDTO userRoleDTOAfterSave = userRoleService.save(userRoleDTO);
		if(userRoleService.save(userRoleDTO) != null) {
			return ResponseEntity.ok(userRoleDTO); 
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PostMapping("/login")
	public UserLoginDTO getUserLogin(@RequestBody UserLogin userLogin){
		
		
		return null;
	}
	
	
	
	

}
