package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {

    private NoteService noteService;
    private UserService userService;

    public HomeController (NoteService noteService, UserService userService){
        System.out.println("Creating HomeController");
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping("/home")
    public String getHomePage(Model model){

        //Fetching userid credentials from database
        int userid = userService.getLoggedInUsersId();

        //updating list of notes shown on home page
        if(noteService.getNotes(userid).size()>0){
            model.addAttribute("notes", noteService.getNotes(userid));
            System.out.println("Notes added: " + noteService.getNotes(userid).size());
        }

        System.out.println("Going to home page");
        return "home";
    }













}
