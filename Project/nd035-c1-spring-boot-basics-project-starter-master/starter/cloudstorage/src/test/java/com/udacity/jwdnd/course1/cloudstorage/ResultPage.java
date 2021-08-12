package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * CredentialTest and NoteTest helper class to hold WebElements and perform click
 * functions on result.html
 */

public class ResultPage {

    //instance fields: JavascriptExecutor and WebDriverWait
    private final JavascriptExecutor javascriptExecutor;
    private final WebDriverWait wait;

    //Class constructor
    public ResultPage(WebDriver driver, int waitTimeOut) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, waitTimeOut);
    }

    //WebElements
    @FindBy(id="noteCreated")
    private WebElement noteCreated;

    @FindBy(id="nCreationSuccess")
    private WebElement nCreationSuccess;

    @FindBy(id="cCreationSuccess")
    private WebElement cCreationSuccess;

    @FindBy(id="nEditSuccess")
    private WebElement nEditSuccess;

    @FindBy(id="nDeleteSuccess")
    private WebElement nDeleteSuccess;

    @FindBy(id="cDeleteSuccess")
    private WebElement cDeleteSuccess;

    @FindBy(id="cEditSuccess")
    private WebElement cEditSuccess;

    //Click functions for various success messages
    public void clickNoteCreationSuccess(){
        javascriptExecutor.executeScript("arguments[0].click()",
                wait.until(ExpectedConditions.elementToBeClickable(nCreationSuccess)));
    }

    public void clickCredCreationSuccess(){
        javascriptExecutor.executeScript("arguments[0].click()",
                wait.until(ExpectedConditions.elementToBeClickable(cCreationSuccess)));
    }

    public void clickNoteEditSuccess(){
        javascriptExecutor.executeScript("arguments[0].click()",
                wait.until(ExpectedConditions.elementToBeClickable(nEditSuccess)));
    }

    public void clickNoteDeleteSuccess(){
        javascriptExecutor.executeScript("arguments[0].click()",
                wait.until(ExpectedConditions.elementToBeClickable(nDeleteSuccess)));
    }

    public void clickCredDeleteSuccess(){
        javascriptExecutor.executeScript("arguments[0].click()",
                wait.until(ExpectedConditions.elementToBeClickable(cDeleteSuccess)));
    }

    public void clickCredEditSuccess(){
        javascriptExecutor.executeScript("arguments[0].click()",
                wait.until(ExpectedConditions.elementToBeClickable(cEditSuccess)));
    }


}
