package com.fstack.phong_tro_fstack.admin.personnal.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fstack.phong_tro_fstack.admin.personnal.models.PersonnalDTO;
import com.fstack.phong_tro_fstack.admin.personnal.models.PersonnalRequest;
import com.fstack.phong_tro_fstack.admin.personnal.services.PersonnalService;
import com.fstack.phong_tro_fstack.admin.users.service.UserService;
import com.fstack.phong_tro_fstack.base.dto.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.dto.compostitekeydto.UserRoleKeyDTO;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.client.service.UserRoleService;

@RestController
@RequestMapping("personnal")
public class PersonnalController {
	
	
	@Autowired
	PersonnalService personnalService;
	
	 @Autowired
	 private UserRoleService userRoleService;
	
	@GetMapping("/get-personanl")
	public List<PersonnalDTO> getListPersonnal(){
		
		return personnalService.getPersonnal();
	}
	
	@PostMapping("/save-personnal")
	public ResponseEntity<?> savePersonnal(@RequestBody PersonnalRequest personnalRequest){
		
		Date date = new Date();
		
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(personnalRequest.getEmail());
		userEntity.setFullName(personnalRequest.getFullName());
		userEntity.setBalance((float) 0);
		userEntity.setCreatedAt(date);
		userEntity.setPhoneNumber(personnalRequest.getPhone());
		userEntity.setIdCard(personnalRequest.getIdCard());
		userEntity.setPassword("123456");
		
		UserEntity userEntitySave = personnalService.saveUserEntity(userEntity);
		if(userEntitySave != null) {
			
			for(int i =0 ; i< personnalRequest.getListRole().size();i++) {
				UserRoleDTO userRoleDTO = new UserRoleDTO();
				userRoleDTO.setUserRoleKeyDTO(new UserRoleKeyDTO(userEntitySave.getId(), personnalRequest.getListRole().get(i).getId()));
				userRoleService.save(userRoleDTO);
			}
			return ResponseEntity.ok(userEntitySave);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}

}
