package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.base.converter.ProvinceConverter;
import com.fstack.phong_tro_fstack.base.dto.ProvinceDTO;
import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import com.fstack.phong_tro_fstack.client.repository.ProvinceRepository;
import com.fstack.phong_tro_fstack.client.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private ProvinceConverter provinceConverter;

    @Override
    public List<ProvinceDTO> getAll() {
        List<ProvinceDTO> result = new ArrayList<>();

        List<ProvinceEntity> provinceEntities = provinceRepository.findAll();
        for (ProvinceEntity provinceEntity : provinceEntities) {
            result.add(provinceConverter.toDTO(provinceEntity));
        }
        return result;
    }
}
