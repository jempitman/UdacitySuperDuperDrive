package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    //Fetch all files from database according to userId
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<FileData> getAllFiles(int userid);

    //Upload new file to database
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFile(FileData file);

    //Delete file from database
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);

    //Selectr
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    FileData getFileFromFileId(Integer fileId);

}
