package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NoteTab {

    private WebDriver driver;
    private final JavascriptExecutor javascriptExecutor;

    public NoteTab(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.javascriptExecutor = (JavascriptExecutor) driver;
    }

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="new-note")
    private WebElement newNoteButton;

    @FindBy(id="note-title")
    private WebElement nTitle;

    @FindBy(id="note-description")
    private WebElement nDescription;

    @FindBy(id="add-note")
    private WebElement addNoteButton;

    @FindBy(className="listNoteTitle")
    private List<WebElement> tableHeader;

    @FindBy(className="listNoteDescription")
    private List<WebElement> tableData;

    @FindBy(id="noteEdit")
    private List<WebElement> noteEditButton;

    @FindBy(id="noteDelete")
    private List<WebElement> noteDeleteButton;

    public void noteTabNavigation(){
        javascriptExecutor.executeScript("arguments[0].click();", notesTab);

        javascriptExecutor.executeScript("arguments[0].click();", newNoteButton);
    }

    //Fill in note title and description
    public void fillNewNote(String noteTitle, String noteDescription){

        javascriptExecutor.executeScript("arguments[0].value='" + noteTitle +
                "';", nTitle);
        javascriptExecutor.executeScript("arguments[0].value='" + noteDescription +
                "';", nDescription);
    }

    public void clickAddNewNote(){
        javascriptExecutor.executeScript("arguments[0].click();", addNoteButton);
    }

    public void viewNotes(){
        javascriptExecutor.executeScript("arguments[0].click();", notesTab);
    }





}
