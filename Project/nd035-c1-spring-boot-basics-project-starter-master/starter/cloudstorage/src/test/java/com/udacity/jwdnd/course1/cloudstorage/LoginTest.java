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


    @Test
    public void loginOK(){
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("username", "password");

        HomePage homePage = new HomePage(driver);
        System.out.println("Landed on Home page");
        //homePage.logout();
        //Assertions.assertEquals("You have successfully logged out", loginPage.displayLogoutMessage());




        //driver.get(baseURL + "/home");

        //Assertions.assertEquals("Home", driver.getTitle());
        //Assertions.assertEquals("NO LOGIN ERROR", loginPage.displayLoginErrorMessage());
    }



}
