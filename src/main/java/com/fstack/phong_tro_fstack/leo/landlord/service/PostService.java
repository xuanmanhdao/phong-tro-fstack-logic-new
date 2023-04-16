package com.fstack.phong_tro_fstack.leo.landlord.service;

import com.fstack.phong_tro_fstack.base.dto.PostDTO;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import java.util.List;

public interface PostService {

  PostDTO savePost(PostDTO postDTO);

  PostDTO getPost(long id);

  PostDTO updatePost(PostDTO postDTO, long id);

  List<PostDTO> getPostAndRateByUser(long idUser);

}
