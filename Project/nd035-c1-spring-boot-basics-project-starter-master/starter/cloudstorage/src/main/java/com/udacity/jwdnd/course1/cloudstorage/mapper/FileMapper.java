package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileData;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper class to perform SQL operations on File database
 */

@Mapper
public interface FileMapper {

    //Fetch all files from database according to userId
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<FileData> getAllFiles(int userId);

    //Upload new file to database
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFile(FileData file);

    //Delete file from database
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);

    //Selectr
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    FileData getFileFromFileId(Integer fileId);

}
