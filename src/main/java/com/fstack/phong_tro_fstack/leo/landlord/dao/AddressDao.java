package com.fstack.phong_tro_fstack.leo.landlord.dao;

import com.fstack.phong_tro_fstack.base.entity.DistrictEntity;
import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import com.fstack.phong_tro_fstack.base.entity.WardEntity;

import java.util.List;

public interface AddressDao {

    List<ProvinceEntity> getProvince();
    List<WardEntity> findByDistrictId(String districtId);
    List<DistrictEntity> findByProvinceId(String provinceId);

}
