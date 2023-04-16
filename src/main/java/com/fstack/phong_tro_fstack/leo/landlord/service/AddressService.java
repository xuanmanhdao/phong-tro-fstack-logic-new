package com.fstack.phong_tro_fstack.leo.landlord.service;

import com.fstack.phong_tro_fstack.base.dto.DistrictDTO;
import com.fstack.phong_tro_fstack.base.dto.ProvinceDTO;
import com.fstack.phong_tro_fstack.base.dto.WardDTO;

import java.util.List;

public interface AddressService {
    List<ProvinceDTO> getProvince();
    List<WardDTO> getWard(String id);
    List<DistrictDTO> getDistrict(String id);

}
