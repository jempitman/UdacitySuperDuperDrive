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

    //Test that unauthorized page requests redirect to the login page
    @Test
    public void loginRedirect(){
        driver.get(baseURL + "/home");
        Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());

        driver.get(baseURL + "/results");
        Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
    }


    //Login, verify home page is available, then logout and verify home page is no longer available
    @Test
    public void loginOK() throws InterruptedException{
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.fillSignup("firstName", "lastName", "userName", "password");
        Thread.sleep(1000);

        signupPage.clickSignup();

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLogin("userName", "password");
        loginPage.clickLogin();

        Thread.sleep(1000);

        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver);
        homePage.logout();

        Thread.sleep(1000);
        Assertions.assertEquals("Login", driver.getTitle());

    }

    @Test
    public void failedLogin() throws InterruptedException{

        driver.get(baseURL + "/home");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLogin("userName", "password");
        loginPage.clickLogin();

        Thread.sleep(1000);

        Assertions.assertTrue(loginPage.isError());
    }





}
