package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {

    //capture messageText box
    @FindBy(id="messageText")
    private WebElement textField;

    @FindBy(id="submitMessage")
    private WebElement submitButton;

    @FindBy(className = "chatMessageUsername")
    private WebElement firstMessageUsername;

    @FindBy(className = "chatMessageText")
    private WebElement firstMessageText;

    private JavascriptExecutor javascriptExecutor;

    //Initialization of chatPage elements
    public ChatPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
    }

    public String getDisplayMessageText(){
        return textField.getText();
    }


    public void submitMessage(String msgToSend){
        System.out.println("Preparing to send: " + msgToSend);
        javascriptExecutor.executeScript("arguments[0].value='" + msgToSend + "';", textField);
        //this.textField.sendKeys(msgToSend);
        System.out.println("Submitting: " + msgToSend);
        //this.submitButton.click();
        javascriptExecutor.executeScript("arguments[0].click();", submitButton);




    }

    public ChatMessage getFirstMessage(){
        ChatMessage result = new ChatMessage();
        result.setUsername(firstMessageUsername.getText());
        result.setMessageText(firstMessageText.getText());
        return result;
    }


}
