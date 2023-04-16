package com.fstack.phong_tro_fstack.leo.landlord.controller;


import com.fstack.phong_tro_fstack.base.dto.UserDTO;
import com.fstack.phong_tro_fstack.leo.landlord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{id}")
    public UserDTO testUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }
//    @GetMapping("/post-news")
//    public String testUserd() {
//        System.out.println("sdfsdf");
//        return "landlord/home";
//    }
//    @GetMapping("/")
//    public ModelAndView test(){
//        return new ModelAndView("landlord/post-news");
//    }
//    @GetMapping("/test")
//    public ModelAndView test2(){
//        return new ModelAndView("landlord/test");
//    }

//    @PostMapping(value = "/adduser")
//    public UserDTO addUser(@RequestBody UserDTO userDTO) {
////        return ResponseEntity.ok().headers(new HttpHeaders()).body(userDTO);
//        return userService.addUser(userDTO) ;
//    }
//    @PutMapping(value = "/updateuser")
//    public UserDTO updateUser(@RequestParam("id")long id,@RequestBody UserDTO userDTO) {
////        return ResponseEntity.ok().headers(new HttpHeaders()).body(userDTO);
//        return userService.updateUser(userDTO, id);
//    }
}
