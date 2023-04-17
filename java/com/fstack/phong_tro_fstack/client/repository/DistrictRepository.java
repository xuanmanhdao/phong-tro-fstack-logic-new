package com.fstack.phong_tro_fstack.client.repository;

import com.fstack.phong_tro_fstack.base.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, String> {
    @Query("Select new DistrictEntity(d.id, d.name, d.type) From DistrictEntity d " +
            "where d.provinceEntity.id=:idProvince")
    List<DistrictEntity> findAllByProvinceId(@Param("idProvince") String idProvince);
}
