package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

/**
 * Service class to perform credential creation, update and deletion tasks
 */
@Service
public class CredentialService {

    //instance fields: credentialMapper, userService, encryptionService
    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final EncryptionService encryptionService;

    //Class constructor
    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    //fetch credential list from database
    public List<CredentialDTO> getCredentialList(Integer userId){
        return credentialMapper.getCredentialList(userId);
    }

    public CredentialDTO getCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId);
    }

    //create credential
    public boolean postCredential(CredentialDTO credentialDTO){
        //flag to check is a new Credential is being created
        boolean newCred;
        Credential credential = new Credential();

        credential.setUrl(credentialDTO.getUrl());
        credential.setUserName(credentialDTO.getUserName());
        credential.setUserId(userService.getLoggedInUsersId());

        //encrypt password
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentialDTO.getPassword(),encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);

        //check if credential is being created or updated
        if (credentialDTO.getCredentialId().isEmpty()){
            credentialMapper.createCredential(credential);
            newCred = true;
        } else{
            credential.setCredentialId(Integer.parseInt(credentialDTO.getCredentialId()));
            credentialMapper.updateCredential(credential);
            newCred = false;
        }

        return newCred;
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }

    public String getUserNameForCredential(Integer credentialId){
        Integer userid = credentialMapper.getUserIdFromCredential(credentialId);
        return userService.getUsernameForId(userid);
    }

    public boolean lookupCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId)!=null;
    }

    public boolean duplicateUsernameCheck(CredentialDTO credDTO, int userId){

        boolean duplicateUsername = false;
        String credUsername = credDTO.getUserName();
        List<CredentialDTO> credentialDTOList = getCredentialList(userId);

        for (CredentialDTO credentialDTO : credentialDTOList){
            assert credUsername != null;
            if (credUsername.equals(credentialDTO.getUserName())){
                duplicateUsername = true;
                //System.out.println("duplicate Note found");
                break;
            }
        }
        return duplicateUsername;
    }

}
