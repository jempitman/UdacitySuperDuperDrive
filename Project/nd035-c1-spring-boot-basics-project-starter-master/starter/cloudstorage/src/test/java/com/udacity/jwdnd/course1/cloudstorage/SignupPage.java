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
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    //capture inputLastName text field
    @FindBy(id= "inputLastName")
    private WebElement inputLastName;

    //capture inputUserName text field
    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    //capture inputPassword text field
    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    //capture signUp button
    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id="error-msg")
    private WebElement errorMsg;

    @FindBy(id="success-msg")
    private WebElement successMsg;

    public void fillSignup(String firstName, String lastName, String userName, String password) {


        javascriptExecutor.executeScript("arguments[0].value='" + firstName +
                "';", inputFirstName);
        javascriptExecutor.executeScript("arguments[0].value='" + lastName +
                "';", inputLastName);
        javascriptExecutor.executeScript("arguments[0].value='" + userName +
                "';", inputUserName);
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", inputPassword);

    }

    public void clickSignup(){
        javascriptExecutor.executeScript("arguments[0].click();", submitButton);

        System.out.println(">>> SignupPage: signed up ...");
    }

    public void clickLogin(){

    }

    public boolean isSuccess(){
        return successMsg != null;
    }

    public boolean isError(){
        return errorMsg != null;
    }

}
