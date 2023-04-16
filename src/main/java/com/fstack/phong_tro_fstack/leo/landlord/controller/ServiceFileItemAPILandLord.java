package com.fstack.phong_tro_fstack.leo.landlord.controller;

import com.fstack.phong_tro_fstack.leo.landlord.Google.FileResponse;
import com.fstack.phong_tro_fstack.leo.landlord.Google.GoogleDriveServiceLandLord;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ServiceFileItemAPILandLord {

  private GetJsonImage getJsonImage;

  @Autowired
  @Qualifier("googleDriveService")
  private GoogleDriveServiceLandLord googleDriveService;

  @GetMapping("leo/getallImage")
  public ResponseEntity<?> getAllFile() throws IOException {
    List<FileResponse> result = googleDriveService.getAllFile();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

//  @PostMapping("/leo/upListImage/file-on-google-drive")
//  public ResponseEntity<?> uploadFileListImageOnService(@RequestParam("files") MultipartFile[] files) {
//    // Kiểm tra xem file có tồn tại không
//    if (files.length == 0) {
//      return ResponseEntity.badRequest().body("Không có file được chọn");
//    } else {
//      List<FileResponse> fileResponseList = new ArrayList<>();
//      for (MultipartFile file : files) {
//        if (!file.getContentType().startsWith("image/")) {
//          return ResponseEntity.badRequest().body("File không đúng định dạng ảnh");
//        } else {
//          try {
//            FileResponse fileResponse = googleDriveService.uploadFile(file);
//            fileResponseList.add(fileResponse);
//          } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lưu file");
//          }
//        }
//      }
//      System.out.println(getJsonImage());
//      getJsonImage.getJsonImg(fileResponseList);
//      return ResponseEntity.status(HttpStatus.CREATED).body(fileResponseList);
//    }
//  }

  @PostMapping("leo/upImage/list-file-on-google-drive")
  public ResponseEntity<?> uploadListFileOnService(
      @RequestParam("listFiles") MultipartFile[] listFiles, HttpSession session) {
    // Kiểm tra xem file có tồn tại không
    if (listFiles.length == 0) {
      return ResponseEntity.badRequest().body("Không có file được chọn");
    } else {
      List<FileResponse> fileResponseList = new ArrayList<>();
      for (MultipartFile file : listFiles) {
        if (!file.getContentType().startsWith("image/")) {
          return ResponseEntity.badRequest().body("File không đúng định dạng ảnh");
        } else {
          try {
            FileResponse fileListResponse = googleDriveService.uploadFile(file);
            fileResponseList.add(fileListResponse);
          } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lưu file");
          }
        }
      }
      session.setAttribute("list-image",new Gson().toJson(fileResponseList));

      return ResponseEntity.status(HttpStatus.CREATED).body(fileResponseList);
    }
  }

  @PostMapping("leo/upImage/one-file-on-google-drive")
  public ResponseEntity<?> uploadOneFileOnService(@RequestParam("file") MultipartFile files,
      HttpSession session) {

    // Kiểm tra xem file có tồn tại không
    if (files.getSize() == 0) {
      return ResponseEntity.badRequest().body("Không có file được chọn");
    } else {
      List<FileResponse> aFileResponse = new ArrayList<>();
      if (!files.getContentType().startsWith("image/")) {
        return ResponseEntity.badRequest().body("File không đúng định dạng ảnh");
      } else {
        try {
          FileResponse fileResponse = googleDriveService.uploadFile(files);
          aFileResponse.add(fileResponse);
        } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lưu file");
        }
      }
      session.setAttribute("one-image",new Gson().toJson(aFileResponse));
      return ResponseEntity.status(HttpStatus.CREATED).body(aFileResponse);
    }
  }


  @DeleteMapping("/{fileId}")
  public ResponseEntity<?> deleteFile(@PathVariable(name = "fileId") String fileId)
      throws IOException {
    googleDriveService.deleteFile(fileId);
    return ResponseEntity.status(HttpStatus.OK).body("File has been deleted");
  }


  interface GetJsonImage {

    void getJsonListImg(String listImage);

    void getJsonimg(String image);
  }
}
