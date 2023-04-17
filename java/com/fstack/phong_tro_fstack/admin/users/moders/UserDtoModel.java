package com.fstack.phong_tro_fstack.admin.users.moders;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoModel {
	
	private long id;
	
	private String email;
	
	private String fullName;
	
	private String phoneNumber;
	
	private List<RoleDtoModel> listRole;

}
