package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import com.fstack.phong_tro_fstack.client.repository.RoomRepository;
import com.fstack.phong_tro_fstack.client.service.RoomService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomServiceImpl implements RoomService {

  @Autowired
  private RoomRepository roomRepository;

  @Override
  public RoomDTO getRoomById(Long id) {
    Optional<RoomEntity> roomEntity = roomRepository.findById(id);
    RoomDTO result = new RoomDTO();
    result.setId(roomEntity.get().getId());
    result.setName(roomEntity.get().getName());
    result.setAcreage(roomEntity.get().getAcreage());
    result.setRentPrice(roomEntity.get().getRentPrice());
    result.setElectricService(roomEntity.get().getElectricService());
    result.setWaterService(roomEntity.get().getWaterService());
    result.setDescription(roomEntity.get().getDescription());
    result.setImage(roomEntity.get().getImage());
    result.setVideo(roomEntity.get().getVideo());
    return result;
  }
}
