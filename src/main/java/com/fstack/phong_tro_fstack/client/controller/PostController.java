package com.fstack.phong_tro_fstack.client.controller;

import com.fstack.phong_tro_fstack.client.output.post.PagedPostResponse;
import com.fstack.phong_tro_fstack.client.output.post.PostResponse;
import com.fstack.phong_tro_fstack.client.service.PostService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/show-posts")
public class PostController {

  @Autowired
  private PostService postService;

  @GetMapping()
  public ModelAndView show(
      @Param("idProvince") Optional<String> idProvince,
      @Param("idDistrict") Optional<String> idDistrict,
      @Param("idWard") Optional<String> idWard,
      @Param("rentPrice") Optional<String> rentPrice,
      @Param("minPrice") Optional<Float> minPrice,
      @Param("maxPrice") Optional<Float> maxPrice,
      @Param("acreage") Optional<String> acreage,
      @Param("minAcreage") Optional<Float> minAcreage,
      @Param("maxAcreage") Optional<Float> maxAcreage,
      @Param("exactAddress") Optional<String> exactAddress,
      @Param(value = "pageNumber") Optional<Integer> pageNumber,
      @Param(value = "pageSize") Optional<Integer> pageSize
  ) {
    ModelAndView modelAndView = new ModelAndView("client/posts");
    PagedPostResponse data = postService.getAllByNumberDateOtherZeroOrderByCreatedAt(
        idProvince,
        idDistrict,
        idWard,
        rentPrice,
        minPrice,
        maxPrice,
        acreage,
        minAcreage,
        maxAcreage,
        exactAddress,
        pageNumber,
        pageSize
    );
    modelAndView.addObject("postsResponse", data);
    return modelAndView;
  }

  @GetMapping("/post/{id}")
  public ModelAndView showDetailPost(
      @PathVariable("id") Long id
  ) {
    System.out.println("id post: " + id);
    ModelAndView modelAndView = new ModelAndView("client/detail-post");
    PostResponse data = postService.getDetailPost(id);
    modelAndView.addObject("postResponse", data);
    return modelAndView;
  }
}
