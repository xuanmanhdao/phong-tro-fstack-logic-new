package com.fstack.phong_tro_fstack.admin.recharge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fstack.phong_tro_fstack.admin.recharge.model.RechargeDTO;
import com.fstack.phong_tro_fstack.admin.recharge.model.RechargeResponseDTO;
import com.fstack.phong_tro_fstack.admin.recharge.response.RechargeResponse;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;

@Service
public class RechargeSevice {
	
	@Autowired
	RechargeResponse rechargeResponse;

	
	private RechargeDTO getUserByEmail(String email) {
		RechargeDTO rechargeDTO = new RechargeDTO();
		Object object = rechargeResponse.findByEmail(email);
		if(object == null) {
			rechargeDTO=null;
		}else {
			Object[] objArr = (Object[]) object;
		    
		    rechargeDTO.setId((Long) objArr[0]);
		    rechargeDTO.setEmail((String) objArr[1]);
		    rechargeDTO.setNameUser((String) objArr[2]);
		    rechargeDTO.setBank_acount((float) objArr[3]);
		}
		
		 return rechargeDTO;
		
	}
	
	public RechargeResponseDTO getRechargeResponseDTO(String email) {
		
		
		RechargeResponseDTO rechargeResponseDTO = new RechargeResponseDTO();
		if(getUserByEmail(email) == null) {
			rechargeResponseDTO.setCode(402);
			rechargeResponseDTO.setRechargeDTO(null);
			rechargeResponseDTO.setMessage("Không tồn tại email");
		}else {
			rechargeResponseDTO.setCode(200);
			rechargeResponseDTO.setRechargeDTO(getUserByEmail(email));
			rechargeResponseDTO.setMessage("Thành công");
		}
		return rechargeResponseDTO;
	}
	
	public  Optional<UserEntity> getUserEmtity(Long id){
		
		return rechargeResponse.findById(id);
		
	}
	
	public UserEntity saveEntity(UserEntity userEntity) {
		
		return rechargeResponse.save(userEntity);
		
		
	}
}
