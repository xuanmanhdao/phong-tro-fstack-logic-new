package com.fstack.phong_tro_fstack.client.service.implement;

import com.fstack.phong_tro_fstack.client.output.FileResponse;
import com.fstack.phong_tro_fstack.client.service.GoogleDriveService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class GoogleDriveServiceImpl implements GoogleDriveService {
    private static final String APPLICATION_NAME = "Google drive SpringBoot Fstack";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URI;

    @Value("${google.secret.key.path}")
    private Resource googleDriveSecretKeys;

    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;

    @Value("${google.service.account.key}")
    private Resource serviceAccountKey;

    private Drive getDrive() throws IOException {
        Credential credential = GoogleCredential.fromStream(serviceAccountKey.getInputStream()).createScoped(SCOPES);
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
        return drive;
    }

    @Override
    public FileResponse uploadFile(MultipartFile file) throws IOException {
        Drive drive = this.getDrive();

//        // Create a folder in Google Drive
//        File folderMetadata = new File();
//        folderMetadata.setName("FStack Find Accommodation");
//        folderMetadata.setMimeType("application/vnd.google-apps.folder");
//        File folder = drive.files().create(folderMetadata)
//                .setFields("id")
//                .execute();
//        String folderId = folder.getId();

        // Upload file in folder
        File gDriveFile = new File();
        gDriveFile.setName(file.getOriginalFilename());
//        gDriveFile.setParents(Collections.singletonList(folderId));

        // Tạo đối tượng FileContent để chứa dữ liệu file
        InputStreamContent mediaContent = new InputStreamContent(
                file.getContentType(),
                new BufferedInputStream(file.getInputStream())
        );

        // Gửi file lên Google Drive và lưu trữ:
        // ID, Name, Thumbnail Link, Web View Link, Web Content Link của file mới được tạo
        File uploadedFile = drive.files().create(gDriveFile, mediaContent)
                .setFields("id,name,thumbnailLink,webViewLink,webContentLink").execute();

        // Set public cho file
        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        drive.permissions().create(uploadedFile.getId(), permission).execute();

        // Trả về
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(uploadedFile.getId());
        fileResponse.setName(uploadedFile.getName());
        fileResponse.setThumbnailLink(uploadedFile.getThumbnailLink());
        fileResponse.setWebViewLink(uploadedFile.getWebViewLink());
        fileResponse.setWebContentLink(uploadedFile.getWebContentLink());

        return fileResponse;
    }

    @Override
    public List<FileResponse> getAllFile() throws IOException {
        Drive drive = this.getDrive();
        List<FileResponse> responseList = new ArrayList<>();
        FileList fileList = drive.files().list().setFields("files(id,name,thumbnailLink,webViewLink,webContentLink)").execute();
        for (File file : fileList.getFiles()) {
            FileResponse fileResponse = new FileResponse();
            fileResponse.setId(file.getId());
            fileResponse.setName(file.getName());
            fileResponse.setThumbnailLink(file.getThumbnailLink());
            fileResponse.setWebViewLink(file.getWebViewLink());
            fileResponse.setWebContentLink(file.getWebContentLink());
            responseList.add(fileResponse);
        }
        return responseList;
    }

    @Override
    public void deleteFile(String fileId) throws IOException {
        Drive drive = this.getDrive();
        drive.files().delete(fileId).execute();
    }
}
