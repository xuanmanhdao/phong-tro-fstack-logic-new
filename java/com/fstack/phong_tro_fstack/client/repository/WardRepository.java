package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.WardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<WardEntity, String> {
    @Query("Select new WardEntity(w.id, w.name, w.type) From WardEntity w " +
            "where w.districtEntity.id=:idDistrict")
    List<WardEntity> findAllByDistrictId(@Param("idDistrict") String idDistrict);
}
