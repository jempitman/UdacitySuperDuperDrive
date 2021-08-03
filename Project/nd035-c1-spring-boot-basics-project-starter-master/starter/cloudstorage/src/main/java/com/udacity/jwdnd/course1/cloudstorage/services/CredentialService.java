package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private UserService userService;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    public List<CredentialForm> getCredentials(){
        return credentialMapper.getCredentials(userService.getLoggedInUsersId());
    }

    public CredentialForm getCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId);
    }

    public boolean postCredential(CredentialForm credentialForm){
        //flag to check is a new Credential is being created
        boolean newCred;
        Credential credential = new Credential();

        credential.setUrl(credentialForm.getUrl());
        credential.setUserName(credentialForm.getUserName());
        credential.setUserid(userService.getLoggedInUsersId());
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(),encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);

        if (credentialForm.getCredentialId().isEmpty()){
            credentialMapper.createCredential(credential);
            newCred = true;
        } else{
            credential.setCredentialId(Integer.parseInt(credentialForm.getCredentialId()));
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

    private String passwordEncryption(CredentialForm credentialForm){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return encryptionService.encryptValue(credentialForm.getPassword(),encodedKey);
    }

}
