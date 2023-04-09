package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.client.output.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface GoogleDriveService {
    FileResponse uploadFile(MultipartFile file) throws IOException;

    List<FileResponse> getAllFile() throws IOException;

    void deleteFile(String fileId) throws IOException;
}
