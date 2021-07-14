package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    NoteList getNote(String noteid);

    @Select("SELECT * FROM NOTES")
    List<NoteList> getAllNotes();

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) values (#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int createNote(NoteList note);



}
