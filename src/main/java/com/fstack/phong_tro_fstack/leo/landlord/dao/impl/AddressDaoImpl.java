package com.fstack.phong_tro_fstack.leo.landlord.dao.impl;

import com.fstack.phong_tro_fstack.base.entity.DistrictEntity;
import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import com.fstack.phong_tro_fstack.base.entity.WardEntity;
import com.fstack.phong_tro_fstack.client.repository.DistrictRepository;
import com.fstack.phong_tro_fstack.client.repository.ProvinceRepository;
import com.fstack.phong_tro_fstack.client.repository.WardRepository;
import com.fstack.phong_tro_fstack.leo.landlord.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@Qualifier("addressDao")
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public List<ProvinceEntity> getProvince() {
        return new ArrayList<>(provinceRepository.findAll());
    }

    @Override
    public List<WardEntity> findByDistrictId(String districtId) {
        return wardRepository.findAllByDistrictId(districtId);
    }

    @Override
    public List<DistrictEntity> findByProvinceId(String provinceId) {
        return districtRepository.findAllByProvinceId(provinceId);

    }


}
