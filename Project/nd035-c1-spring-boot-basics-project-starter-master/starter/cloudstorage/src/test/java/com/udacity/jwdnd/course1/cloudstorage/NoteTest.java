package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest extends LoginTest {

    public NoteTest(){

    }

    @Test
    public void addNewNote() throws InterruptedException{
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.fillSignup("firstName", "lastName", "userName", "password");
        signupPage.clickSignup();
        Thread.sleep(1000);

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLogin("userName", "password");
        loginPage.clickLogin();
        Thread.sleep(1000);

        NoteTab noteTab= new NoteTab(driver);

        noteTab.noteTabNavigation();

        String noteTitle = "Lorem ipsum";
        String noteDescription = "dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        noteTab.fillNewNote(noteTitle, noteDescription);

        noteTab.clickAddNewNote();

        Thread.sleep(1000);

        driver.get(baseURL + "/home");

        noteTab.viewNotes();

        Thread.sleep(1000);

        Assertions.assertEquals(baseURL + "/home", driver.getCurrentUrl());


    }


}
