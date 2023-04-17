package com.fstack.phong_tro_fstack.client.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
    @GetMapping("/test-upload-file-google-drive")
    public ModelAndView uploadFile() {
        ModelAndView modelAndView = new ModelAndView("client/test-upload-file-on-google-drive");
        return modelAndView;
    }
}
