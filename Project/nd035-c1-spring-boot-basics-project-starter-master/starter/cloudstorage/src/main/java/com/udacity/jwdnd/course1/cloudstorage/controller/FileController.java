package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.FileData;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Controller class to handle file upload, viewing and deletion requests on the Home page
 */

@Controller
public class FileController {

    //instance fields: FileService and UserService
    private final FileService fileService;
    private final UserService userService;

    //constructor:
    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    //fetch FileDTO so that file data can be mapped to FileController
    @ModelAttribute("fileDTO")
    public FileDTO getFileDTO(){
        return new FileDTO();
    }

    //method to upload a new file
    @PostMapping("/home/file/newFile")
    public String uploadNewFile(Authentication authentication, Model model,
                                @ModelAttribute("fileDTO")MultipartFile file) throws IOException{

        //Initializing the error message
        String errorMsg = null;

        //fetch current UserId
        int currentUserId = this.userService.getLoggedInUsersId();

        //1. checking for duplicity
        boolean duplicateFile = fileService.duplicityCheck(file, currentUserId);

        //Edge case handling
        //2. checking for empty file upload
        if (file.isEmpty()) {
            errorMsg = "emptyFile";
            model.addAttribute("result", errorMsg);
        } else if (duplicateFile){
            errorMsg = "duplicateFile";
            model.addAttribute("result", errorMsg);
        }

        if (errorMsg == null){
            //upload file to database by fileId:
            //return current fileId if successful:
            int currentFileId = this.fileService.uploadFile(file, currentUserId);
            model.addAttribute("result", "uploadSuccess");

            //3. Checking fileId is non-negative
            if (currentFileId <0){
                errorMsg = "uploadError";
            }
        } else{
            model.addAttribute("result", errorMsg);
        }

        return "result";
    }

    //Delete file from database based on fileId
    @GetMapping("/home/file/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, @ModelAttribute("fileDTO")MultipartFile file, Model model){

        String fileName = fileService.getFileFromFileId(fileId).getFileName();
        fileService.deleteFile(fileName);

        model.addAttribute("files", fileService.getAllFiles(userService.getLoggedInUsersId()));
        model.addAttribute("result", "fileDelete");
        return "result";
    }

    //View file
    @GetMapping("/home/file/view/{fileId}")
    public StreamingResponseBody viewFile(HttpServletResponse response, @PathVariable Integer fileId) throws IOException{
        FileData file = fileService.getFileFromFileId(fileId);
        response.setContentType(file.getContentType());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\"" + file.getFileName());
        return outputStream -> {
            int bytesRead;
            byte[] buffer = new byte[10000];
            InputStream inputStream = new ByteArrayInputStream(file.getFileData());
            while((bytesRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0,bytesRead);
            }
        };
    }


}
