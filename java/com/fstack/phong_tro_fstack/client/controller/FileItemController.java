package com.fstack.phong_tro_fstack.client.controller;

import com.fstack.phong_tro_fstack.client.dto.FileItemDTO;
import com.fstack.phong_tro_fstack.client.output.FileResponse;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class FileItemController {
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    private static final String USER_IDENTIFIER_KEY = "MY_DUMMY_USER";

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URI;

    @Value("${google.secret.key.path}")
    private Resource googleDriveSecretKeys;

    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;

    @Value("${google.service.account.key}")
    private Resource serviceAccountKey;

    private GoogleAuthorizationCodeFlow flow;

    @PostConstruct
    public void init() throws IOException {
        GoogleClientSecrets googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(
                        googleDriveSecretKeys.getInputStream()));
        flow = new GoogleAuthorizationCodeFlow
                .Builder(HTTP_TRANSPORT, JSON_FACTORY, googleClientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(credentialsFolder.getFile()))
                .build();
    }

    @GetMapping("/test-gg-drive")
    public String showHomePage() throws IOException {
        boolean isUserAuthenticated = false;
        Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
        if (credential != null) {
            boolean tokenValid = credential.refreshToken();
            if (tokenValid) {
                isUserAuthenticated = true;
            }
        }

        return isUserAuthenticated ? "client/dashboard-test-gg-drive.html" : "client/index-test-gg-drive.html";
    }

    @GetMapping("/google-sign-in")
    public void doGoogleSignIn(HttpServletResponse httpServletResponse) throws IOException {
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        String redirectUrl = url.setRedirectUri(CALLBACK_URI).setAccessType("offline").build();
        httpServletResponse.sendRedirect(redirectUrl);
    }

    @GetMapping("/oauth")
    public String saveAuthorizationCode(HttpServletRequest httpServletRequest) throws IOException {
        String code = httpServletRequest.getParameter("code");
        if (code != null) {
            saveToken(code);
            return "client/dashboard-test-gg-drive.html";
        }
        return "client/index-test-gg-drive.html";
    }

    private void saveToken(String code) throws IOException {
        GoogleTokenResponse googleTokenResponse = flow.newTokenRequest(code).setRedirectUri(CALLBACK_URI).execute();
        flow.createAndStoreCredential(googleTokenResponse, USER_IDENTIFIER_KEY);
    }

    @GetMapping("/create-file")
    public void createFile(HttpServletResponse httpServletResponse) throws IOException {
        Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();
        File file = new File();
        file.setName("sample.jpg");
        FileContent fileContent = new FileContent("image/jpeg", new java.io.File("F:\\Pictures\\AnhMeme\\oke.jpg"));
        File uploadedFile = drive.files().create(file, fileContent).setFields("id").execute();

        String fileReference = String.format("{fileID: '%s'}", uploadedFile.getId());
        httpServletResponse.getWriter().write(fileReference);
    }

    @GetMapping(value = "/list-files", produces = {"application/json"})
    public @ResponseBody
    List<FileResponse> listFiles() throws IOException {
        Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();

        List<FileResponse> responseList = new ArrayList<>();

        FileList fileList = drive.files().list().setFields("files(id,name,thumbnailLink)").execute();
        for (File file : fileList.getFiles()) {
            FileResponse item = new FileResponse();
            item.setId(file.getId());
            item.setName(file.getName());
            item.setThumbnailLink(file.getThumbnailLink());
            responseList.add(item);
        }

        return responseList;
    }

    @DeleteMapping(value = {"/delete-file/{fileId}"}, produces = "application/json")
    public @ResponseBody
    Message deleteFile(@PathVariable(name = "fileId") String fileId) throws IOException {
        Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();
        drive.files().delete(fileId).execute();

        Message message = new Message();
        message.setMessage("File has been deleted");
        return message;
    }

    @GetMapping(value = "/create-folder/{folderName}", produces = "application/json")
    public @ResponseBody
    Message createFolder(@PathVariable(name = "folderName") String folderName) throws IOException {
        Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();

        File file = new File();
        file.setName(folderName);
        file.setMimeType("application/vnd.google-apps.folder");

        drive.files().create(file).execute();

        Message message = new Message();
        message.setMessage("Folder has been created successfully");

        return message;
    }

    @GetMapping("/upload-file-in-folder")
    public void uploadFileInFolder(HttpServletResponse httpServletResponse) throws IOException {
        Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();
        File file = new File();
        file.setName("fuckutest1.jpg");
        file.setParents(Arrays.asList("1yVz_DMHx9fx2LyT-yrTIEwZw8zHfH5iV"));

        FileContent fileContent = new FileContent("image/jpeg", new java.io.File("F:\\Pictures\\AnhMeme\\fucku.jpg"));
        File uploadedFile = drive.files().create(file, fileContent).setFields("id").execute();

        String fileReference = String.format("{fileID: '%s'}", uploadedFile.getId());
        httpServletResponse.getWriter().write(fileReference);
    }

    @PostMapping(value = "/make-public/{fileId}", produces = "application/json")
    public @ResponseBody
    Message makePublic(@PathVariable(name = "fileId") String fileId) throws IOException {
        Credential credential = flow.loadCredential(USER_IDENTIFIER_KEY);
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();

        Permission permission = new Permission();
        permission.setType("anyone");
        permission.setRole("reader");

        drive.permissions().create(fileId, permission).execute();

        Message message = new Message();
        message.setMessage("Permission has been successfully granted");
        return message;
    }

