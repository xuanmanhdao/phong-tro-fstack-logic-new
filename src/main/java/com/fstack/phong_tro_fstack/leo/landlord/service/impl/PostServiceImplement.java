package com.fstack.phong_tro_fstack.leo.landlord.service.impl;

import com.fstack.phong_tro_fstack.base.converter.AreaConverter;
import com.fstack.phong_tro_fstack.base.converter.PostConverter;
import com.fstack.phong_tro_fstack.base.dto.AreaDTO;
import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import com.fstack.phong_tro_fstack.base.dto.RoomDTO;
import com.fstack.phong_tro_fstack.base.entity.AreaEntity;
import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.leo.landlord.dao.AreaDao;
import com.fstack.phong_tro_fstack.leo.landlord.dao.PostDao;
import com.fstack.phong_tro_fstack.leo.landlord.service.AreaService;
import com.fstack.phong_tro_fstack.leo.landlord.service.PostService;
import com.fstack.phong_tro_fstack.leo.landlord.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Qualifier("postService")
public class PostServiceImplement implements PostService {

  @Autowired
  private PostDao postDao;
  @Qualifier("areaService")
  AreaService areaService;
  @Autowired
  private PostConverter postConverter;
  @Autowired
  AreaConverter areaConverter;

  @Override
  public PostDTO savePost(PostDTO postDTO) {
    if (postDTO != null) {
      PostEntity postEntity = postConverter.toEntity(postDTO);

      postDao.savePost(postEntity);
    }
    return postDTO;
  }


  @Override
  public PostDTO getPost(long id) {
    PostEntity postEntity = postDao.getPostById(id);
    return postConverter.toDTO(postEntity);
  }

  @Override
  public PostDTO updatePost(PostDTO postDTO, long id) {
    if (postDTO != null && id < 0) {
      PostEntity postEntity = postDao.getPostById(id);
      if (postEntity != null) {
        postDTO = postConverter.toDTO(postEntity);
        postDao.updatePost(postEntity);
      }
    }
    return postDTO;
  }

  @Override
  public List<PostDTO> getPostAndRateByUser(long idUser) {
    return postConverter.toListDTOAndRate(postDao.getPostAndRateByUser(idUser));
  }


}
