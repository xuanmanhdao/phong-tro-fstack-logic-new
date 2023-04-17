package com.fstack.phong_tro_fstack.admin.users.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fstack.phong_tro_fstack.admin.users.moders.RoleDtoModel;
import com.fstack.phong_tro_fstack.admin.users.moders.UserDtoModel;
import com.fstack.phong_tro_fstack.base.entity.RoleEntity;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;

@Service
public class UserService {
	
	@Autowired
	UserRepos userRepos;
	
	@Autowired
	RoleRepos roleRepos;
	
	@Autowired
	UserRoleRepos userRoleRepos;
	
	@Autowired
	LoginRespos loginRespos;
	
	
	public Long getId(String email, String pass) {
		return loginRespos.findIDUser(email, pass);
	}
	
	// lấy ra user với từng role tương ứng
	public UserDtoModel getUserRole(long id) {
		
		Optional<UserEntity> userEntity = userRepos.findById(id);
		return getConverUserRoleDTO(userEntity.get());
		
	}
	// lấy ra user với danh sách uer
	private UserDtoModel getConverUserRoleDTO(UserEntity userEntity) {
		UserDtoModel userDtoModel = new UserDtoModel();
		userDtoModel.setId(userEntity.getId());
		userDtoModel.setFullName(userEntity.getFullName());
		userDtoModel.setEmail(userEntity.getEmail());
		userDtoModel.setListRole(listRoleDto(userEntity.getId()));
		return userDtoModel;
	}
	// lấy ra các role của user
	private List<RoleDtoModel> listRoleDto(long id){
		
		List<Object[]> listObject = roleRepos.findByUserRole(id);
		List<RoleDtoModel> listRoleDtoModels = new ArrayList<>();
		for(Object[] result : listObject) {
	
			RoleDtoModel roleDtoModel = new RoleDtoModel();
			roleDtoModel.setIdRole((Long) result[0]);
			roleDtoModel.setNameRole(result[1].toString());
			listRoleDtoModels.add(roleDtoModel);
		}
		return listRoleDtoModels;
	}
	
	
	//// trả về danh sách list user với role
	
	public List<UserDtoModel> getListUserRole(){
		List<Object[]> listObject = userRepos.findAllUserRole();
		Map<Long, UserDtoModel> map = new HashMap<>();
		for(Object[] result : listObject) {
			Long id = (Long) result[0];
			if(map.containsKey(id)) {// 
				RoleDtoModel roleDtoModel = new RoleDtoModel();
				roleDtoModel.setIdRole((Long)result[3]);
				roleDtoModel.setNameRole(result[4].toString());
				map.get(id).getListRole().add(roleDtoModel);
				
			}else {
				List<RoleDtoModel> listRoleDTO = new ArrayList<>();
				listRoleDTO.add(new RoleDtoModel((Long)result[3],result[4].toString() ));
				map.put(id, new UserDtoModel(id,result[1].toString(),result[2].toString(), result[5].toString(), listRoleDTO ));
			}
		}
		
		List<UserDtoModel> listTRR = new ArrayList<>(map.values());
		
		return listTRR;
	}
	
	
	
	public UserRoleEntity insertUserRole(UserRoleEntity userRoleEntity) {
//		 LocalDateTime currentTime = LocalDateTime.now();
		 
		 Date dateNow = new Date();
		 userRoleEntity.setCreatedAt(dateNow);
		UserRoleEntity userRoleSave = userRoleRepos.save(userRoleEntity);
		return userRoleSave;
	}
}
