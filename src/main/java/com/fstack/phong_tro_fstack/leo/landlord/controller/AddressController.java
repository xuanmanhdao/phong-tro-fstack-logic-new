package com.fstack.phong_tro_fstack.leo.landlord.controller;


import com.fstack.phong_tro_fstack.base.dto.*;
import com.fstack.phong_tro_fstack.leo.landlord.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("leo")
public class AddressController {
  @Autowired
  @Qualifier("addressService")
  private AddressService addressService;

  //show address.
  @GetMapping("PostNews/province")
  public ResponseEntity<?> getAddress() {
    List<ProvinceDTO> result = addressService.getProvince();
    return ResponseEntity.ok(result);
  }


  @GetMapping("PostNews/district/{idProvince}")
  public ResponseEntity<?> getListDistrictByIdProvince(@PathVariable String idProvince) {

    List<DistrictDTO> result = addressService.getDistrict(idProvince);

    return ResponseEntity.ok(result);
  }

  @GetMapping("PostNews/ward/{idDistrict}")
  public ResponseEntity<?> getListWardByIdDistrict(@PathVariable String idDistrict) {
    List<WardDTO> result = addressService.getWard(idDistrict);
    return ResponseEntity.ok(result);
  }





}
