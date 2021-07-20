package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
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

    public List<NoteForm> getNotes(Integer userid){
        return noteMapper.getNotes(userid);
    }


    public void createNote(NoteForm noteForm){

        noteMapper.createNote(noteForm);
    }

    public void deleteNote(Integer noteid){
        noteMapper.deleteNote(noteid);

    }

    public void updateNote(NoteForm noteForm){
        noteMapper.updateNote(noteForm);
    }

    public String getUserNameForNote(Integer noteid){
        Integer userid = noteMapper.getUserIdFromNote(noteid);
        return userService.getUsernameForId(userid);
    }

    public boolean lookupNote(Integer noteid){
        return noteMapper.getNote(noteid)!=null;
    }



}
