package com.fstack.phong_tro_fstack.leo.landlord.converter;

import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import com.fstack.phong_tro_fstack.leo.landlord.dto.AreaDTO;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaConverterLandLord implements Mapper<AreaEntity, AreaDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public AreaEntity toEntity(AreaDTO dto) {
    return modelMapper.map(dto, AreaEntity.class);
  }

  @Override
  public AreaDTO toDTO(AreaEntity entity) {
    return modelMapper.map(entity, AreaDTO.class);
  }

//  @Autowired
//  RoomCoverter roomCoverter;
// @Autowired
//  private  PostConverter postConverter;
//
//  public AreaEntity toEntity(AreaDTO areaDTO) {
//    AreaEntity areaEntity = new AreaEntity();
//    areaEntity.setId(areaDTO.getId());
//    areaEntity.setExactAddress(areaDTO.getExactAddress());
//    areaEntity.setLatitude(areaDTO.getLatitude());
//    areaEntity.setLongitude(areaDTO.getLongitude());
//    areaEntity.setName(areaDTO.getName());
//    areaEntity.setRoomEntities(roomCoverter.toListEntity(areaDTO.getRoomDTOList()));
//    areaEntity.setPostEntity(postConverter.toEntity(areaDTO.getPostDTO()));
//
//    return areaEntity;
//  }
//  public AreaDTO toDTO(AreaEntity areaEntity) {
//    AreaDTO areaDTO = new AreaDTO();
//    areaDTO.setId(areaEntity.getId());
//    areaDTO.setExactAddress(areaEntity.getExactAddress());
//    areaDTO.setLatitude(areaEntity.getLatitude());
//    areaDTO.setLongitude(areaEntity.getLongitude());
//    areaDTO.setName(areaEntity.getName());
//    areaDTO.setRoomDTOList(roomCoverter.toListDTO(areaEntity.getRoomEntities()));
//    areaDTO.setPostDTO(postConverter.toDTO(areaEntity.getPostEntity()));
//
//    return areaDTO;
//  }
//
//
//
  public List<AreaDTO> toListDTO(List<AreaEntity> list) {
    List<AreaDTO> areaDTOList = new ArrayList<>();
    for (AreaEntity areaEntity : list) {
      areaDTOList.add(toDTO(areaEntity));
    }
    return areaDTOList;
  }
}
