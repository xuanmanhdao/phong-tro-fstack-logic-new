package com.fstack.phong_tro_fstack.base.converter;

import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    public PostDTO toDTO(PostEntity postEntity){
        PostDTO postDTO=new PostDTO();
        postDTO.setId(postEntity.getId());
        postDTO.setThumbnail(postEntity.getThumbnail());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setContent(postEntity.getContent());
        postDTO.setPhoneNumber(postEntity.getPhoneNumber());
        postDTO.setPhoneZalo(postEntity.getPhoneZalo());
        postDTO.setCreatedTime(postEntity.getCreatedTime());
        postDTO.setCreatedAt(postEntity.getCreatedAt());
        postDTO.setUpdatedAt(postEntity.getUpdatedAt());
        postDTO.setNumberDate(postEntity.getNumberDate());
        postDTO.setStatus(postEntity.getStatus());
        return postDTO;
    }

    public PostEntity toEntity(PostDTO postDTO){
        PostEntity postEntity=new PostEntity();
        postEntity.setId(postDTO.getId());
        postEntity.setThumbnail(postDTO.getThumbnail());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());
        postEntity.setPhoneNumber(postDTO.getPhoneNumber());
        postEntity.setPhoneZalo(postDTO.getPhoneZalo());
        postEntity.setCreatedTime(postDTO.getCreatedTime());
        postEntity.setCreatedAt(postDTO.getCreatedAt());
        postEntity.setUpdatedAt(postDTO.getUpdatedAt());
        postEntity.setNumberDate(postDTO.getNumberDate());
        postEntity.setStatus(postDTO.getStatus());
        return postEntity;
    }
}
