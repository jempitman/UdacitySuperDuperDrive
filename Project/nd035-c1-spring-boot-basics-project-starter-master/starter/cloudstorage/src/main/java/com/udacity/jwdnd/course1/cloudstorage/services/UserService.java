package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Service class to handle user creation requests
 */

@Service
public class UserService {

    //instance fields: userMapper, hashService
    private final UserMapper userMapper;
    private final HashService hashService;

    //Class constructor
    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUser(username) == null;
    }

    public int createUser(UserData user){
        //encrypt password
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        //add user to User database
        return userMapper.createUser(new UserData(null, user.getUsername(), encodedSalt, hashedPassword,
                user.getFirstName(), user.getLastName()));
    }

    public UserData getUser(String username) {
        return userMapper.getUser(username);
    }

    public String getUsernameForId(Integer userid){
        return userMapper.getUsernameId(userid);
    }

    public int getLoggedInUsersId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUser(username).getUserId();
    }

}
