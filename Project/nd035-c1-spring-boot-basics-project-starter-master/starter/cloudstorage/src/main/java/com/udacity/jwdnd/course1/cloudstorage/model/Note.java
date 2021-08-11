package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Model class to map data from NoteDTO to Notes database
 */

public class Note {

    //instance fields: noteId, noteTitle, noteDescription, userId
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    //Class constructor
    public Note(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    //Empty constructor
    public Note(){

    }

    //Getters and Setters for instance fields

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
