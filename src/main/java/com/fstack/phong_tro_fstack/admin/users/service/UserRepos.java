package com.fstack.phong_tro_fstack.admin.users.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import com.fstack.phong_tro_fstack.base.entity.UserRoleEntity;

public interface UserRepos extends CrudRepository<UserEntity, Long> {
	
	@Query(value =  "SELECT u.id, u.email,u.full_name, r.id , r.name "
			+ "FROM user_role ur "
			+ "INNER JOIN user u ON u.id = ur.id_user "
			+ "INNER JOIN role r on r.id = ur.id_role" , nativeQuery = true)
	List<Object[]> findAllUserRole();


}
