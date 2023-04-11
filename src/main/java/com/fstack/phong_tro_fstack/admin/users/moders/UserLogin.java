package com.fstack.phong_tro_fstack.admin.users.moders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

	private String email;
	private String password;
}
