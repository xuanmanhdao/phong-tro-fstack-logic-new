package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.dto.ProvinceDTO;
import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvinceService {
    List<ProvinceDTO> getAll();
}
