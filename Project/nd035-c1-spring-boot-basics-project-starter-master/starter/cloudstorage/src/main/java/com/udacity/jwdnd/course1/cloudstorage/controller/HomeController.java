package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.HomeForm;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private HomeService homeService;

    public HomeController (HomeService homeService){
        System.out.println("Creating HomeController");
        this.homeService = homeService;
    }

    @GetMapping
    public String getHomePage(HomeForm homeForm, Model model){
        System.out.println("Going to home page");
        model.addAttribute("notes", homeService.getNotes());
        return "home";
    }

    @PostMapping
    public String createNote(Authentication authentication, HomeForm homeForm, Model model){
        homeForm.setUsername(authentication.getName());
        this.homeService.addNote(homeForm);
        homeForm.setNoteTitle("");
        homeForm.setNoteDescription("");
        model.addAttribute("notes", this.homeService.getNotes());
        return "home";



    }








}
