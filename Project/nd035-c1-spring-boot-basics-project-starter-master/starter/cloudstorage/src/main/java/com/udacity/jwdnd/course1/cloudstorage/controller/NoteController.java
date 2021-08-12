package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.dto.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService){
        this.noteService = noteService;
        this.userService = userService;
        System.out.println("Created NoteController");
    }

    @ModelAttribute("noteDTO")
    public NoteDTO getNoteDTO(){
        return new NoteDTO();
    }

    @PostMapping("add-note")
    public String postNote(@ModelAttribute("noteDTO") NoteDTO noteDTO, Model model){

        boolean newNote = noteService.postNote(noteDTO);

        Integer userId = userService.getLoggedInUsersId();


        model.addAttribute("notes", noteService.getNoteList(userId));
        String resultMsg;

        if (newNote){
            resultMsg = "noteCreated";

        } else{
            resultMsg = "noteUpdated";
        }
        model.addAttribute("result", resultMsg);

        System.out.println();
        return "result";

    }

    @GetMapping(value = "/get-note/{noteId}")
    public Note getNote(@PathVariable("noteId") Integer noteId){
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
