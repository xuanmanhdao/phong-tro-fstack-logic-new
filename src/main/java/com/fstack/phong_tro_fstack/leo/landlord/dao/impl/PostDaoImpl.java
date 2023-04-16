package com.fstack.phong_tro_fstack.leo.landlord.dao.impl;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.base.entity.RoomEntity;
import com.fstack.phong_tro_fstack.client.repository.PostRepository;
import com.fstack.phong_tro_fstack.leo.landlord.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {

  @Autowired
  private PostRepository postRepository;

  @Override
  public void savePost(PostEntity postEntity) {
    postRepository.save(postEntity);
  }

  @Override
  public void updatePost(PostEntity postEntity) {
    postRepository.save(postEntity);

  }

  @Override
  public PostEntity getPostById(long id) {
    return postRepository.findById(id).get();
  }

  @Override
  public List<PostEntity> getAllPost() {
    return postRepository.findAll();
  }

  @Override
  public List<Object[]> getPostAndRateByUser(long idUser) {
    return postRepository.getPostandRatebyUser(idUser);
  }

}
