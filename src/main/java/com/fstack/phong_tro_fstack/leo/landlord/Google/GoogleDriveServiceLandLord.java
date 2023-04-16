package com.fstack.phong_tro_fstack.leo.landlord.Google;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface GoogleDriveServiceLandLord {

  FileResponse uploadFile(MultipartFile file) throws IOException;

  List<FileResponse> getAllFile() throws IOException;

  void deleteFile(String fileId) throws IOException;
}
