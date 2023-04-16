package com.fstack.phong_tro_fstack.leo.landlord.converter;

import com.fstack.phong_tro_fstack.base.dto.UserDTO;
import com.fstack.phong_tro_fstack.base.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverterLandLord implements Mapper<UserEntity, UserDTO> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public UserEntity toEntity(UserDTO dto) {
    return modelMapper.map(dto, UserEntity.class);
  }

  @Override
  public UserDTO toDTO(UserEntity entity) {
    return modelMapper.map(entity, UserDTO.class);
  }
}
