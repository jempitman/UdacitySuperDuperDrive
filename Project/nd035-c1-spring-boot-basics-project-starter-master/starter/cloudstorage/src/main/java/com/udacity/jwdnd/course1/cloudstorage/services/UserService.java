package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUser(username) == null;
    }

    public int createUser(UserData user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.createUser(new UserData(null, user.getUsername(), encodedSalt, hashedPassword,
                user.getFirstname(), user.getLastname()));
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
