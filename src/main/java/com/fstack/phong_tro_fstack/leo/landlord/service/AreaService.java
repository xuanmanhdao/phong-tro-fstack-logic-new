package com.fstack.phong_tro_fstack.leo.landlord.service;

import com.fstack.phong_tro_fstack.leo.landlord.dto.AreaDTO;
import java.util.List;

public interface AreaService {

  AreaDTO saveArea(AreaDTO areaDTO);

  AreaDTO getArea(long id);

  AreaDTO updateArea(AreaDTO areaDTO, long id);

  Long saveAreaAndGetId(AreaDTO areaDTO);

  List<AreaDTO> getAllArea();
  AreaDTO getAreaByPostId(long idPost);
}
