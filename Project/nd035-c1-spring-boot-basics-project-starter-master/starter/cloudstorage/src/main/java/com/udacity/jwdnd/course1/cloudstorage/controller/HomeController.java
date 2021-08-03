package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NoteService noteService;
    private final UserService userService;
    private final CredentialService credentialService;


    public HomeController (NoteService noteService, UserService userService, CredentialService credentialService){
        System.out.println("Creating HomeController");
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomePage(@ModelAttribute("newNote")NoteForm noteForm,
                              @ModelAttribute("newCredential")CredentialForm credentialForm,
                              Model model){

        model.addAttribute("notes", noteService.getNotes());
        model.addAttribute("credentials", credentialService.getCredentials());

        //Fetching userid credentials from database
        int userid = userService.getLoggedInUsersId();

        //updating list of notes shown on home page
        if(noteService.getNotes().size()>0){
            model.addAttribute("notes", noteService.getNotes());
            System.out.println("Notes added: " + noteService.getNotes().size());
        }

        System.out.println("Going to home page");
        return "home";
    }













}
