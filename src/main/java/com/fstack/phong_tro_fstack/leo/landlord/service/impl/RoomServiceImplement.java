package com.fstack.phong_tro_fstack.leo.landlord.service.impl;

import com.fstack.phong_tro_fstack.base.converter.AreaConverter;
import com.fstack.phong_tro_fstack.base.converter.RoomCoverter;
import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import com.fstack.phong_tro_fstack.leo.landlord.dao.RoomDao;
import com.fstack.phong_tro_fstack.leo.landlord.service.AddressService;
import com.fstack.phong_tro_fstack.leo.landlord.service.AreaService;
import com.fstack.phong_tro_fstack.leo.landlord.service.RoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Qualifier("roomService")
public class RoomServiceImplement implements RoomService {


  @Autowired
  private RoomDao roomDao;
  @Autowired
  private RoomCoverter roomCoverter;
  @Autowired
  @Qualifier("areaService")
  @Lazy
  AreaService areaService;
  @Autowired
  AreaConverter areaConverter;

  @Override
  public RoomDTO saveRoom(RoomDTO roomDTO) {
    if (roomDTO != null) {
      AreaEntity areaEntity = areaConverter.toEntity(areaService.getArea(roomDTO.getIdArea()));
      RoomEntity roomEntity = roomCoverter.toEntity(roomDTO);
      roomEntity.setAreaEntity(areaEntity);
      roomDao.saveRoom(roomEntity);
    }
    return roomDTO;
  }

  @Override
  public RoomDTO getRoom(long id) {
    RoomEntity roomEntity = roomDao.getRoomById(id);
    return roomCoverter.toDTO(roomEntity);
  }

  @Override
  public RoomDTO updateRoom(RoomDTO roomDTO, long id) {
    if (roomDTO != null && id < 0) {
      RoomEntity roomEntity = roomDao.getRoomById(id);
      if (roomEntity != null) {
        roomDTO = roomCoverter.toDTO(roomEntity);
      }
    }
    return roomDTO;
  }

  @Override
  public List<RoomDTO> getRoombyAreaId(long idArea) {
    return roomCoverter.toListDTO(roomDao.getRoombyAreaId(idArea));
  }
}
