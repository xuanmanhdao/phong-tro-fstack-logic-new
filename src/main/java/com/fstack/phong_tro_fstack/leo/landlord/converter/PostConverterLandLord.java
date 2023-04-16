package com.fstack.phong_tro_fstack.leo.landlord.converter;

import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.leo.landlord.dto.PostDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostConverterLandLord implements Mapper<PostEntity, PostDTO> {

  @Autowired
  private ModelMapper modelMapper;

  public PostEntity toEntity(PostDTO dto) {
    return modelMapper.map(dto, PostEntity.class);
  }

  public PostDTO toDTO(PostEntity entity) {
    return modelMapper.map(entity, PostDTO.class);
  }


  public List<PostEntity> toListEntity(List<PostDTO> postDTOList) {
    List<PostEntity> postEntityList = new ArrayList<>();
    for (PostDTO postDTO : postDTOList) {
      postEntityList.add(toEntity(postDTO));
    }
    return postEntityList;
  }

  public List<PostDTO> toListDTO(List<PostEntity> postEntityList) {
    List<PostDTO> postDTOList = new ArrayList<>();
    for (PostEntity PostEntity : postEntityList) {
      postDTOList.add(toDTO(PostEntity));
    }
    return postDTOList;
  }

  public List<PostDTO> toListDTOAndRate(List<Object[]> objects) {
    List<PostDTO> list = new ArrayList<>();
    for (Object[] result : objects) {
      PostDTO postDTO = new PostDTO();
      postDTO.setContent((String) result[0]);
      postDTO.setStatus((int) result[1]);
      postDTO.setTitle((String) result[2]);
      postDTO.setThumbnail((String) result[3]);
      postDTO.setId((long) result[4]);
      // Lấy giá trị ratingStarts từ cột ratingStarts của RateEntity
      postDTO.setRatetingStart(result[5] instanceof Float ? (Float) result[4] : 0f);
      list.add(postDTO);
    }
    return list;
  }

}
