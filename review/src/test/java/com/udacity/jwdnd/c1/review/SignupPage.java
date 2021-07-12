package com.udacity.jwdnd.c1.review;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    //capture inputFirstName
    @FindBy(css = "#inputFirstName")
    private WebElement inputFirstName;

    //capture inputLastName text field
    @FindBy(css= "#inputLastName")
    private WebElement inputLastName;

    //capture inputUserName text field
    @FindBy(css = "#inputUsername")
    private WebElement inputUserName;

    //capture inputPassword text field
    @FindBy(css = "#inputPassword")
    private WebElement inputPassword;

    //capture signUp button
    @FindBy(css = "#submit-button")
    private WebElement submitButton;


    private JavascriptExecutor javascriptExecutor;

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
    }

    public String getFirstName(){
        return inputFirstName.getText();
    }

    public String getLastName(){
        return inputLastName.getText();
    }

    public String getUserName(){
        return inputUserName.getText();
    }

    public String getPassword(){
        return inputPassword.getText();
    }

    public void signUpClick(){
        submitButton.click();
    }

    public void signup(String firstName, String lastName, String userName, String password){


        javascriptExecutor.executeScript("arguments[0].value='" + firstName +
                "';", inputFirstName);
        javascriptExecutor.executeScript("arguments[0].value='" + lastName +
                "';", inputLastName);
        javascriptExecutor.executeScript("arguments[0].value='" + userName +
                "';", inputUserName);
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", inputPassword);

        javascriptExecutor.executeScript("arguments[0].click();", submitButton);



        /*

        this.inputFirstName.sendKeys(firstName);

        this.inputLastName.sendKeys(lastName);

        this.inputUserName.sendKeys(userName);

        this.inputPassword.sendKeys(password);

        this.submitButton.click();

         */


    }



}
