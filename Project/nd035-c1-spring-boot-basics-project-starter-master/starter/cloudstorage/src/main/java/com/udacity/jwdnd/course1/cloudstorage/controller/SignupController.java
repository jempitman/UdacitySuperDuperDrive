package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UserData;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle requests from signup.html page
 */

@Controller
@RequestMapping("/signup")
public class SignupController {

    //instance fields: userService to create new user in database
    private final UserService userService;

    //Class constructor
    public SignupController (UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String signupView(Model model){
        return "signup";
    }

    //Method to signup user
    @PostMapping
    public String signupUser(@ModelAttribute UserData user, Model model){
        String signupError = null;

        //check whether userName already exists
        if (!userService.isUsernameAvailable(user.getUsername())){
            signupError = "The username already exists";
        }

        //create user and check whether the user database was updated correctly
        if (signupError == null){
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0){
                signupError = "invalidSignup";
                model.addAttribute("error", signupError);
                return "error";
            } else{
                model.addAttribute("signupSuccess", true);
                return "login";
            }
        } else{
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
