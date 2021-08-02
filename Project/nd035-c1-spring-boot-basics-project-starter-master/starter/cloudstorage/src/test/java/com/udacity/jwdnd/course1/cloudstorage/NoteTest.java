package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest extends LoginTest {

    public NoteTest(){

    }

    @Test
    public void addNewNote() throws InterruptedException{


        login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.noteTabNavigation();
        String noteTitle = "Test";
        String noteDescription = "This is a test";

        createNote(noteTitle, noteDescription, homePage);

        homePage.noteTabNavigation();

        Note note = homePage.getNoteList();

        Assertions.assertEquals(noteTitle, note.getNotetitle());
        Assertions.assertEquals(noteDescription, note.getNotedescription());
    }

    //method to perform basic note creation operations
    private void createNote(String noteTitle, String noteDescription, HomePage homePage) throws InterruptedException{
        homePage.noteTabNavigation();
        homePage.clickAddNewNote();
        homePage.fillNewNote(noteTitle, noteDescription);
        homePage.clickSubmitNote();
        ResultPage resultPage = new ResultPage(driver, 1000);
        resultPage.clickNoteCreationSuccess();

    }


}
