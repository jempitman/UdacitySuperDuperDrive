package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.FileData;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

       try{
            //Initializing the result message
            String resultMsg = null;

            //fetch current UserId
            int currentUserId = this.userService.getLoggedInUsersId();

            //1. checking for duplicity
            boolean duplicateFile = fileService.duplicityCheck(file, currentUserId);

            //Edge case handling
            //2. checking for empty file upload
            if (file.isEmpty()) {
                resultMsg = "emptyFile";
                model.addAttribute("result", resultMsg);
            } else if (duplicateFile){
                resultMsg = "duplicateFile";
                model.addAttribute("result", resultMsg);
            }

            if (resultMsg == null){
                //upload file to database by fileId:
                //return current fileId if successful:
                int currentFileId = this.fileService.uploadFile(file, currentUserId);
                model.addAttribute("result", "uploadSuccess");

                //3. Checking if fileId is non-negative
                if (currentFileId <0){
                    resultMsg = "uploadError";
                }
            } else{
                model.addAttribute("result", resultMsg);
            }

        } catch (MaxUploadSizeExceededException e){

                model.addAttribute("error", "fileSizeError");
                return "error";
        }

        return "result";


    }

    //Delete file from database based on fileId
    @GetMapping("/home/file/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, @ModelAttribute("fileDTO")MultipartFile file, Model model){

        //delete file according to fileId
        fileService.deleteFile(fileId);
        int userId = userService.getLoggedInUsersId();

        //update fileList in model
        model.addAttribute("files", fileService.getAllFiles(userId));
        //update result to
        model.addAttribute("result", "fileDelete");
        return "result";
    }

    //View file
    @GetMapping("/home/file/view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable Integer fileId) {
        //fetching file details according to fileId
        FileData file = fileService.getFileFromFileId(fileId);

        //create new ByteArrayResource to read data from uploaded file
        ByteArrayResource resource = new ByteArrayResource(file.getFileData());
        //fetch file details from ByteArrayResource
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .contentLength(file.getFileSize())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\"" + file.getFileName() + "\"")
                .body(resource);
    }

}
