package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NoteService {


    private NoteMapper noteMapper;

    private UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService){

        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating HomeService bean");
    }

    public List<NoteForm> getNotes(){
        return noteMapper.getNotes(userService.getLoggedInUsersId());
    }

    public NoteForm getNote(Integer noteid){
        return noteMapper.getNote(noteid);
    }


    public boolean postNote(NoteForm noteForm){
        //flag to check if a new Note is being created.
        boolean newNote;
        Note note = new Note();

        note.setNotetitle(noteForm.getNotetitle());
        note.setNotedescription(noteForm.getNotedescription());
        note.setUserid(userService.getLoggedInUsersId());

        if (noteForm.getNoteid()==0){
            noteMapper.createNote(note);
            newNote = true;
        } else{
            note.setNoteId(noteForm.getNoteid());
            noteMapper.updateNote(note);
            newNote = false;
        }
        return newNote;
    }

    public void deleteNote(Integer noteid){
        noteMapper.deleteNote(noteid);

    }

    public String getUserNameForNote(Integer noteid){
        Integer userid = noteMapper.getUserIdFromNote(noteid);
        return userService.getUsernameForId(userid);
    }

    public boolean lookupNote(Integer noteid){
        return noteMapper.getNote(noteid)!=null;
    }



}
