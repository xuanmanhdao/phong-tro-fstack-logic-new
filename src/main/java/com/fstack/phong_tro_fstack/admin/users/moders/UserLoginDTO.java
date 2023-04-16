package com.fstack.phong_tro_fstack.admin.users.moders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
	
	
	private int code;
	
	private UserDtoModel userDtoModel;

}
