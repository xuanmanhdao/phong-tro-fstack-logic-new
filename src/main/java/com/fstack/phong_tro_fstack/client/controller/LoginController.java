package com.fstack.phong_tro_fstack.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @GetMapping("/login-register")
  public ModelAndView loginRegister() {
    ModelAndView modelAndView = new ModelAndView("client/register");
    return modelAndView;
  }
}
