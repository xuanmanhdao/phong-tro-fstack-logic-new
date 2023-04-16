package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import com.fstack.phong_tro_fstack.client.output.post.PagedPostResponse;
import com.fstack.phong_tro_fstack.client.output.post.PostResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
//    List<PostDTO> getAllByNumberDateOtherZeroOrderByCreatedAt();

  PagedPostResponse getAllByNumberDateOtherZeroOrderByCreatedAt(
      Optional<String> idProvince,
      Optional<String> idDistrict,
      Optional<String> idWard,
      Optional<String> rentPrice,
      Optional<Float> minPrice,
      Optional<Float> maxPrice,
      Optional<String> acreage,
      Optional<Float> minAcreage,
      Optional<Float> maxAcreage,
      Optional<String> exactAddress,
      Optional<Integer> pageNumber,
      Optional<Integer> pageSize
  );

  PostResponse getDetailPost(Long id);
}
