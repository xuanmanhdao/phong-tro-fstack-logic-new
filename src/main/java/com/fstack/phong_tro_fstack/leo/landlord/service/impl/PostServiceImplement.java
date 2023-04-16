package com.fstack.phong_tro_fstack.leo.landlord.service.impl;


import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.leo.landlord.converter.AreaConverterLandLord;
import com.fstack.phong_tro_fstack.leo.landlord.converter.PostConverterLandLord;
import com.fstack.phong_tro_fstack.leo.landlord.dao.PostDao;
import com.fstack.phong_tro_fstack.leo.landlord.dto.PostDTO;
import com.fstack.phong_tro_fstack.leo.landlord.service.AreaService;
import com.fstack.phong_tro_fstack.leo.landlord.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("postService")
public class PostServiceImplement implements PostService {

  @Autowired
  private PostDao postDao;
  @Qualifier("areaService")
  AreaService areaService;
  @Autowired
  private PostConverterLandLord postConverterLandLord;
  @Autowired
  AreaConverterLandLord areaConverterLandLord;

  @Override
  public PostDTO savePost(PostDTO postDTO) {
    if (postDTO != null) {
      PostEntity postEntity = postConverterLandLord.toEntity(postDTO);

      postDao.savePost(postEntity);
    }
    return postDTO;
  }


  @Override
  public PostDTO getPost(long id) {
    PostEntity postEntity = postDao.getPostById(id);
    return postConverterLandLord.toDTO(postEntity);
  }

  @Override
  public PostDTO updatePost(PostDTO postDTO, long id) {
    if (postDTO != null && id < 0) {
      PostEntity postEntity = postDao.getPostById(id);
      if (postEntity != null) {
        postDTO = postConverterLandLord.toDTO(postEntity);
        postDao.updatePost(postEntity);
      }
    }
    return postDTO;
  }

  @Override
  public List<PostDTO> getPostAndRateByUser(long idUser) {
    return postConverterLandLord.toListDTOAndRate(postDao.getPostAndRateByUser(idUser));
  }


}
