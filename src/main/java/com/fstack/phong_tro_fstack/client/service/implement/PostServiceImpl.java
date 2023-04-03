package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.base.converter.PostConverter;
import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import com.fstack.phong_tro_fstack.base.entity.PostEntity;
import com.fstack.phong_tro_fstack.client.repository.PostRepository;
import com.fstack.phong_tro_fstack.client.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostConverter postConverter;

    @Override
    public List<PostDTO> getAllByNumberDateOtherZeroOrderByCreatedAt() {
        List<PostDTO> result = new ArrayList<>();
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createdAt");
//        Sort sort = Sort.by(order);

        List<PostEntity> postEntities = postRepository.findAllNumberDateOtherZeroOrderByCreatedAt();
        for (PostEntity postEntity : postEntities) {
            result.add(postConverter.toDTO(postEntity));
        }
        return result;
    }
}
