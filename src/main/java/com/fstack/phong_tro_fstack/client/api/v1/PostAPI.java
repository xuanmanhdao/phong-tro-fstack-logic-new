package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.base.dto.PostDTO;
import com.fstack.phong_tro_fstack.client.output.post.PostResponse;
import com.fstack.phong_tro_fstack.client.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rest/post")
public class PostAPI {
    @Autowired
    private PostService postService;

//    @GetMapping
//    public ResponseEntity<?> findAllNumberDateOtherZeroOrderByCreatedAt() {
//        List<PostDTO> result = postService.getAllByNumberDateOtherZeroOrderByCreatedAt();
//        return ResponseEntity.ok(result);
//    }

    @GetMapping()
    public ResponseEntity<?> findAllNumberDateOtherZeroOrderByCreatedAt(){
        List<PostResponse> result=postService.getAllByNumberDateOtherZeroOrderByCreatedAt();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPost(@RequestParam String keyword) {
        return null;
    }
}
