package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class built on top of LoginTest to perform note creation, edit and deletion tests
 */
public class NoteTest extends LoginTest {

    public NoteTest(){

    }

    @Test
    public void addNewNote() {

        HomePage homePage= new HomePage(driver, 1);
        homePage.noteTabNavigation();
        String noteTitle = "Add Note test";
        String noteDescription = "This is a test";

        //clear NoteList before creating a new note
        if (!homePage.emptyNoteList(driver)){
            deleteNote(homePage);
        }

        createNote(noteTitle, noteDescription, homePage);

        homePage.noteTabNavigation();

        Note note = homePage.getNoteList();
        //Verifying that the noteTitle and noteDescription in db matches submitted values
        Assertions.assertEquals(noteTitle, note.getNoteTitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());

        homePage.logout();
    }

    @Test
    public void editNoteTest() {

        //login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.noteTabNavigation();

        //create new Note if one does not exist already
        if(homePage.emptyNoteList(driver)){
            String initialNoteTitle = "Test";
            String initialNoteDescription = "This is a test";

            createNote(initialNoteTitle, initialNoteDescription, homePage);
            homePage.noteTabNavigation();
        }

        String updatedNoteTitle = "Edit test";
        String updatedNoteDescription = "This note has been updated";

        updateNote(updatedNoteTitle, updatedNoteDescription, homePage);

        homePage.noteTabNavigation();

        Note note = homePage.getNoteList();

        //Verify that updated note title and description in db match submitted values
        Assertions.assertEquals(updatedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(updatedNoteDescription, note.getNoteDescription());

        homePage.logout();
    }

    @Test
    public void deleteNoteTest() {

        //login("FirstName", "LastName", "UserName", "password");

        HomePage homePage= new HomePage(driver, 1);
        homePage.noteTabNavigation();

        if (homePage.emptyNoteList(driver)){
            String initialNoteTitle = "Test";
            String initialNoteDescription = "This is a test";

            createNote(initialNoteTitle, initialNoteDescription, homePage);
            homePage.noteTabNavigation();
        }

        //Verify that there is a note to delete
        Assertions.assertFalse(homePage.emptyNoteList(driver));

        deleteNote(homePage);
        homePage.noteTabNavigation();

        //Verify that the note has been deleted
        Assertions.assertTrue(homePage.emptyNoteList(driver));
    }

    //method to perform basic note creation operations
    private void createNote(String noteTitle, String noteDescription, HomePage homePage){
        homePage.noteTabNavigation();
        homePage.clickAddNewNote();
        homePage.fillNewNote(noteTitle, noteDescription);
        homePage.clickSubmitNote();
        ResultPage resultPage = new ResultPage(driver, 2);
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
