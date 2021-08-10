package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller method to handle credential creation, deletion and update requests from Home page
 */

@Controller
@RequestMapping("credential")
public class CredentialController {

    //instance fields: credentialService, userService and encryption
    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    //Class constructor
    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    //Method to create or update new credential
    @PostMapping("add-credential")
    public String postCredential(@ModelAttribute("newCredential")CredentialForm credentialForm, Model model){

        //Initialize error message
        String errorMsg = null;
        Integer userId = userService.getLoggedInUsersId();

        //flag to check if credential already exists
        boolean newCred = credentialService.postCredential(credentialForm);

        if(newCred){
            errorMsg = "credCreated";
            //display creation result page if credential is new
            model.addAttribute("credentials", credentialService.getCredentialList(userId));
            //display credential creation result page
        } else{
            errorMsg = "credUpdated";
            //display update result page if credential already exists
        }
        model.addAttribute("result", errorMsg);

        //refresh model encryptionService to continue encrypting credential passwords
        model.addAttribute("encryptionService", encryptionService);

        return "result";
    }

    //Method to delete credentials
    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId,
                                   Model model){

        //Initialize errorMsg
        String errorMsg = null;
        Integer userId = userService.getLoggedInUsersId();

        //fetch userName for note
        String usernameForNote = credentialService.getUserNameForCredential(credentialId);

        //fetch userName
        String loggedInUsername = userService.getUsernameForId(userService.getLoggedInUsersId());

        //update credentialList
        model.addAttribute("credentials", credentialService.getCredentialList(userId));
        model.addAttribute("encryptionService", encryptionService);

        //check if ID of credential to be deleted matches an existing credential in the Credentials database
        if (credentialService.lookupCredential(credentialId) && usernameForNote.equals(loggedInUsername)){
            credentialService.deleteCredential(credentialId);
            //display credential deleted result page
            errorMsg = "credDeleted";
        } else{
            //display credential not deleted result page
            errorMsg = "credNotDeleted";
        }

        model.addAttribute("result",errorMsg);

        return "result";
    }
}
