package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileData;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NoteService noteService;
    private final UserService userService;
    private final CredentialService credentialService;
    private final FileService fileService;


    public HomeController (NoteService noteService, UserService userService, CredentialService credentialService,
                           EncryptionService encryptionService, FileService fileService){
        System.out.println("Creating HomeController");
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @ModelAttribute("fileDTO")
    public FileDTO getFileDTO(){
        return new FileDTO();
    }

    @GetMapping
    public String getHomePage(@ModelAttribute("newNote")NoteForm noteForm,
                              @ModelAttribute("newCredential")CredentialForm credentialForm,
                              EncryptionService encryptionService,
                              Model model){

        //Fetching userId credentials from database
        int userId = userService.getLoggedInUsersId();

        List<NoteForm> noteList = this.noteService.getNoteList(userId);
        List<FileData> fileList = this.fileService.getAllFiles(userId);
        List<CredentialForm> credentialList = this.credentialService.getCredentialList(userId);

        model.addAttribute("notes", noteList);
        model.addAttribute("credentials", credentialList);
        model.addAttribute("encryptionService",encryptionService);
        model.addAttribute("files", fileList);

        //System.out.println("Going to home page");
        return "home";
    }

}
