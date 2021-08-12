package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class built on top of LoginTest to perform credential creation, edit and deletion tests
 */
public class CredentialTest extends LoginTest{

    //Class constructor
    public CredentialTest(){

    }

    @Test
    public void testCredAdd() {

        HomePage homePage= new HomePage(driver, 1);
        homePage.credTabNavigation();
        String url = "http://localhost:8080";
        String userName = "UserName";
        String password = "password";

        //Clear credentialList so that test can be run individually or by class
        if (!homePage.emptyCredList(driver)){
            deleteCred(homePage);
        }

        createCred(url, userName,password, homePage);

        System.out.println("Credential created");

        homePage.credTabNavigation();

        Credential credential = homePage.getCredList();

        //Verify that url, userName and password details in db match the submitted data
        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(userName, credential.getUserName());
        Assertions.assertNotEquals(password, credential.getPassword());

        homePage.logout();
    }

    @Test
    public void testCredEdit(){

        HomePage homePage= new HomePage(driver, 1);
        homePage.credTabNavigation();

        //Create new credentialList if one does not exist already
        if (homePage.emptyCredList(driver)){
            String url = "http://localhost:";
            String userName = "UserName";
            String password = "password";

            createCred(url,userName,password, homePage);
            homePage.credTabNavigation();
        } else {
            //Edit an existing credential
            Credential existingCredential = homePage.getCredList();
            String existingEncryptedPassword = existingCredential.getPassword();

            String updatedUrl = "http://localhost:8080/login";
            String updatedUserName = "JPitman";
            String updatedPassword = "UpdatedPassword";

            homePage.credTabNavigation();

            updateCred(updatedUrl,updatedUserName,updatedPassword, homePage);

            homePage.credTabNavigation();

            Credential updatedCredential = homePage.getCredList();
            //System.out.println(updatedCredential.getPassword());

            //Verify updated credential details in db match the submitted data
            Assertions.assertEquals(updatedUrl, updatedCredential.getUrl());
            Assertions.assertEquals(updatedUserName, updatedCredential.getUserName());
            //Verify that encrypted version of updated password does not match the encrypted version of the old password
            Assertions.assertNotEquals(existingEncryptedPassword, updatedCredential.getPassword());
        }

        homePage.logout();
    }

    @Test
    public void testCredDelete(){

        HomePage homePage= new HomePage(driver, 1);
        homePage.credTabNavigation();

        //create a credential if one does not already exist
        if (homePage.emptyCredList(driver)){
            String url = "http://localhost:8080/home";
            String userName = "UserName";
            String password = "password";

            createCred(url, userName, password, homePage);
            homePage.credTabNavigation();
        }

        Assertions.assertFalse(homePage.emptyCredList(driver));

        deleteCred(homePage);
        homePage.credTabNavigation();

        Assertions.assertTrue(homePage.emptyCredList(driver));
    }

    private void createCred(String url, String userName, String password, HomePage homePage){
        homePage.credTabNavigation();
        homePage.clickAddNewCred();
        homePage.fillNewCred(url, userName, password);
        homePage.clickSubmitCred();
        ResultPage resultPage = new ResultPage(driver,1);
        resultPage.clickCredCreationSuccess();
    }

    private void deleteCred(HomePage homePage){
        homePage.clickDeleteCred();
        ResultPage resultPage = new ResultPage(driver, 1);
        resultPage.clickCredDeleteSuccess();
    }

    private void updateCred(String url, String userName, String password, HomePage homePage){
        homePage.clickEditCred();
        homePage.clearCredUrl();
        homePage.clearCredUserName();
        homePage.clearCredPassword();
        homePage.fillNewCred(url, userName, password);
        homePage.clickSubmitCred();
        ResultPage resultPage = new ResultPage(driver, 1);
        resultPage.clickCredEditSuccess();
    }
}
