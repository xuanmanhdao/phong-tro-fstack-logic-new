package com.fstack.phong_tro_fstack.leo.landlord.controller;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.dto.UserDTO;

import com.fstack.phong_tro_fstack.leo.landlord.dto.AreaDTO;
import com.fstack.phong_tro_fstack.leo.landlord.service.RoomService;
import com.fstack.phong_tro_fstack.leo.landlord.service.impl.AreaServiceImplement;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("leo")
public class PostManageController {

  @Autowired
  @Qualifier("areaService")
  private AreaServiceImplement areaService;

  @Autowired
  @Qualifier("roomService")
  private RoomService roomService;

  @PostMapping(value = "PostNews/save-post-news")
  public ResponseEntity<?> savePost(@RequestBody AreaDTO areaDTO, HttpSession session) {
    UserDTO userDTO = (UserDTO) session.getAttribute("user");
    areaDTO.getPostDTO().setIdUser(userDTO != null ? userDTO.getId() : 2L);
    areaDTO.getPostDTO().setThumbnail((String) session.getAttribute("one-image"));

    Long areaId = areaService.saveAreaAndGetId(areaDTO);
    List<RoomDTO> roomDTOList = areaDTO.getRoomDTOList();
    if (roomDTOList != null && !roomDTOList.isEmpty()) {
      for (RoomDTO roomDTO : roomDTOList) {
        roomDTO.setIdArea(areaId);
        roomDTO.setImage((String) session.getAttribute("list-image"));
        roomService.saveRoom(roomDTO);
      }
    }
    return ResponseEntity.ok().build();
  }


}
