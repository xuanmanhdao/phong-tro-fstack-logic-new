package com.fstack.phong_tro_fstack.leo.landlord.dao.impl;

import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import com.fstack.phong_tro_fstack.client.repository.RoomRepository;
import com.fstack.phong_tro_fstack.leo.landlord.dao.RoomDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoomDaoImpl implements RoomDao {

  @Autowired
  private RoomRepository roomRepository;

  @Override
  public void saveRoom(RoomEntity roomEntity) {
    roomRepository.save(roomEntity);
  }

  @Override
  public void updateRoom(RoomEntity roomEntity) {
    roomRepository.save(roomEntity);

  }

  @Override
  public RoomEntity getRoomById(long id) {
    return roomRepository.findById(id).get();
  }

  @Override
  public List<RoomEntity> getRoombyAreaId(long idArea) {
    return roomRepository.getRoombyAreaId(idArea);
  }
}
