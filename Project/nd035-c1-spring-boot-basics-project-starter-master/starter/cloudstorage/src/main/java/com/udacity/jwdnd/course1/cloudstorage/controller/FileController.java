package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @ModelAttribute("fileDTO")
    public FileDTO getFileDTO(){
        return new FileDTO();
    }

    @PostMapping("/home/file/newFile")
    public String uploadNewFile(Authentication authentication, Model model, @ModelAttribute("fileDTO")MultipartFile file) throws IOException{
        String errorMsg = null;

        int currentUserId = this.userService.getLoggedInUsersId();

        System.out.println("Name " + file.getName());

        //Edge case handling

        //1. Empty file upload
        if (file.isEmpty()) {
            errorMsg = "emptyFile";
            model.addAttribute("result", errorMsg);
        }

        if (errorMsg == null){
            //upload file to database by fileId:
            //return current fileId if successful:
            int currentFileId = this.fileService.uploadFile(file, currentUserId);
            if (currentFileId <0){
                errorMsg = "uploadError";
            }
        }

        if (errorMsg == null){
            model.addAttribute("result", "uploadSuccess");
        } else{
            model.addAttribute("result", errorMsg);
        }

        return "result";
    }
}
