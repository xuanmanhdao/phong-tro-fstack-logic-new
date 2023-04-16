package com.fstack.phong_tro_fstack.admin.users.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fstack.phong_tro_fstack.base.entity.RoleEntity;


@Repository
public interface RoleRepos extends CrudRepository<RoleEntity, Long> {
	
	@Query(value = "SELECT r.id, r.name "
			+ "FROM user_role ur "
			+ "INNER JOIN role r ON ur.id_role = r.id "
			+ "WHERE ur.id_user = ?" , nativeQuery = true)
	List<Object[]> findByUserRole(long id);
	
}
