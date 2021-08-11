package com.udacity.jwdnd.course1.cloudstorage.dto;

/**
 * Credential Data Transfer Object to map data from Credential modal to CredentialService layer
 */
public class CredentialDTO {

    //Instance fields: credentialId, url, userName, key, password, userId
    private String credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private String userId;

    //Getters and Setters for instance fields below

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
