package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * LoginTest helper class to hold WebElements and perform basic filling operations for
 * login.html
 */

public class LoginPage {

    //instance fields
    private final JavascriptExecutor javascriptExecutor;
    private final WebDriverWait wait;

    //Class constructor
    public LoginPage(WebDriver driver, int waitTimeOut){
        PageFactory.initElements(driver, this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, waitTimeOut);
    }

    //Login page WebElements
    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy (id= "inputPassword")
    private WebElement inputPassword;

    @FindBy (id="submit-button")
    private WebElement loginButton;

    @FindBy(id="error-msg")
    private WebElement errorMsg;

    @FindBy(id="logout-msg")
    private WebElement logoutMsg;

    //basic clicking and form filling operations on login page

    public void fillLogin(String username, String password) {
        //System.out.println("Entering username details");

        wait.until(ExpectedConditions.elementToBeClickable(inputUsername));

        javascriptExecutor.executeScript("arguments[0].value='" + username +
                "';", inputUsername);
        //System.out.println("Entering password details");
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", inputPassword);

    }

    public void clickLogin(){
        //System.out.println("clicking Login button");
        javascriptExecutor.executeScript("arguments[0].click();", loginButton);
    }


    public String displayLoginErrorMessage(){
        if(isError()) {
            return errorMsg.getText();
        }else {
            return "NO LOGIN ERROR";
        }
    }

    public boolean isError(){
        return errorMsg.getText().equals("Invalid username or password");

    }

    public String displayLogoutMessage(){
        return logoutMsg.getText();
    }

    public void login(String username, String password){
        fillLogin(username,password);
        clickLogin();
    }


}
