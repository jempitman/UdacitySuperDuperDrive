package com.udacity.jwdnd.course1.cloudstorage.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * File Data transfer object to map multipart file data from file tab to FileService layer
 */

public class FileDTO {

    private MultipartFile file;

    public MultipartFile getFile(){
        return file;
    }

    public void setFile(MultipartFile file){
        this.file = file;
    }

}
