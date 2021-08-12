package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class built on top of SignupTest to perform login tests
 */

public class LoginTest extends SignupTest{

    //Class constructor
    public LoginTest() {
    }

    //check that Login page is available to unauthorized users
    @Test
    public void getLoginPage(){
        driver.get(baseURL + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    //Test that unauthorized page requests redirect to the login page
    @Test
    public void loginRedirect(){
        HomePage homePage = new HomePage(driver, 1);
        homePage.logout();

        driver.get(baseURL + "/home");
        Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());

        driver.get(baseURL + "/results");
        Assertions.assertEquals(baseURL + "/login", driver.getCurrentUrl());
    }


    //Login, verify home page is available, then logout and verify home page is no longer available
    @Test
    public void loginOK(){
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver, 1);
        signupPage.fillSignup("firstName", "lastName", "userName", "password");

        signupPage.clickSignup();

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver, 1);
        loginPage.fillLogin("userName", "password");
        loginPage.clickLogin();

        Assertions.assertEquals("Home", driver.getTitle());

        HomePage homePage = new HomePage(driver, 1);
        homePage.logout();

        Assertions.assertEquals("Login", driver.getTitle());

    }

    @Test
    public void failedLogin(){
        HomePage homePage = new HomePage(driver, 1);
        homePage.logout();

        driver.get(baseURL + "/home");
        LoginPage loginPage = new LoginPage(driver, 1);
        loginPage.fillLogin("userName", "password");
        loginPage.clickLogin();

        Assertions.assertTrue(loginPage.isError());
    }


}
