package com.fstack.phong_tro_fstack.admin.personnal.reponse;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fstack.phong_tro_fstack.admin.personnal.models.PersonnalDTO;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;

public interface PersonnalReponse extends CrudRepository<UserEntity, Long>{
	
	@Query(value = "SELECT u.id, u.email,u.full_name, r.id , r.name, u.phone_number, u.balance FROM user_role ur INNER JOIN user u ON u.id = ur.id_user INNER JOIN role r on r.id = ur.id_role", nativeQuery = true)
	List<Object[]> findAllPersonnal();

}
