package com.fstack.phong_tro_fstack.admin.users.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;

public interface LoginRespos extends CrudRepository<UserEntity, Long>{
	
	@Query(value = "SELECT id FROM user WHERE email = ? AND password = ?", nativeQuery = true)
	Long findIDUser(String email, String pass);

}
