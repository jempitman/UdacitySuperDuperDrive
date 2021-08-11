package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper class to perform SQL operations on Credentials database
 */

@Mapper
public interface CredentialMapper {

    //Return all credentials belonging to a particular userid
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    public List<CredentialDTO> getCredentialList(Integer userId);

    //Add new credential
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) " +
            "VALUES (#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    void createCredential(Credential credential);

    // Credential deletion operation
    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void deleteCredential(Integer noteid);

    //Update and edit an existing credential
    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{userName}, key=#{key}, " +
            "password=#{password} WHERE credentialId=#{credentialId}")
    void updateCredential(Credential credential);

    //get credential according to credentialId
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    CredentialDTO getCredential(Integer credentialId);

    // get userid from credential
    @Select("SELECT userid FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    public Integer getUserIdFromCredential(Integer credentialId);


}
