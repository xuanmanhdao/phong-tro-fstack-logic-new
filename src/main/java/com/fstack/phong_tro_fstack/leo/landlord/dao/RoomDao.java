package com.fstack.phong_tro_fstack.leo.landlord.dao;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import java.util.List;

public interface RoomDao {

  void saveRoom(RoomEntity roomEntity);

  void updateRoom(RoomEntity roomEntity);

  RoomEntity getRoomById(long id);

  List<RoomEntity> getRoombyAreaId(long idArea);

}
