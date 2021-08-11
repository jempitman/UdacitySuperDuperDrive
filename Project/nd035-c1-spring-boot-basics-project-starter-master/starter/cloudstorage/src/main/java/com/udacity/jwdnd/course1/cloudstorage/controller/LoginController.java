package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle login requests from login.html
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    //Class constructor
    public LoginController(){
        System.out.println("Creating LoginController");
    }

    @GetMapping()
    public String loginView(){
        //System.out.println(">>> going to login page...");
        return "login";
    }
}
