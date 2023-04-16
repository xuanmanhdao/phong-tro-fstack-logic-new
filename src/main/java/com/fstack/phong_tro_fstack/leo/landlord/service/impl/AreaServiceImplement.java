package com.fstack.phong_tro_fstack.leo.landlord.service.impl;

import com.fstack.phong_tro_fstack.base.converter.AreaConverter;
import com.fstack.phong_tro_fstack.base.converter.PostConverter;
import com.fstack.phong_tro_fstack.base.converter.UserConverter;
import com.fstack.phong_tro_fstack.base.dto.AreaDTO;
import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.dto.UserDTO;
import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.leo.landlord.dao.AreaDao;
import com.fstack.phong_tro_fstack.leo.landlord.service.AreaService;

import com.fstack.phong_tro_fstack.leo.landlord.service.PostService;
import com.fstack.phong_tro_fstack.leo.landlord.service.RoomService;
import com.fstack.phong_tro_fstack.leo.landlord.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Qualifier("areaService")
public class AreaServiceImplement implements AreaService {

  @Autowired
  private AreaDao areaDao;

  @Autowired
  private AreaConverter areaConverter;
  @Autowired
  @Qualifier("postService")
  private PostService postService;
  @Autowired
  @Qualifier("roomService")
  private RoomService roomService;
  @Autowired
  private PostConverter postConverter;

  @Autowired
  private UserService userService;
  @Autowired
  private UserConverter userConverter;

  @Override
  public AreaDTO saveArea(AreaDTO areaDTO) {
    AreaEntity areaEntity = areaConverter.toEntity(areaDTO);
    areaDao.saveArea(areaEntity);
    return areaDTO;
  }


  @Override
  public AreaDTO getArea(long id) {
    AreaEntity areaEntity = areaDao.getAreaById(id);
    return areaConverter.toDTO(areaEntity);
  }

  @Override
  public AreaDTO updateArea(AreaDTO areaDTO, long id) {
    if (areaDTO != null && id < 0) {
      AreaEntity areaEntity = areaDao.getAreaById(id);
      if (areaEntity != null) {
        areaDTO = areaConverter.toDTO(areaEntity);
        areaDao.updateArea(areaEntity);
      }
    }
    return areaDTO;
  }

  @Override
  public Long saveAreaAndGetId(AreaDTO areaDTO) {
    PostEntity postEntity = postConverter.toEntity(areaDTO.getPostDTO());
    postEntity.setUserEntity(
        userConverter.toEntity(userService.getUser(areaDTO.getPostDTO().getIdUser())));
    AreaEntity areaEntity = areaConverter.toEntity(areaDTO);
    postEntity.setAreaEntity(areaEntity);
    areaEntity.setPostEntity(postEntity);
    return areaDao.saveAreaAndGetId(areaEntity);
  }

  @Override
  public List<AreaDTO> getAllArea() {
    return areaConverter.toListDTO(areaDao.getAllArea());
  }

  @Override
  public AreaDTO getAreaByPostId(long idPost) {
    PostDTO postDTO = postService.getPost(idPost);
    AreaDTO areaDTO = areaConverter.toDTO(areaDao.getAreaByPostId(idPost));
    List<RoomDTO> roomDTOs = roomService.getRoombyAreaId(areaDTO.getId());
    areaDTO.setRoomDTOList(roomDTOs);
    areaDTO.setPostDTO(postDTO);
    return areaDTO;
  }

}
