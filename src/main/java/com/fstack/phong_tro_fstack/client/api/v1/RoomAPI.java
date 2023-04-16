package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.client.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rest/room")
public class RoomAPI {

  @Autowired
  private RoomService roomService;

  @GetMapping
  public ResponseEntity<RoomDTO> findRoomById(@Param("id") Long id) {
    RoomDTO result = roomService.getRoomById(id);
    return ResponseEntity.ok(result);
  }
}
