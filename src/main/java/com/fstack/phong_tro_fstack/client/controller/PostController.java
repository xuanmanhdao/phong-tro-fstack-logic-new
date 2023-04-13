package com.fstack.phong_tro_fstack.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/show-posts")
public class PostController {
    @GetMapping()
    public ModelAndView show(){
      ModelAndView modelAndView = new ModelAndView("client/posts");
      return modelAndView;
    }
}
