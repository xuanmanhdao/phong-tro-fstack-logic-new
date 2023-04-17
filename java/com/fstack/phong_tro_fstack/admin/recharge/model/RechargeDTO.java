package com.fstack.phong_tro_fstack.admin.recharge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeDTO {
	
	private Long id;
	private String nameUser;
	private String email;
	private float bank_acount;
	

}
