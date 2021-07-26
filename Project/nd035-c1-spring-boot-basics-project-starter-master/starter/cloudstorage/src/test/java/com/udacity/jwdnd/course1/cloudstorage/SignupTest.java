package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTest {

    protected WebDriver driver;
    protected String baseURL;

    @LocalServerPort
    protected int port;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        baseURL = baseURL = ("http://localhost:" + this.port);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getSignupPage(){
        driver.get(baseURL + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }

    @Test
    public void signupOK(){
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.fillSignup("firstname", "lastname", "username", "password");
        signupPage.clickSignup();
        Assertions.assertTrue(signupPage.isSuccess());
    }


    @Test
    public void failedSignup() throws InterruptedException{
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.fillSignup("firstname", "lastname", "username", "password");
        signupPage.clickSignup();
        Assertions.assertTrue(signupPage.isSuccess());
        //Assertions.assertEquals("You successfully signed up! Please continue to the login page", signupPage.getSuccessMsg());

        signupPage.fillSignup("firstname", "lastname", "username", "password");
        Assertions.assertTrue(signupPage.isError());
        //Assertions.assertEquals("User already exists", signupPage.getErrorMsg());
        //SignupPage newSignupPage = new SignupPage(driver);
        //signupPage.fillSignup("firstname", "lastname", "username", "password");


        //Assertions.assertTrue(signupPage.isError());

    }




}
