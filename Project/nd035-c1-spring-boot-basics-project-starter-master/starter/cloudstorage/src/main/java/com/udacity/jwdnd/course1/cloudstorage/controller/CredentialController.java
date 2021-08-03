package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;


    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("add-credential")
    public String postCredential(@ModelAttribute("newCredential")CredentialForm credentialForm, Model model){



        boolean newCred = credentialService.postCredential(credentialForm);



        if(newCred){
            model.addAttribute("credentials", credentialService.getCredentials());
            model.addAttribute("result", "credCreated");
        } else{
            model.addAttribute("result", "credUpdated");
        }

        model.addAttribute("encryptionService", encryptionService);

        return "result";
    }


    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId,
                                   Model model){
        System.out.println("Selected noteId is " + credentialId);

        String usernameForNote = credentialService.getUserNameForCredential(credentialId);

        String loggedInUsername = userService.getUsernameForId(userService.getLoggedInUsersId());

        model.addAttribute("credentials", credentialService.getCredentials());
        model.addAttribute("encryptionService", encryptionService);

        if (credentialService.lookupCredential(credentialId) && usernameForNote.equals(loggedInUsername)){
            credentialService.deleteCredential(credentialId);
            model.addAttribute("result", "credDeleted");
        } else{
            model.addAttribute("result", "credNotDeleted");
        }

        return "result";
    }
}
