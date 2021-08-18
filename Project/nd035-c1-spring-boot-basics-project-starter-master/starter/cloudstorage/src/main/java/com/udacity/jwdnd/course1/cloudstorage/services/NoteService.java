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

    public List<Note> getNoteList(Integer userId){
        return noteMapper.getNoteList(userId);
    }

    public Note getNote(Integer noteId){
        return noteMapper.getNote(noteId);
    }

    //create or update note
    public boolean postNote(NoteDTO noteDTO){
        //flag to check if a new Note is being created.
        boolean newNote;
        Note note = new Note();

        note.setNoteTitle(noteDTO.getNoteTitle());
        //System.out.println(note.getNoteTitle());
        note.setNoteDescription(noteDTO.getNoteDescription());
        //System.out.println(note.getNoteDescription());
        note.setUserId(userService.getLoggedInUsersId());
        //System.out.println(note.getUserId());

        //check if note is being created or updated
        if (noteDTO.getNoteId().isEmpty()){
            noteMapper.createNote(note);
            newNote = true;
        } else{
            note.setNoteId(Integer.parseInt(noteDTO.getNoteId()));
            noteMapper.updateNote(note);
            newNote = false;
        }

        //System.out.println("Note not added to db");
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

    public boolean duplicateNoteCheck(NoteDTO noteDTO, int userId){
        boolean duplicateNote = false;

        String noteTitle = noteDTO.getNoteTitle();
        String noteDescription = noteDTO.getNoteDescription();

        List<Note> noteList = getNoteList(userId);

        for (Note note : noteList){
            assert noteTitle != null;
            if (noteTitle.equals(note.getNoteTitle())){
                if (noteDescription.equals(note.getNoteDescription())){
                    duplicateNote = true;
                    //System.out.println("duplicate Note found");
                    break;
                }
            }
        }
        return duplicateNote;
    }

}
