package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDTO> getAllByNumberDateOtherZeroOrderByCreatedAt();
}
