package com.fstack.phong_tro_fstack.leo.landlord.converter;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomCoverterLandLord implements Mapper<RoomEntity, RoomDTO> {

  @Autowired
  private ModelMapper modelMapper;

  public RoomDTO toDTO(RoomEntity entity) {
    return modelMapper.map(entity, RoomDTO.class);
  }

  public RoomEntity toEntity(RoomDTO dto) {
    return modelMapper.map(dto, RoomEntity.class);
  }

  public List<RoomEntity> toListEntity(List<RoomDTO> roomDTOs) {
    List<RoomEntity> roomEntityList = new ArrayList<>();
    if (roomDTOs != null) {
      for (RoomDTO roomDTO : roomDTOs) {
        roomEntityList.add(toEntity(roomDTO));
      }
    }
    return roomEntityList;
  }

  public List<RoomDTO> toListDTO(List<RoomEntity> roomEntitys) {
    List<RoomDTO> roomDTOList = new ArrayList<>();
    if (roomEntitys != null) {
      for (RoomEntity roomEntity : roomEntitys) {
        roomDTOList.add(toDTO(roomEntity));
      }
    }
    return roomDTOList;
  }
}
