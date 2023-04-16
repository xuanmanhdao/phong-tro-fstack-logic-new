package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
  RoomDTO getRoomById(Long id);
}
