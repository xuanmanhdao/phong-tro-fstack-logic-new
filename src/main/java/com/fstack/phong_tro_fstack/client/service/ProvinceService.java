package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import com.fstack.phong_tro_fstack.client.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public List<ProvinceEntity> getAll(){
//        return provinceRepository.findAllExceptTypeByMe();
        return provinceRepository.findAll();
    }
}
