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

    @FindBy(css="#inputUsername")
    private WebElement inputUsername;

    @FindBy (css="#inputPassword")
    private WebElement inputPassword;

    @FindBy (css="#submit-button")
    private WebElement submitButton;

    @FindBy(id="error-msg")
    private WebElement error;

    @FindBy(id="logout-msg")
    private WebElement logout;


    public void login(String username, String password) {
        System.out.println("Entering username details");


        javascriptExecutor.executeScript("arguments[0].value='" + username +
                "';", inputUsername);
        System.out.println("Entering password details");
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", inputPassword);

        System.out.println("clicking Login button");
        javascriptExecutor.executeScript("arguments[0].click();", submitButton);

        System.out.println(">>> LoginPage: logging in ...");

    }

    public boolean isError(){
        return error != null;
    }

    public boolean isLogout(){
        return logout != null;
    }


}
