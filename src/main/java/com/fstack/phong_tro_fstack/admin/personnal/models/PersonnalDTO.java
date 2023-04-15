package com.fstack.phong_tro_fstack.admin.personnal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonnalDTO {
	
	private Long id;
	
	private String email;
	
	private String fullName;
	
	private float bankAccount;
	
	private String phoneNumber;
	
	private long idRole;
	
	private String nameRole;

}
