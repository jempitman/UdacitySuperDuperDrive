package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.HomeForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteList;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class HomeService {


    private NoteMapper noteMapper;

    public HomeService(NoteMapper noteMapper){

        this.noteMapper = noteMapper;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating HomeService bean");
    }

    public List<NoteList> getNotes(){
        return noteMapper.getAllNotes();
    }


    public void addNote(HomeForm homeForm){
        NoteList newNote = new NoteList();
        //newNote.setUserid(homeForm.getUserid());
        //System.out.println("Userid: " + newNote.getUserid());
        newNote.setNotetitle(homeForm.getNoteTitle());
        System.out.println("Notetitle: " + newNote.getNotetitle());
        newNote.setNotedescription(homeForm.getNoteDescription());
        System.out.println("Notedescription: " + newNote.getNotedescription());
        noteMapper.createNote(newNote);
    }

    public void deleteNote(HomeForm homeForm){

    }




}
