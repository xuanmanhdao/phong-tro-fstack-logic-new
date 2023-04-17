package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.base.dto.WardDTO;
import com.fstack.phong_tro_fstack.client.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rest/ward")
public class WardAPI {
    @Autowired
    private WardService wardService;

    @GetMapping("/district/{idDistrict}")
    public ResponseEntity<?> getListWardByIdDistrict(@PathVariable String idDistrict) {
        System.out.println(idDistrict);
        List<WardDTO> result = wardService.getAllByIdDistrict(idDistrict);
        return ResponseEntity.ok(result);
    }
}
