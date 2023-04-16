package com.fstack.phong_tro_fstack.leo.landlord.converter;

import com.fstack.phong_tro_fstack.base.dto.ProvinceDTO;
import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import org.springframework.stereotype.Component;

@Component
public class ProvinceConverterLandLord {
    public ProvinceEntity toEntity(ProvinceDTO provinceDTO){
        ProvinceEntity provinceEntity=new ProvinceEntity();
        provinceEntity.setId(provinceDTO.getId());
        provinceEntity.setName(provinceDTO.getName());
        provinceEntity.setType(provinceDTO.getType());
        return provinceEntity;
    }

    public ProvinceDTO toDTO(ProvinceEntity provinceEntity){
        ProvinceDTO provinceDTO=new ProvinceDTO();
        provinceDTO.setId(provinceEntity.getId());
        provinceDTO.setName(provinceEntity.getName());
        provinceDTO.setType(provinceEntity.getType());
        return provinceDTO;
    }
}
