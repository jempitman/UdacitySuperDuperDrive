package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void postFileServiceConstruct(){
        System.out.println("Creating FileService bean");
    }

    //fetch all files in database according to userId:
    public List<FileData> getAllFiles(int userid){
        return this.fileMapper.getAllFiles(userid);
    }

    public int uploadFile(MultipartFile multipartFile, int userid) throws IOException{
        FileData file = new FileData(null, multipartFile.getOriginalFilename(),
                multipartFile.getContentType(), multipartFile.getSize(),userid, multipartFile.getBytes());

        return this.fileMapper.uploadFile(file);
    }

    public void deleteFile(String fileName){
        fileMapper.deleteFile(fileName);
    }

    public FileData getFileFromFileId(Integer fileId){
        return fileMapper.getFileFromFileId(fileId);
    }



}
