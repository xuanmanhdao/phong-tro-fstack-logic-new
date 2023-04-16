package com.fstack.phong_tro_fstack.leo.landlord.controller;


import com.fstack.phong_tro_fstack.base.dto.UserDTO;
import com.fstack.phong_tro_fstack.leo.landlord.common.Common;
import com.fstack.phong_tro_fstack.leo.landlord.dto.PostDTO;
import com.fstack.phong_tro_fstack.leo.landlord.service.AreaService;
import com.fstack.phong_tro_fstack.leo.landlord.service.PostService;
import com.fstack.phong_tro_fstack.leo.landlord.service.UserService;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("leo")
public class HomeController {

  //hiển thị ds post
  @Autowired
  @Qualifier("postService")
  private PostService postService;
  @Autowired
  @Qualifier("areaService")
  AreaService areaService;
  @Autowired
  UserService userService;

  //  @GetMapping({"/home", "/", ""})
//  public String homeShowNews(ModelMap modelMap) {
//    modelMap.addAttribute("posts", postService.getPostAndRateByUser(2));
//    return "landlord/home";
//  }

  @GetMapping({"/home", "/", ""})
  public String homeShowNews(ModelMap modelMap, HttpSession session) {
    UserDTO user = userService.getUser(2);
    session.setAttribute("user", user);
    long idUser = ((UserDTO) session.getAttribute("user")).getId();
    List<PostDTO> postDTOs = postService.getPostAndRateByUser(idUser);
    Common common = new Common();
    List<PostDTO> updatedPostDTOs = new ArrayList<>();
    for (PostDTO post : postDTOs) {
      post.setThumbnail(common.geturlthumbnail(post.getThumbnail()));
      updatedPostDTOs.add(post);
    }
    postDTOs = updatedPostDTOs;
    modelMap.addAttribute("posts", postDTOs);
    return "landlord/home";
  }

//  @GetMapping("/getAllArae")
//  public List<PostDTO> getAllPost() {
//
//    return postService.getPostAndRateByUser(2);
//  }
}
