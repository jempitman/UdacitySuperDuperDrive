package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("note")
public class NoteController {

    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
        System.out.println("Created NoteController");
    }

    @PostMapping("add-note")
    public String postNote(@ModelAttribute("newNote") NoteForm noteForm, Model model){

        boolean newNote = noteService.postNote(noteForm);
        Integer userId = userService.getLoggedInUsersId();


        if (newNote){
            model.addAttribute("result", "noteCreated");
        } else{
            model.addAttribute("result", "noteUpdated");
        }

        model.addAttribute("notes", noteService.getNoteList(userId));
        //model.addAttribute("result", "success");

        return "result";

    }

    @GetMapping(value = "/get-note/{noteId}")
    public NoteForm getNote(@PathVariable("noteId") Integer noteId){
        return noteService.getNote(noteId);
    }

    @GetMapping(value = "/delete-note/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId, Model model){
        System.out.println("Selected noteId is " + noteId);

        String usernameForNote = noteService.getUserNameForNote(noteId);

        String loggedInUsername = userService.getUsernameForId(userService.getLoggedInUsersId());

        if (noteService.lookupNote(noteId) && usernameForNote.equals(loggedInUsername)){
            noteService.deleteNote(noteId);
            model.addAttribute("result", "noteDeleted");
        } else{
            model.addAttribute("result", "noteNotDeleted");
        }

        return "result";
    }


}
