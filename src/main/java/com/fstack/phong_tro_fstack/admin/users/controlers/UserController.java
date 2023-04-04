package com.fstack.phong_tro_fstack.admin.users.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fstack.phong_tro_fstack.admin.users.moders.RoleDtoModel;
import com.fstack.phong_tro_fstack.admin.users.moders.UserDtoModel;
import com.fstack.phong_tro_fstack.admin.users.moders.UserRoleDTO;
import com.fstack.phong_tro_fstack.admin.users.service.UserService;

@RestController
@RequestMapping("/user-role")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public UserDtoModel getUserRole(@PathVariable long id){
		return userService.getUserRole(id);
	}
	
	
	@GetMapping("/abc")
	public List<UserDtoModel> getListUserRole(){
		return userService.getListUserRole();
	}

}
