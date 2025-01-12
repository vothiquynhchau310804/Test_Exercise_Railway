package testcases;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

import static org.testng.Assert.assertEquals;

public class RegisterTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    @Test
    public void TC07() {
        System.out.println("TC07 - Verify that user can create a new account");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        registerPage.enterEmail("testcase12345@gmail.com");
        registerPage.enterPassword("validPassword123");
        registerPage.enterConfirmPassword("validPassword123");
        registerPage.enterPid("123456789");

        registerPage.clickRegisterButton();

        String actualMessage = registerPage.getErrorMessage();
        String expectedMessage = "Thank you for registering your account";
        assertEquals(actualMessage, expectedMessage, "The success message is not displayed as expected.");
    }
    @Test
    public void TC10() {
        System.out.println("TC10 - Verify that User can't create account with 'Confirm password' is not the same with 'Password'");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        registerPage.enterEmail("testcreate12345@gmail.com");
        registerPage.enterPassword("valid123");
        registerPage.enterConfirmPassword("notvalidd123");
        registerPage.enterPid("123456789");

        registerPage.clickRegisterButton();

        String actualMessage = registerPage.getErrorMessage();
        String expectedMessage = "There're errors in the form. Please correct the errors and try again.";
        assertEquals(actualMessage, expectedMessage, "The error message is not displayed as expected.");
    }

    @Test
    public void TC11() {
        System.out.println("TC11 - Verify that User can't create account while password and PID fields are empty");
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        registerPage.enterEmail("testcreate12534@gmail.com");
        registerPage.enterPassword("");
        registerPage.enterPid("");

        registerPage.clickRegisterButton();

        String actualMessage = registerPage.getErrorMessage();
        String expectedMessage = "There're errors in the form. Please correct the errors and try again.";
        assertEquals(actualMessage, expectedMessage, "The error message is not displayed as expected.");

        String passwordActualMsg = registerPage.getValidationPasswordError();
        String passwordExpectedMsg = "Invalid password length.";
        assertEquals(passwordActualMsg, passwordExpectedMsg, "The password error message is not the same as expected.");

        String pidActualMsg = registerPage.getValidationPIDError();
        String pidExpectedMsg = "Invalid ID length.";
        assertEquals(pidActualMsg, pidExpectedMsg, "The PID error message is not the same as expected.");
    }



}
