package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        //System.out.println("UserId = " + noteForm.getUserid());
        noteService.createNote(new NoteForm(null, noteForm.getNotetitle(), noteForm.getNotedescription(), noteForm.getUserid()));
        model.addAttribute("noteCreated", true);
        return "result";

    }

    @GetMapping("notes/delete")
    public String deleteNote(@RequestParam("noteid") Integer noteid, Model model){
        System.out.println("Selected noteId is " + noteid);

        String usernameForNote = noteService.getUserNameForNote(noteid);

        String loggedInUsername = userService.getUsernameForId(userService.getLoggedInUsersId());

        if (noteService.lookupNote(noteid) && usernameForNote.equals(loggedInUsername)){
            noteService.deleteNote(noteid);
            model.addAttribute("noteDeleted", true);
        } else{
           model.addAttribute("noteNotDeleted", true);
        }

        return "result";
    }









}
