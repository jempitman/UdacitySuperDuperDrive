package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {

    private NoteService noteService;
    private UserService userService;

    public ResultController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
        System.out.println("Created ResultController");
    }


    @PostMapping ("/notes")
    public String createNote(@ModelAttribute(value="newNote") NoteForm noteForm, Model model){

        noteForm.setUserid(userService.getLoggedInUsersId());

        noteService.addNote(new NoteForm(null, noteForm.getNotetitle(), noteForm.getNotedescription(), noteForm.getUserid()));
        model.addAttribute("noteCreated", true);
        return "result";

    }





}
