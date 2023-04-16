package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.base.dto.DistrictDTO;
import com.fstack.phong_tro_fstack.client.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rest/district")
public class DistrictAPI {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/province/{idProvince}")
    public ResponseEntity<?> getListDistrictByIdProvince(@PathVariable String idProvince){
        System.out.println(idProvince);
        List<DistrictDTO> result= districtService.getAllByIdProvince(idProvince);
        return ResponseEntity.ok(result);
    }

}
