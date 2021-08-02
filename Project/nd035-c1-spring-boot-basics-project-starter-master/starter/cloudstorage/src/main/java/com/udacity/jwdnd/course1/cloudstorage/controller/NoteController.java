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

        if (newNote){
            model.addAttribute("result", "noteCreated");
        } else{
            model.addAttribute("result", "noteUpdated");
        }

        model.addAttribute("notes", noteService.getNotes());
        //model.addAttribute("result", "success");

        return "result";

    }

    @GetMapping(value = "/get-note/{noteid}")
    public NoteForm getNote(@PathVariable("noteid") Integer noteid){
        return noteService.getNote(noteid);
    }

    @GetMapping(value = "/delete-note/{noteid}")
    public String deleteNote(@PathVariable("noteid") Integer noteid, Model model){
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
