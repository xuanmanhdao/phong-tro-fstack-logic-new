package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.base.converter.DistrictConverter;
import com.fstack.phong_tro_fstack.base.dto.DistrictDTO;
import com.fstack.phong_tro_fstack.base.entity.DistrictEntity;
import com.fstack.phong_tro_fstack.client.repository.DistrictRepository;
import com.fstack.phong_tro_fstack.client.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictConverter districtConverter;

    @Override
    public List<DistrictDTO> getAllByIdProvince(String idProvince) {
        List<DistrictDTO> result = new ArrayList<>();

        List<DistrictEntity> districtEntities = districtRepository.findAllByProvinceId(idProvince);
        for (DistrictEntity districtEntity : districtEntities) {
            result.add(districtConverter.toDTO(districtEntity));
        }
        return result;
    }
}
