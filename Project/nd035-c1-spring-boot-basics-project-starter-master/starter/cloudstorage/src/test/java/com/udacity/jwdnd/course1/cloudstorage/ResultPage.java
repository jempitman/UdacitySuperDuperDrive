package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage {

    private final JavascriptExecutor javascriptExecutor;
    private final WebDriverWait wait;

    public ResultPage(WebDriver driver, int waitTimeOut) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, waitTimeOut);
    }

    @FindBy(id="noteCreated")
    private WebElement noteCreated;

    @FindBy(css="#nCreationSuccess")
    private WebElement nCreationSuccess;

    public void clickNoteCreationSuccess(){
        javascriptExecutor.executeScript("arguments[0].click()",
                wait.until(ExpectedConditions.elementToBeClickable(nCreationSuccess)));
    }


}
