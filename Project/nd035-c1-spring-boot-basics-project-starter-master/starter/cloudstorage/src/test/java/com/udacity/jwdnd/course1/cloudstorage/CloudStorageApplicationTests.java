package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	public int port;

	private WebDriver driver;

	private String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = baseURL = ("http://localhost:" + port);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testUserSignupLogin(){

		String firstName = "Jeremy";
		String lastName = "Pitman";
		String userName = "jpit";
		String password = "Jem123";

		driver.get(baseURL + "/signup");

		SignupPage signup = new SignupPage(driver);
		signup.signup(firstName, lastName, userName, password);

		//Assertions.assertEquals("Sign Up", driver.getTitle());

		LoginPage login = new LoginPage(driver);
		login.login(userName,password);

		//Assertions.assertEquals("Login", driver.getTitle());


		try {
			//attempt to access invalid page below

			Assertions.fail("Shouldn't happen");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Succeeded");
		}


	}


}
