package com.udacity.jwdnd.course1.cloudstorage.model;

public class HomeForm {


    private String username;
    private Integer userid;
    private String noteTitle;
    private String noteDescription;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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


}