//    @GetMapping(value = {"/service-list-files"}, produces = {"application/json"})
//    public @ResponseBody
//    List<FileItemDTO> listFilesInServiceAccount() throws IOException {
//        Credential cred = GoogleCredential.fromStream(serviceAccountKey.getInputStream());
//
//        GoogleClientRequestInitializer keyInitializer = new CommonGoogleClientRequestInitializer();
//
//        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, null).setHttpRequestInitializer(cred)
//                .setGoogleClientRequestInitializer(keyInitializer).build();
//
//        List<FileItemDTO> responseList = new ArrayList<>();
//
//        FileList fileList = drive.files().list().setFields("files(id,name,thumbnailLink)").execute();
//        for (File file : fileList.getFiles()) {
//            FileItemDTO item = new FileItemDTO();
//            item.setId(file.getId());
//            item.setName(file.getName());
//            item.setThumbnailLink(file.getThumbnailLink());
//            responseList.add(item);
//        }
//
//        return responseList;
//    }

    @GetMapping(value = {"/service-list-files"}, produces = {"application/json"})
    public @ResponseBody
    List<FileResponse> listFilesInServiceAccount() throws IOException, GeneralSecurityException {
        GoogleCredential credential = GoogleCredential.fromStream(serviceAccountKey.getInputStream())
                .createScoped(Collections.singleton(DriveScopes.DRIVE_READONLY));

        // Create a new Drive API client
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        Drive drive = new Drive.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();
        // Get the list of files from the Drive API
        List<FileResponse> fileItems = new ArrayList<>();
        String nextPageToken = null;
        do {
            FileList files = drive.files().list().setQ("trashed = false")
                    .setFields("nextPageToken, files(id, name, thumbnailLink)").setPageToken(nextPageToken)
                    .execute();
            List<File> fileList = files.getFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    FileResponse fileItem = new FileResponse();
                    fileItem.setId(file.getId());
                    fileItem.setName(file.getName());
                    fileItem.setThumbnailLink(file.getThumbnailLink());
                    fileItems.add(fileItem);
                }
            }
            nextPageToken = files.getNextPageToken();
        } while (nextPageToken != null);

        return fileItems;
    }

    @GetMapping("/create-file-by-service")
    public void createFileByService(HttpServletResponse httpServletResponse) throws IOException {
        Credential credential = GoogleCredential.fromStream(serviceAccountKey.getInputStream()).createScoped(Collections.singleton(DriveScopes.DRIVE));
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName("Google drive SpringBoot Fstack").build();
        File file = new File();
        file.setName("sample.jpg");
        FileContent fileContent = new FileContent("image/jpeg", new java.io.File("F:\\Pictures\\AnhMeme\\oke.jpg"));
        File uploadedFile = drive.files().create(file, fileContent).setFields("id").execute();

        String fileReference = String.format("{fileID: '%s'}", uploadedFile.getId());
        httpServletResponse.getWriter().write(fileReference);
    }

    @Data
    class Message {
        private String message;
    }
}
