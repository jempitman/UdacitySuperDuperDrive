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
        String noteTitle = "Add Note test";
        String noteDescription = "This is a test";

        createNote(noteTitle, noteDescription, homePage);

        homePage.noteTabNavigation();

        Note note = homePage.getNoteList();

        Assertions.assertEquals(noteTitle, note.getNoteTitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());

        deleteNote(homePage);
        homePage.logout();
    }

    @Test
    public void addNoteAndEdit() throws InterruptedException{

        login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.noteTabNavigation();
        String initialNoteTitle = "Test";
        String initialNoteDescription = "This is a test";

        createNote(initialNoteTitle, initialNoteDescription, homePage);
        homePage.noteTabNavigation();

        String updatedNoteTitle = "Edit test";
        String updatedNoteDescription = "This note has been updated";

        updateNote(updatedNoteTitle, updatedNoteDescription, homePage);

        homePage.noteTabNavigation();

        Note note = homePage.getNoteList();

        Assertions.assertEquals(updatedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(updatedNoteDescription, note.getNoteDescription());

        deleteNote(homePage);
        homePage.logout();
    }

    @Test
    public void deleteNoteTest() throws InterruptedException{

        login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.noteTabNavigation();
        String initialNoteTitle = "Test";
        String initialNoteDescription = "This is a test";

        createNote(initialNoteTitle, initialNoteDescription, homePage);
        homePage.noteTabNavigation();

        Assertions.assertFalse(homePage.emptyNoteList(driver));

        deleteNote(homePage);
        homePage.noteTabNavigation();

        Assertions.assertTrue(homePage.emptyNoteList(driver));
    }

    //method to perform basic note creation operations
    private void createNote(String noteTitle, String noteDescription, HomePage homePage){
        homePage.noteTabNavigation();
        homePage.clickAddNewNote();
        homePage.fillNewNote(noteTitle, noteDescription);
        homePage.clickSubmitNote();
        ResultPage resultPage = new ResultPage(driver, 1);
        resultPage.clickNoteCreationSuccess();

    }

    //method to perform basic note update function
    private void updateNote(String updatedNoteTitle, String updatedNoteDescription, HomePage homePage){
        homePage.clickEditNote();
        homePage.clearNoteTitle();
        homePage.clearNoteDescription();
        homePage.fillNewNote(updatedNoteTitle, updatedNoteDescription);
        homePage.clickSubmitNote();
        ResultPage resultPage = new ResultPage(driver, 1);
        resultPage.clickNoteEditSuccess();
    }

    //method to perform note deletion
    private void deleteNote(HomePage homePage){
        homePage.clickDeleteNote();
        ResultPage resultPage = new ResultPage(driver, 1);
        resultPage.clickNoteDeleteSuccess();
    }


}
