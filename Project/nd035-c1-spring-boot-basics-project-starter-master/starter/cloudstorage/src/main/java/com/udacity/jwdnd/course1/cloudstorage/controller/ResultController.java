package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping()
    public String resultView(){
        return "result";
    }

    /*
    private NoteService noteService;
    private UserService userService;


    public ResultController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
        System.out.println("Created ResultController");
    }

    @PostMapping ("/notes")
    public String postNote(@ModelAttribute(value="noteData") NoteForm noteForm, Model model){

        noteForm.setUserid(userService.getLoggedInUsersId());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //int userId = userService.getUser(username).getUserid();
        //noteForm.setUserid(userId);
       // int noteId = noteForm.getNoteid();

        int noteid = noteForm.getNoteid();

        if (noteid == 0) {
            noteService.createNote(new NoteForm(null, noteForm.getNotetitle(), noteForm.getNotedescription(), noteForm.getUserid()));
            model.addAttribute("noteCreated", true);
        } else {
            noteService.updateNote(noteForm);
           model.addAttribute("noteUpdated", true);
        }

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

    /*
    @GetMapping("notes/update")
    public String updateNote(@RequestParam("noteid") Integer noteid, Model model, NoteForm noteForm){
        System.out.println("Selected noteId is " + noteid);

        String usernameForNote = noteService.getUserNameForNote(noteid);

        String loggedInUsername = userService.getUsernameForId(userService.getLoggedInUsersId());

        if (noteService.lookupNote(noteid) && usernameForNote.equals(loggedInUsername)){

        }

        return "result";
    }

     */


}
