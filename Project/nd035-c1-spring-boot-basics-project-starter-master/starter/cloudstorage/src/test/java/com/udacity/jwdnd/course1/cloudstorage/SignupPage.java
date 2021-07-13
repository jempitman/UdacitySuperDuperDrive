package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    private JavascriptExecutor javascriptExecutor;
    private WebDriver driver;

    public SignupPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
    }

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

    @FindBy(id="signupError")
    private WebElement error;

    @FindBy(id="successfulSignup")
    private WebElement success;

    public void signup(String firstName, String lastName, String userName, String password) {


        javascriptExecutor.executeScript("arguments[0].value='" + firstName +
                "';", inputFirstName);
        javascriptExecutor.executeScript("arguments[0].value='" + lastName +
                "';", inputLastName);
        javascriptExecutor.executeScript("arguments[0].value='" + userName +
                "';", inputUserName);
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", inputPassword);

        javascriptExecutor.executeScript("arguments[0].click();", submitButton);

        System.out.println(">>> SignupPage: signed up ...");

    }

    public boolean isSuccess(){
        return success != null;
    }

    public boolean isError(){
        return error != null;
    }

}
