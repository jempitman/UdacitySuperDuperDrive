package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("credential")
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("add-credential")
    public String postCredential(@ModelAttribute("newCredential")CredentialForm credentialForm, Model model){


        boolean newCred = credentialService.postCredential(credentialForm);

        if(newCred){
            model.addAttribute("result", "credCreated");
        } else{
            model.addAttribute("result", "credUpdated");
        }

        model.addAttribute("credentials", credentialService.getCredentials());

        return "result";
    }
}
