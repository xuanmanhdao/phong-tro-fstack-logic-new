package com.fstack.phong_tro_fstack.leo.landlord.service.impl;

import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.leo.landlord.converter.AreaConverterLandLord;
import com.fstack.phong_tro_fstack.leo.landlord.converter.PostConverterLandLord;
import com.fstack.phong_tro_fstack.leo.landlord.converter.UserConverterLandLord;
import com.fstack.phong_tro_fstack.leo.landlord.dao.AreaDao;
import com.fstack.phong_tro_fstack.leo.landlord.dto.AreaDTO;
import com.fstack.phong_tro_fstack.leo.landlord.dto.PostDTO;
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
  private AreaConverterLandLord areaConverterLandLord;
  @Autowired
  @Qualifier("postService")
  private PostService postService;
  @Autowired
  @Qualifier("roomService")
  private RoomService roomService;
  @Autowired
  private PostConverterLandLord postConverterLandLord;

  @Autowired
  private UserService userService;
  @Autowired
  private UserConverterLandLord userConverterLandLord;

  @Override
  public AreaDTO saveArea(AreaDTO areaDTO) {
    AreaEntity areaEntity = areaConverterLandLord.toEntity(areaDTO);
    areaDao.saveArea(areaEntity);
    return areaDTO;
  }


  @Override
  public AreaDTO getArea(long id) {
    AreaEntity areaEntity = areaDao.getAreaById(id);
    return areaConverterLandLord.toDTO(areaEntity);
  }

  @Override
  public AreaDTO updateArea(AreaDTO areaDTO, long id) {
    if (areaDTO != null && id < 0) {
      AreaEntity areaEntity = areaDao.getAreaById(id);
      if (areaEntity != null) {
        areaDTO = areaConverterLandLord.toDTO(areaEntity);
        areaDao.updateArea(areaEntity);
      }
    }
    return areaDTO;
  }

  @Override
  public Long saveAreaAndGetId(AreaDTO areaDTO) {
    PostEntity postEntity = postConverterLandLord.toEntity(areaDTO.getPostDTO());
    postEntity.setUserEntity(
        userConverterLandLord.toEntity(userService.getUser(areaDTO.getPostDTO().getIdUser())));
    AreaEntity areaEntity = areaConverterLandLord.toEntity(areaDTO);
    postEntity.setAreaEntity(areaEntity);
    areaEntity.setPostEntity(postEntity);
    return areaDao.saveAreaAndGetId(areaEntity);
  }

  @Override
  public List<AreaDTO> getAllArea() {
    return areaConverterLandLord.toListDTO(areaDao.getAllArea());
  }

  @Override
  public AreaDTO getAreaByPostId(long idPost) {
    PostDTO postDTO = postService.getPost(idPost);
    AreaDTO areaDTO = areaConverterLandLord.toDTO(areaDao.getAreaByPostId(idPost));
    List<RoomDTO> roomDTOs = roomService.getRoombyAreaId(areaDTO.getId());
    areaDTO.setRoomDTOList(roomDTOs);
    areaDTO.setPostDTO(postDTO);
    return areaDTO;
  }

}
