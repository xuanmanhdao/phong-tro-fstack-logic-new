package com.fstack.phong_tro_fstack.admin.personnal.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fstack.phong_tro_fstack.admin.personnal.models.PersonnalDTO;
import com.fstack.phong_tro_fstack.admin.personnal.reponse.PersonnalReponse;
import com.fstack.phong_tro_fstack.admin.users.moders.RoleDtoModel;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;

@Service
public class PersonnalService {
	
	@Autowired
	PersonnalReponse personnalService;
	
	public List<PersonnalDTO> getPersonnal(){
		
		List<Object[]> listObject = personnalService.findAllPersonnal();
		List<PersonnalDTO> listPersonnalDTOs = new ArrayList();
		
		Map<Long , PersonnalDTO> map = new HashMap<>();
		
		
		for(Object[] result : listObject) {
			
			Long id = (Long) result[0];
			
			PersonnalDTO PersonnalDTO = new PersonnalDTO();
			PersonnalDTO.setId((Long) result[0]);
			PersonnalDTO.setEmail(result[1].toString());
			PersonnalDTO.setFullName(result[2].toString());
			PersonnalDTO.setIdRole((Long) result[3]);
			PersonnalDTO.setNameRole(result[4].toString());
			PersonnalDTO.setPhoneNumber(result[5] != null ? result[5].toString() : "");
			PersonnalDTO.setBankAccount( (Float) result[6]);
//			if(map.containsKey(id)) {
//				if(map.get(id).getNameRole()=="ADMIN_HT") {
//					map.get(id).setNameRole("ADMIN_HT");
//				}else {
//					
//				}
//			}
			listPersonnalDTOs.add(PersonnalDTO);
		}
		
		return listPersonnalDTOs;
		
	}
	
	public UserEntity saveUserEntity(UserEntity userEntity) {
		
		UserEntity userEntitySave = personnalService.save(userEntity);
		return userEntitySave;
	}
	
	

}
