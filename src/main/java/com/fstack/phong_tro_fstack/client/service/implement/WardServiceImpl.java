package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.base.converter.WardConverter;
import com.fstack.phong_tro_fstack.base.dto.WardDTO;
import com.fstack.phong_tro_fstack.base.entity.WardEntity;
import com.fstack.phong_tro_fstack.client.repository.WardRepository;
import com.fstack.phong_tro_fstack.client.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private WardConverter wardConverter;


    @Override
    public List<WardDTO> getAllByIdDistrict(String idDistrict) {
        List<WardDTO> result = new ArrayList<>();

        List<WardEntity> wardEntities = wardRepository.findAllByDistrictId(idDistrict);
        for (WardEntity wardEntity : wardEntities) {
            result.add(wardConverter.toDTO(wardEntity));
        }

        return result;
    }
}
