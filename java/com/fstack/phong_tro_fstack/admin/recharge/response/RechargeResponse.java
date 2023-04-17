package com.fstack.phong_tro_fstack.admin.recharge.response;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;

public interface RechargeResponse extends CrudRepository<UserEntity, Long> {

	@Query(value = "SELECT id, email,full_name,balance FROM user WHERE email = ?",  nativeQuery = true)
	Object findByEmail(String email);
}
