package com.fstack.phong_tro_fstack.leo.landlord.controller;


import com.fstack.phong_tro_fstack.base.converter.PostConverter;
import com.fstack.phong_tro_fstack.base.dto.*;
import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import com.fstack.phong_tro_fstack.leo.landlord.controller.ServiceFileItemAPI.GetJsonImage;
import com.fstack.phong_tro_fstack.leo.landlord.service.AddressService;
import com.fstack.phong_tro_fstack.leo.landlord.service.AreaService;
import com.fstack.phong_tro_fstack.leo.landlord.service.PostService;
import com.fstack.phong_tro_fstack.leo.landlord.service.RoomService;
import com.fstack.phong_tro_fstack.leo.landlord.service.UserService;
import com.fstack.phong_tro_fstack.leo.landlord.service.impl.AreaServiceImplement;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
