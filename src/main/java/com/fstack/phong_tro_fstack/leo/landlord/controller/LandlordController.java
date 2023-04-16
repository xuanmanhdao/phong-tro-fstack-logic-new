package com.fstack.phong_tro_fstack.leo.landlord.controller;

import com.fstack.phong_tro_fstack.leo.landlord.common.Common;
import com.fstack.phong_tro_fstack.leo.landlord.dto.AreaDTO;
import com.fstack.phong_tro_fstack.leo.landlord.service.UserService;
import com.fstack.phong_tro_fstack.leo.landlord.service.impl.AreaServiceImplement;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("leo")
public class LandlordController {

  @Autowired
  @Qualifier("areaService")
  private AreaServiceImplement areaService;

  @Autowired
  private UserService userService;

  @GetMapping("/post-news")
  public String post_news(ModelMap model) {
//    model.addAttribute("balance",
//        userService.getUser(userId) != null ? userService.getUser(userId) : userService.getUser(2));
    return "landlord/post-news";
  }

  @GetMapping("/update-post-news")
  public String updatePost(ModelMap modelMap, Model model, @RequestParam("id") long id) {
    AreaDTO areaDTO = areaService.getAreaByPostId(id != 0 ? id : 1);
    String jsonListImg = areaDTO.getRoomDTOList().get(0).getImage();
    Common common = new Common();
    String json = areaDTO.getPostDTO().getThumbnail();
    areaDTO.getPostDTO().setThumbnail(common.geturlthumbnail(json));
    modelMap.addAttribute("area", areaDTO);
    model.addAttribute("imageRoom", common.getListImage(jsonListImg));
    return "landlord/post-edit";
  }


  @GetMapping("/manage-post")
  public String manage_post() {
    return "landlord/manage-post";
  }

  @GetMapping("/profile")
  public String profile() {
    return "landlord/profile";
  }

  @GetMapping("/sign-in")
  public String sign_in() {
    return "landlord/sign-in";
  }

  @GetMapping("/profile-edit")
  public String profile_edit() {
    return "landlord/profile-edit";
  }

  @GetMapping("/account-balance")
  public String account_balance() {
    return "landlord/account-balance";
  }

  @GetMapping("/chat")
  public String chat() {
    return "landlord/chat";
  }

  //@GetMapping("/post-news")
//public String post_news(ModelMap model, @SessionAttribute("userId") Long userId) {
//  model.addAttribute("balance",
//      userService.getUser(userId) != null ? userService.getUser(userId) : userService.getUser(2));
//  return "landlord/post-news";
//}
//    @GetMapping({"/home","/"})
//    public String home() {
//        return "landlord/home";
//    }
}
