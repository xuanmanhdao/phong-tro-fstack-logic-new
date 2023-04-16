package com.fstack.phong_tro_fstack.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("client/index");
        return modelAndView;
    }

    @GetMapping("/test-template")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("client/test");
        return modelAndView;
    }
}
