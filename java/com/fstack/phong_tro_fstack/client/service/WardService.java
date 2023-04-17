package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.dto.WardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WardService {
    List<WardDTO> getAllByIdDistrict(String idDistrict);
}
