package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;

public class FileTest extends LoginTest{

    public FileTest(){

    }

    @Test
    public void emptyFileError(){
        login("FirstName", "LastName", "UserName", "password");
        HomePage homePage = new HomePage(driver, 1);
        homePage.fileTabNavigation();
        homePage.clickUploadFileBtn();

        ResultPage resultPage = new ResultPage(driver, 1);
        resul

    }

}
