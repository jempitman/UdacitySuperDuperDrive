package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css="#inputUsername")
    private WebElement inputUsername;

    @FindBy (css="#inputPassword")
    private WebElement inputPassword;

    @FindBy (css="#submit-button")
    private WebElement submitButton;



    private JavascriptExecutor javascriptExecutor;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
    }

    public String getUserName(){
        return inputUsername.getText();
    }

    public String getPassword(){
        return inputPassword.getText();
    }

    public void submitButtonClick(){
        submitButton.click();
    }

    public void setUserName(String userName){
        inputUsername.clear();
        inputUsername.sendKeys(userName);
    }


    public void login(String user, String password) {
        System.out.println("Entering username details");


        javascriptExecutor.executeScript("arguments[0].value='" + user +
                "';", inputUsername);
        System.out.println("Entering password details");
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", inputPassword);

        System.out.println("clicking button");
        javascriptExecutor.executeScript("arguments[0].click();", submitButton);

        System.out.println(">>> LoginPage: logging in ...");


    }
}
