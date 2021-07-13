package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends SignupTest{

    public LoginTest() {
    }

    @Test
    public void getLoginPage(){
        driver.get(baseURL + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    /*
    @Test
    public void loginOK(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("username", "password");
        Assertions.assertEquals("Home", driver.getTitle());



    }

     */



}
