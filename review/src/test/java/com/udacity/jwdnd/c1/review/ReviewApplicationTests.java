package com.udacity.jwdnd.c1.review;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootApplication
public class ReviewApplicationTests {

	//Set up local server address
	@LocalServerPort
	public int port;

	public static WebDriver driver;

	public String baseURL;



	@BeforeAll
	public static void beforeAll(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll(){
		driver.quit();
		driver = null;
	}

	//
	@BeforeEach
	public void	beforeEach(){
		baseURL = baseURL = ("http://localhost:" + port);
	}

	@Test
	public void testUserSignupLoginAndSubmitMessage(){

		String userName = "Jeremy";
		String password = "Jem123";
		String messageText = "Hi";

		driver.get(baseURL + "/signup");


		SignupPage signup = new SignupPage(driver);
		signup.signup("Jeremy", "Pitman", userName, password);
		System.out.println("Passed signup page");

		LoginPage loginPage = new LoginPage(driver);
		System.out.println("Landed on login page");
		loginPage.login(userName, password);
		System.out.println("Passed login page");

		ChatPage chatPage = new ChatPage(driver);
		System.out.println("Created new Chat page");
		chatPage.submitMessage(messageText);
		System.out.println("Submitted new message");

		ChatMessage sentMessage = chatPage.getFirstMessage();

		assertEquals(userName, sentMessage.getUsername());
		assertEquals(messageText, sentMessage.getMessageText());

		System.out.println("msg: " + sentMessage.getUsername() + " / " + sentMessage.getMessageText());


		//assertEquals("JPitman", loginPage.getUserName());


	}



	//public static void main(String[] args) {
	//	SpringApplication.run(ReviewApplication.class, args);}

// no longer needed
//	@Bean
//	public String message(){
//		System.out.println("Creating message bean");
//		return "Hello Spring";
//	}

//	@Bean
//	public String uppercaseMessage(com.udacity.jwdnd.c1.review.service.MessageService messageService){
//		System.out.println("Creating uppercaseMessage bean");
//		return messageService.uppercase();
//	}

//	@Bean
//	public String lowercaseMessage(MessageService messageService){
//		System.out.println("Creating lowercaseMessage bean");
//		return messageService.lowercase();
//	}

}
