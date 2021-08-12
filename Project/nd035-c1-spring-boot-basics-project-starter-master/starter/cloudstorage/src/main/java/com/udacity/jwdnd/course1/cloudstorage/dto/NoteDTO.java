package com.udacity.jwdnd.course1.cloudstorage.dto;

/**
 * Note Data Transfer Object to map data from Note Modal to NoteService layer
 */

public class NoteDTO {

    //instance fields: noteId, noteTitle, noteDescription, userId
    private String noteId;
    private String noteTitle;
    private String noteDescription;
    //private String userId;

    //Getters and setters for instance fields below

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    /*
    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

     */

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }


}
