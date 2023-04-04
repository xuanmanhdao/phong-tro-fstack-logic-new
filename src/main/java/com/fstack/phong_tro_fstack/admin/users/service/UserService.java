package com.fstack.phong_tro_fstack.admin.users.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fstack.phong_tro_fstack.admin.users.moders.RoleDtoModel;
import com.fstack.phong_tro_fstack.admin.users.moders.UserDtoModel;
import com.fstack.phong_tro_fstack.admin.users.moders.UserRoleDTO;
import com.fstack.phong_tro_fstack.base.entity.RoleEntity;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;

@Service
public class UserService {
	
	@Autowired
	UserRoleRepos userRoleRepos;
	
	@Autowired
	RoleRepos roleRepos;
	
	
	public UserDtoModel getUserRole(long id) {
		
		Optional<UserEntity> userEntity = userRoleRepos.findById(id);
		return getConverUserRoleDTO(userEntity.get());
		
	}
	
	private UserDtoModel getConverUserRoleDTO(UserEntity userEntity) {
		UserDtoModel userDtoModel = new UserDtoModel();
		userDtoModel.setId(userEntity.getId());
		userDtoModel.setFullName(userEntity.getFullName());
		userDtoModel.setEmail(userEntity.getEmail());
		userDtoModel.setListRole(listRoleDto(userEntity.getId()));
		return userDtoModel;
	}
	
//	private List<RoleDtoModel> getListRoleUser(long id){
//		List<RoleEntity> listRolEmtity = roleRepos.findByUserRole(id);
//		List<RoleDtoModel> listRoleDtoModels = new ArrayList<>();
//		for(int i=0 ;i<listRolEmtity.size();i++) {
//			RoleDtoModel roleDtoModel = new RoleDtoModel();
//			roleDtoModel.setIdRole(listRolEmtity.get(i).getId());
//			roleDtoModel.setNameRole(listRolEmtity.get(i).getName());		
//			listRoleDtoModels.add(roleDtoModel);
//		}
//		return listRoleDtoModels;
//	}
	
	private List<RoleDtoModel> listRoleDto(long id){
		
		List<Object[]> listObject = roleRepos.findByUserRole(id);
		List<RoleDtoModel> listRoleDtoModels = new ArrayList<>();
		for(Object[] result : listObject) {
			//RoleEntity roleEntity = (RoleEntity) result[0]; // chể
			
			RoleDtoModel roleDtoModel = new RoleDtoModel();
			roleDtoModel.setIdRole((Long) result[0]);
			roleDtoModel.setNameRole(result[1].toString());
			listRoleDtoModels.add(roleDtoModel);
		}
		return listRoleDtoModels;
	}
	
	
	////
	
	public List<UserDtoModel> getListUserRole(){
		List<Object[]> listObject = userRoleRepos.findAllUserRole();
		Map<Long, UserDtoModel> map = new HashMap<>();
		for(Object[] result : listObject) {
			Long id = (Long) result[0];
			if(map.containsKey(id)) {// 
//				map.put(null, null)

				RoleDtoModel roleDtoModel = new RoleDtoModel();
				roleDtoModel.setIdRole((Long)result[3]);
				roleDtoModel.setNameRole(result[4].toString());
				map.get(id).getListRole().add(roleDtoModel);
				
			}else {
				List<RoleDtoModel> listRoleDTO = new ArrayList<>();
				listRoleDTO.add(new RoleDtoModel((Long)result[3],result[4].toString() ));
				map.put(id, new UserDtoModel(id,result[1].toString(),result[2].toString(), listRoleDTO ));
			}
		}
		
		List<UserDtoModel> listTRR = new ArrayList<>(map.values());
		
		
		
		
		return listTRR;
		
	}
	
	
	
	
	
	
	
	
	

}
