package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CredentialTest extends LoginTest{

    //private EncryptionService encryptionService;

    public CredentialTest(){
        //this.encryptionService = encryptionService;

    }

    @Test
    public void addNewCredential() throws InterruptedException{


        login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.credTabNavigation();
        String url = "http://localhost:8080/home";
        String userName = "UserName";
        String password = "password";

        createCred(url, userName,password, homePage);

        System.out.println("Credential created");

        homePage.credTabNavigation();

        Credential credential = homePage.getCredList();

        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(userName, credential.getUserName());
        Assertions.assertNotEquals(password, credential.getPassword());

        deleteCred(homePage);
        homePage.logout();
    }

    @Test
    public void addCredAndEdit() throws InterruptedException{

        login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.credTabNavigation();
        String url = "http://localhost:";
        String userName = "UserName";
        String password = "password";

        createCred(url,userName,password, homePage);

        homePage.credTabNavigation();

        Credential originalCredential = homePage.getCredList();

        String originalEncryptedPassword = originalCredential.getPassword();

        String updatedUrl = "http://localhost:8080/login";
        String updatedUserName = "JPitman";
        String updatedPassword = "UpdatedPassword";

        homePage.credTabNavigation();

        updateCred(updatedUrl,updatedUserName,updatedPassword, homePage);

        homePage.credTabNavigation();

        Credential updatedCredential = homePage.getCredList();

        Assertions.assertEquals(updatedUrl, updatedCredential.getUrl());
        Assertions.assertEquals(updatedUserName, updatedCredential.getUserName());
        Assertions.assertNotEquals(originalEncryptedPassword, updatedCredential.getPassword());

        deleteCred(homePage);
        homePage.logout();
    }

    @Test
    public void deleteCredTest() throws InterruptedException{

        login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.credTabNavigation();
        String url = "http://localhost:8080/home";
        String userName = "UserName";
        String password = "password";

        createCred(url, userName, password, homePage);
        homePage.credTabNavigation();

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
