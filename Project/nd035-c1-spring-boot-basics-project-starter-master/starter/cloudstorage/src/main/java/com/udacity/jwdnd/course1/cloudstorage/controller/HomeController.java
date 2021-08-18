package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.dto.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.dto.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.FileData;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller class to update file, note and credential lists on Home page
 */

@Controller
@RequestMapping("/home")
public class HomeController {

    //instance fields: noteService, userService, credentialService and fileService
    private final NoteService noteService;
    private final UserService userService;
    private final CredentialService credentialService;
    private final FileService fileService;

    //constructor:
    public HomeController(NoteService noteService, UserService userService, CredentialService credentialService,
                          FileService fileService){
        System.out.println("Creating HomeController");
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    //include fileDTO to prevent BindingResultError
    @ModelAttribute("fileDTO")
    public FileDTO getFileDTO(){
        return new FileDTO();
    }

    @ModelAttribute("noteDTO")
    public NoteDTO getNoteDTO(){
        return new NoteDTO();
    }

    @ModelAttribute("credentialDTO")
    public CredentialDTO getCredentialDTO(){
        return new CredentialDTO();
    }

    @GetMapping
    public String getHomePage(EncryptionService encryptionService,
                              Model model){

        try {

            //Fetching userId credentials from database
            int userId = userService.getLoggedInUsersId();

            //fetch file, note and credential lists from db
            List<Note> noteList = this.noteService.getNoteList(userId);
            List<FileData> fileList = this.fileService.getAllFiles(userId);
            List<CredentialDTO> credentialList = this.credentialService.getCredentialList(userId);

            //Update file, note and credential lists on HomePage
            model.addAttribute("notes", noteList);
            model.addAttribute("credentials", credentialList);
            model.addAttribute("encryptionService",encryptionService);
            model.addAttribute("files", fileList);


        } catch (org.thymeleaf.exceptions.TemplateInputException e){
            model.addAttribute("error", "general");
            return "error";
        }
        //System.out.println("Going to home page");
        return "home";
    }

}
