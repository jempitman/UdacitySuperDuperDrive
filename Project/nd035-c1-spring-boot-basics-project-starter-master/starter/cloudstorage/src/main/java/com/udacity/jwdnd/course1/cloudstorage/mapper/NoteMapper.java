package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*

 */

@Mapper
public interface NoteMapper {

    //Return all notes belonging to a particular userid
    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    public List<NoteForm> getNotes(Integer userid);


    //Add new note
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "values (#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int createNote(NoteForm noteForm);

    // Note deletion operation
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void deleteNote(Integer noteid);

    //Update and edit an existing note
    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription} WHERE noteid=#{noteid}")
    void updateNote(NoteForm noteForm);

    //get note according to noteid
    @Select("SELECT * FROM NOTES WHERE noteid=#{noteid}")
    NoteForm getNote(Integer noteId);

    @Select("SELECT userid FROM NOTES WHERE noteid=#{noteid}")
    public Integer getUserIdFromNote(Integer noteid);



}
