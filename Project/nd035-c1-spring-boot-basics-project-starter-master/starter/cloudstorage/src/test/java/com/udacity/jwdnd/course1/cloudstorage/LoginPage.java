package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
    }

    @FindBy(id="inputusername")
    private WebElement inputUsername;

    @FindBy (id= "inputpassword")
    private WebElement inputPassword;

    @FindBy (id="submit-button")
    private WebElement submitButton;

    @FindBy(id="error-msg")
    private WebElement errorMsg;

    @FindBy(id="logout-msg")
    private WebElement logoutMsg;

    @FindBy(id="signupLink")
    private WebElement signupLink;


    public void login(String username, String password) {
        //System.out.println("Entering username details");


        javascriptExecutor.executeScript("arguments[0].value='" + username +
                "';", inputUsername);
        System.out.println("Entering password details");
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", inputPassword);

        System.out.println("clicking Login button");
        javascriptExecutor.executeScript("arguments[0].click();", submitButton);

        //System.out.println(">>> LoginPage: logging in ...");

    }

    public String displayLoginErrorMessage(){
        if(displayLoginErrorMessage() != null) {
            return errorMsg.getText();
        }else {
            return "NO LOGIN ERROR";
        }
    }


    /*
    public boolean isSuccess(){
        if
    }

     */




    public String displayLogoutMessage(){
        return logoutMsg.getText();
    }


}
