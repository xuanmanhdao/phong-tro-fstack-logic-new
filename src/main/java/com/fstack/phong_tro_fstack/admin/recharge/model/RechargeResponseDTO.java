package com.fstack.phong_tro_fstack.admin.recharge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeResponseDTO {
	
	private int code;
	
	private String message;
	
	private RechargeDTO rechargeDTO;

}
