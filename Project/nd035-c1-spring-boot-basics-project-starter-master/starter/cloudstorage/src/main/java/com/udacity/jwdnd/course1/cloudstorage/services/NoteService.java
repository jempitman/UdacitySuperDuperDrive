package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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

    public List<NoteDTO> getNoteList(Integer userId){
        return noteMapper.getNoteList(userId);
    }

    public NoteDTO getNote(Integer noteid){
        return noteMapper.getNote(noteid);
    }

    public boolean postNote(NoteDTO noteDTO){
        //flag to check if a new Note is being created.
        boolean newNote;
        Note note = new Note();

        note.setNoteTitle(noteDTO.getNoteTitle());
        note.setNoteDescription(noteDTO.getNoteDescription());
        note.setUserId(userService.getLoggedInUsersId());

        if (noteDTO.getNoteId()==0){
            noteMapper.createNote(note);
            newNote = true;
        } else{
            note.setNoteId(noteDTO.getNoteId());
            noteMapper.updateNote(note);
            newNote = false;
        }
        return newNote;
    }

    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }

    public String getUserNameForNote(Integer noteId){
        Integer userId = noteMapper.getUserIdFromNote(noteId);
        return userService.getUsernameForId(userId);
    }

    public boolean lookupNote(Integer noteId){
        return noteMapper.getNote(noteId)!=null;
    }

}
