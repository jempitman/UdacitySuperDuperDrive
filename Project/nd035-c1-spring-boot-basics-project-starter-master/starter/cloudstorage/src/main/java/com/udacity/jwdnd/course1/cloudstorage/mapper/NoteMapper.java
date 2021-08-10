package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.dto.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*

 */

@Mapper
public interface NoteMapper {

    //Return all notes belonging to a particular userid
    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    public List<NoteDTO> getNoteList(Integer userId);

    //Add new note
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) " +
            "VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void createNote(Note note);

    // Note deletion operation
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(@Param("noteId") int noteId);

    //Update and edit an existing note
    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid=#{noteId}")
    void updateNote(Note note);

    //get note according to noteid
    @Select("SELECT * FROM NOTES WHERE noteid=#{noteId}")
    NoteDTO getNote(Integer noteId);

    @Select("SELECT userid FROM NOTES WHERE noteid=#{noteId}")
    public Integer getUserIdFromNote(Integer noteId);

}
