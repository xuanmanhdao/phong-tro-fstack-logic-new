package com.fstack.phong_tro_fstack.client.service;

import com.fstack.phong_tro_fstack.client.output.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface GoogleDriveService {
    FileResponse uploadFiles(MultipartFile file) throws IOException;
}
