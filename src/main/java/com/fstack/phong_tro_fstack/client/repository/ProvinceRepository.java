package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, String> {
    //    @Query("SELECT new ProvinceEntity(p.id, p.name) FROM ProvinceEntity p")
//    List<ProvinceEntity> findAllExceptTypeByMe();

}
