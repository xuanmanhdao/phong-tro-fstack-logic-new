package com.fstack.phong_tro_fstack.leo.landlord.common;

import com.fstack.phong_tro_fstack.leo.landlord.Google.FileResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class Common {

  public String geturlthumbnail(String json) {
    Gson gson = new Gson();
    List<FileResponse> imageid = gson.fromJson(json, new TypeToken<List<FileResponse>>() {
    }.getType());
    return "https://drive.google.com/uc?id=" + imageid.get(0).getId();
  }

  public List<String> getListImage(String json) {
    List<String> list = new ArrayList<>();
    Gson gson = new Gson();
    List<FileResponse> imageid = gson.fromJson(json, new TypeToken<List<FileResponse>>() {
    }.getType());
    for (FileResponse fileResponse : imageid) {
      list.add("https://drive.google.com/uc?id=" + fileResponse.getId());
    }
    return list;
  }
}
