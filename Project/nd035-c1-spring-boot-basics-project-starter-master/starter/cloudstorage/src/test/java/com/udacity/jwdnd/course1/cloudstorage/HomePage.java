package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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

    @FindBy(id="new-note")
    private WebElement newNoteButton;

    @FindBy(id="note-title")
    private WebElement nTitle;

    @FindBy(id="note-description")
    private WebElement nDescription;

    @FindBy(id="noteSubmit")
    private WebElement submitNoteButton;

    @FindBy(className="listNoteTitle")
    private List<WebElement> tableHeader;

    @FindBy(className="listNoteDescription")
    private List<WebElement> tableData;

    @FindBy(id="noteEdit")
    private List<WebElement> noteEditButton;

    @FindBy(id="noteDelete")
    private List<WebElement> noteDeleteButton;

    @FindBy(id="noteTableTitle")
    private WebElement noteTableTitle;

    @FindBy(id="noteTableDescription")
    private WebElement noteTableDescription;

    public void logout(){
        javascriptExecutor.executeScript("arguments[0].click();", logoutButton);
    }

    public void noteTabNavigation(){
        javascriptExecutor.executeScript("arguments[0].click();", notesTab);
    }

    //Fill in note title and description
    public void fillNewNote(String noteTitle, String noteDescription){

        javascriptExecutor.executeScript("arguments[0].value='" + noteTitle +
                "';", nTitle);
        javascriptExecutor.executeScript("arguments[0].value='" + noteDescription +
                "';", nDescription);
    }

    public void clickAddNewNote(){
        javascriptExecutor.executeScript("arguments[0].click();", newNoteButton);
    }

    public void clickSubmitNote(){
        javascriptExecutor.executeScript("arguments[0].click();", submitNoteButton);
    }

    public void editNote(){
        javascriptExecutor.executeScript("arguments[0].click();", noteEditButton);
    }

    public Note getNoteList() {
        Note note = new Note();
        note.setNotetitle(wait.until(ExpectedConditions.elementToBeClickable(noteTableTitle)).getText());
        note.setNotedescription(noteTableDescription.getText());

        return note;

    }



}
