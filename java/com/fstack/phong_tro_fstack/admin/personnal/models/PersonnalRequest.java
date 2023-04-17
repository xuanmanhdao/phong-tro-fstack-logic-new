package com.fstack.phong_tro_fstack.admin.personnal.models;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonnalRequest {

	 private String email;
	 private String fullName;
	 private String idCard;
	 private String phone;
	 private List<RoleDTO> listRole;
	
}
