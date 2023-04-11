package com.fstack.phong_tro_fstack.admin.recharge.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fstack.phong_tro_fstack.admin.recharge.model.RechargeDTO;
import com.fstack.phong_tro_fstack.admin.recharge.model.RechargeResponseDTO;
import com.fstack.phong_tro_fstack.admin.recharge.service.RechargeSevice;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;

@RestController
@RequestMapping("/user-bank")
public class RechargeController {
	
	@Autowired
	RechargeSevice rechargeSevice;
	
	
	@PostMapping("/get-user-bank")
	public RechargeResponseDTO getUserBankByEmail(@RequestBody JsonNode json) {
		 String email = json.get("email").asText();
		return rechargeSevice.getRechargeResponseDTO(email);
	}
	
	@PostMapping("/update-bank-acount/{id}")
	public ResponseEntity<Object> updateBankAcout(@PathVariable Long id, @RequestBody JsonNode json){
		
		 float bankAcout = json.get("backAcount").floatValue();
		
		Optional<UserEntity> user = rechargeSevice.getUserEmtity(id);
		

        if (!user.isPresent())
            return ResponseEntity.notFound().build();
		UserEntity userEntity = user.get();
		userEntity.setBalance(bankAcout);
		
		RechargeResponseDTO rechargeResponseDTO = new RechargeResponseDTO();
		rechargeResponseDTO.setCode(200);
		rechargeResponseDTO.setMessage("Thành công");
		rechargeResponseDTO.setRechargeDTO(new RechargeDTO(userEntity.getId(),userEntity.getFullName(), userEntity.getEmail(), userEntity.getBalance()));
		
		if(rechargeSevice.saveEntity(userEntity)!= null) {
			return ResponseEntity.ok(rechargeResponseDTO); 
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			
		
		
	}

}
