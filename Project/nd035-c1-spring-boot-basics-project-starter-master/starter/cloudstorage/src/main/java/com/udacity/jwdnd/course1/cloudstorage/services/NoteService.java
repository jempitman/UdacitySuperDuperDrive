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


    public void addNote(NoteForm noteForm){
        //NoteForm newNote = new NoteForm();
        //newNote.setUserid(homeForm.getUserid());
        //System.out.println("Userid: " + newNote.getUserid());
        //newNote.setNotetitle(noteForm.getNotetitle());
        //System.out.println("Notetitle: " + newNote.getNotetitle());
        //newNote.setNotedescription(noteForm.getNotedescription());
        //System.out.println("Notedescription: " + newNote.getNotedescription());
        noteMapper.createNote(noteForm);
    }

    public void deleteNote(Integer noteid){
        noteMapper.deleteNote(noteid);

    }




}
