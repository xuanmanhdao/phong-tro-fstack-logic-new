package com.fstack.phong_tro_fstack.client.api.v1;

import com.fstack.phong_tro_fstack.client.output.FileResponse;
import com.fstack.phong_tro_fstack.client.service.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/rest/file-on-google-drive")
public class ServiceFileItemAPI {

    @Autowired
    private GoogleDriveService googleDriveService;

    @PostMapping()
    public ResponseEntity<?> uploadFileOnService(@RequestParam("files") MultipartFile[] files) {
        // Kiểm tra xem file có tồn tại không
        if (files.length == 0) {
            return ResponseEntity.badRequest().body("Không có file được chọn");
        } else {
            List<FileResponse> fileResponseList = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.getContentType().startsWith("image/")) {
                    return ResponseEntity.badRequest().body("File không đúng định dạng ảnh");
                } else {
                    try {
                        FileResponse fileResponse = googleDriveService.uploadFiles(file);
                        fileResponseList.add(fileResponse);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lưu file");
                    }
                }
            }
            return ResponseEntity.ok().body(fileResponseList);
        }
    }
}
