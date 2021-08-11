package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Service class to handle creation, deletion and updates of user notes
 */

@Service
public class NoteService {

    //instance fields: noteMapper, userService
    private final NoteMapper noteMapper;
    private final UserService userService;

    //Class constructor
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

    //create or update note
    public boolean postNote(NoteDTO noteDTO){
        //flag to check if a new Note is being created.
        boolean newNote;
        Note note = new Note();

        note.setNoteTitle(noteDTO.getNoteTitle());
        note.setNoteDescription(noteDTO.getNoteDescription());
        note.setUserId(userService.getLoggedInUsersId());

        //check if note is being created or updated
        if (noteDTO.getNoteId()==null){
            noteMapper.createNote(note);
            newNote = true;
        } else{
            note.setNoteId(Integer.valueOf(noteDTO.getNoteId()));
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
