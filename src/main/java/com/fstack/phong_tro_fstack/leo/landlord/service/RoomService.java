package com.fstack.phong_tro_fstack.leo.landlord.service;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import java.util.List;

public interface RoomService {

  RoomDTO saveRoom(RoomDTO roomDTO);

  RoomDTO getRoom(long id);

  RoomDTO updateRoom(RoomDTO roomDTO, long id);

  List<RoomDTO> getRoombyAreaId(long idArea);
}
