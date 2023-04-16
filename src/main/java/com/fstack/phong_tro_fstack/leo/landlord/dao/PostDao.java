package com.fstack.phong_tro_fstack.leo.landlord.dao;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;

import java.util.List;

public interface PostDao {
    void savePost(PostEntity postEntity);
    void updatePost(PostEntity postEntity);
    PostEntity getPostById(long id);
    List<PostEntity>getAllPost();
    List<Object[]>getPostAndRateByUser(long idUser);
}
