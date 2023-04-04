package com.fstack.phong_tro_fstack.admin.users.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fstack.phong_tro_fstack.base.entity.RoleEntity;


//@Repository
//public interface RoleJPARepos extends JpaRepository<RoleEntity, Long>{
//	
//	@Query("Select new RoleEntity(r.id, r.name) From RoleEntity r "
//			+ "" +
//            "where d.provinceEntity.id=:idProvince")
//    List<RoleEntity> findAllByProvinceId(@Param("idProvince") String idProvince);
//
//}
