package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
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

    @ModelAttribute("credentialDTO")
    public CredentialDTO getCredential(){
        return new CredentialDTO();
    }

    //Method to create or update new credential
    @PostMapping("add-credential")
    public String postCredential(@ModelAttribute("credentialDTO") CredentialDTO credentialDTO, Model model){

        //Initialize error message
        String resultMsg;
        Integer userId = userService.getLoggedInUsersId();

        if (credentialDTO.getCredentialId().isEmpty()){
           resultMsg = "credCreationError";
           model.addAttribute("result", resultMsg);
        }

        //flag to check if credential already exists
        boolean newCred = credentialService.postCredential(credentialDTO);

        if(newCred){
            resultMsg = "credCreated";
            //display creation result page if credential is new
            model.addAttribute("credentials", credentialService.getCredentialList(userId));
            //display credential creation result page
        } else{
            resultMsg = "credUpdated";
            //display update result page if credential already exists
        }
        model.addAttribute("result", resultMsg);


        model.addAttribute("encryptionService", encryptionService);

        return "result";
    }

    //Method to delete credentials
    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId,
                                   Model model){

        //Initialize resultMsg
        String resultMsg = null;
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
            resultMsg = "credDeleted";
        } else{
            //display credential not deleted result page
            resultMsg = "credNotDeleted";
        }

        model.addAttribute("result",resultMsg);

        return "result";
    }
}
