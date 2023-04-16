package com.fstack.phong_tro_fstack.leo.landlord.service.impl;

import com.fstack.phong_tro_fstack.base.converter.DistrictConverter;
import com.fstack.phong_tro_fstack.base.converter.ProvinceConverter;
import com.fstack.phong_tro_fstack.base.converter.WardConverter;
import com.fstack.phong_tro_fstack.base.dto.DistrictDTO;
import com.fstack.phong_tro_fstack.base.dto.ProvinceDTO;
import com.fstack.phong_tro_fstack.base.dto.WardDTO;
import com.fstack.phong_tro_fstack.base.entity.DistrictEntity;
import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import com.fstack.phong_tro_fstack.base.entity.WardEntity;
import com.fstack.phong_tro_fstack.leo.landlord.dao.AddressDao;
import com.fstack.phong_tro_fstack.leo.landlord.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Qualifier("addressService")
public class AddressServiceImplement implements AddressService {

    @Autowired
    @Qualifier("addressDao")
    private AddressDao addressDao;
    @Autowired
    private ProvinceConverter provinceConverter;
    @Autowired
    private DistrictConverter districtConverter;
    @Autowired
    private WardConverter wardConverter;

    @Override
    public List<ProvinceDTO> getProvince() {
        List<ProvinceDTO> provinceDTOList = new ArrayList<>();
        for (ProvinceEntity province : addressDao.getProvince()) {
            ProvinceDTO provinceDTO = provinceConverter.toDTO(province);
            provinceDTOList.add(provinceDTO);
        }
        return provinceDTOList;
    }

    @Override
    public List<WardDTO> getWard(String id) {
        List<WardDTO> wardDTOList = new ArrayList<>();
        for (WardEntity ward : addressDao.findByDistrictId(id)) {
            WardDTO wardDTO = wardConverter.toDTO(ward);
            wardDTOList.add(wardDTO);
        }
        return wardDTOList;
    }

    @Override
    public List<DistrictDTO> getDistrict(String id) {
        List<DistrictDTO> districtDTOList = new ArrayList<>();
        for (DistrictEntity district : addressDao.findByProvinceId(id)) {
            DistrictDTO districtDTO = districtConverter.toDTO(district);
            districtDTOList.add(districtDTO);
        }
        return districtDTOList;
    }
}
