package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.base.dto.ProvinceDTO;
import com.fstack.phong_tro_fstack.base.entity.ProvinceEntity;
import com.fstack.phong_tro_fstack.client.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rest/province")
public class ProvinceAPI {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<ProvinceDTO> result =  provinceService.getAll();
        return ResponseEntity.ok(result);
    }
}
