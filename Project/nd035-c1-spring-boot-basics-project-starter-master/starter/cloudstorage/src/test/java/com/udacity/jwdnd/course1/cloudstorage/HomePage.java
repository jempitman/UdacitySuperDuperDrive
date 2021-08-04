package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    private final JavascriptExecutor javascriptExecutor;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver, int waitTimeOut){
        PageFactory.initElements(driver, this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, waitTimeOut);
    }

    @FindBy(id="logout")
    private WebElement logoutButton;

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="nav-credentials-tab")
    private WebElement credTab;

    @FindBy(id="new-note")
    private WebElement newNoteButton;

    @FindBy(id="new-cred")
    private WebElement newCredButton;

    @FindBy(id="note-title")
    private WebElement nTitle;

    @FindBy(id="note-description")
    private WebElement nDescription;

    @FindBy(id="noteSubmit")
    private WebElement submitNoteButton;

    @FindBy(id="credentialSubmit")
    private WebElement submitCredButton;

    @FindBy(id="credential-url")
    private WebElement credUrl;

    @FindBy(id="credential-username")
    private WebElement credUsername;

    @FindBy(id="credential-password")
    private WebElement credPassword;

    @FindBy(className="listNoteTitle")
    private List<WebElement> tableHeader;

    @FindBy(className="listNoteDescription")
    private List<WebElement> tableData;

    @FindBy(id="noteEdit")
    private WebElement noteEditButton;

    @FindBy(id="noteDelete")
    private WebElement noteDeleteButton;

    @FindBy(id="noteTableTitle")
    private WebElement noteTableTitle;

    @FindBy(id="noteTableDescription")
    private WebElement noteTableDescription;

    @FindBy(css="#credTableUrl")
    private WebElement credTableUrl;

    @FindBy(id="credTableUserName")
    private WebElement credTaleUsername;

    @FindBy(id="credTablePassword")
    private WebElement credTablePassword;

    public void logout(){
        javascriptExecutor.executeScript("arguments[0].click();", logoutButton);
    }

    public void noteTabNavigation(){
        javascriptExecutor.executeScript("arguments[0].click();", notesTab);
    }

    public void credTabNavigation(){
        javascriptExecutor.executeScript("arguments[0].click();", credTab);
    }

    //Fill in note title and description
    public void fillNewNote(String noteTitle, String noteDescription){

        javascriptExecutor.executeScript("arguments[0].value='" + noteTitle +
                "';", nTitle);
        javascriptExecutor.executeScript("arguments[0].value='" + noteDescription +
                "';", nDescription);
    }

    public void fillNewCred(String url, String userName, String password){

        javascriptExecutor.executeScript("arguments[0].value='" + url +
                "';", credUrl);
        javascriptExecutor.executeScript("arguments[0].value='" + userName +
                "';", credUsername);
        javascriptExecutor.executeScript("arguments[0].value='" + password +
                "';", credPassword);
    }

    public void clearNoteTitle(){

        javascriptExecutor.executeScript("arguments[0].value='" + " " +
                "';", nTitle);
    }

    public void clearNoteDescription(){
        javascriptExecutor.executeScript("arguments[0].value='" + " " +
                "';", nDescription);
    }

    public void clickAddNewNote(){
        javascriptExecutor.executeScript("arguments[0].click();", newNoteButton);
    }

    public void clickAddNewCred(){
        javascriptExecutor.executeScript("arguments[0].click();", newCredButton);
    }

    public void clickSubmitNote(){
        javascriptExecutor.executeScript("arguments[0].click();", submitNoteButton);
    }

    public void clickSubmitCred(){
        javascriptExecutor.executeScript("arguments[0].click();", submitCredButton);
    }

    public void clickEditNote(){
        javascriptExecutor.executeScript("arguments[0].click();", noteEditButton);
    }

    public void clickDeleteNote(){
        javascriptExecutor.executeScript("arguments[0].click();", noteDeleteButton);
    }

    public Note getNoteList() {
        Note note = new Note();
        note.setNotetitle(wait.until(ExpectedConditions.elementToBeClickable(noteTableTitle)).getText());
        note.setNotedescription(noteTableDescription.getText());

        return note;

    }

    public Credential getCredList() {
        Credential credential = new Credential();
        credential.setUrl(wait.until(ExpectedConditions.elementToBeClickable(credTableUrl)).getText());
        credential.setUserName(credTaleUsername.getText());
        credential.setPassword(credTablePassword.getText());

        return credential;

    }

    public boolean emptyNoteList(WebDriver driver){
        return !isElementPresent(By.id("noteTableTitle"), driver) &&
                !isElementPresent(By.id("noteTableDescription"), driver);
    }

    public boolean isElementPresent(By locatorKey, WebDriver driver){
        try{
            driver.findElement(locatorKey);
            return true;
        } catch(org.openqa.selenium.NoSuchElementException e){
            return false;
        }


    }



}
