package testcases;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;

import java.time.Duration;

import static common.Constant.WEBDRIVER;
import static org.testng.Assert.assertEquals;

public class LoginTest {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        WEBDRIVER = new ChromeDriver();
        WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank 'Username' textbox");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("", Constant.PASSWORD);

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "There was a problem with your login and/or errors exist in your form.";

        assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected.");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, "invalid123");

        String actualErrorMessage = loginPage.getErrorMessage();

        String expectedErrorMessage = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message for invalid password is not displayed as expected.");
    }
    @Test
    public void TC04() {
        System.out.println("TC04 - Verify that user is redirected to Login page after navigating to a protected page without login");
        HomePage homePage = new HomePage();
        homePage.open();
        BookTicketPage BookTicketPage = homePage.gotoBookTicketPage();
        String currentUrl = WEBDRIVER.getCurrentUrl();
        String expectedUrl = "http://railwayb1.somee.com/Account/Login.cshtml";
        Assert.assertTrue(currentUrl.startsWith(expectedUrl), "The user is not redirected to the login page as expected");
    }
    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 0; i < 4; i++) {
            loginPage.login(Constant.USERNAME, "wrongpassword");
        }

        String actualMsg = loginPage.getErrorMessage();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC6() {
        System.out.println("TC06 - Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change password tab is not displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed");

        MyTicketPage myTicketPage = homePage.clickMyTicketTab();
        Assert.assertTrue(myTicketPage.isMyTicketPageDisplayed(), "User is not redirected to My ticket page");

        ChangePasswordPage changePasswordPage = homePage.clickChangePasswordTab();
        Assert.assertTrue(changePasswordPage.isChangePasswordPageDisplayed(), "User is not redirected to Change password page");


    }

    @Test
    public void TC08() {
        System.out.println("TC08 - Verify that user can't login with an account that hasn't been activated");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("ngaymoi@gmail.com", "invalid123");

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message is not displayed as expected.");
    }

    @Test
    public void TC12(){
        System.out.println("TC12-TC13 - Errors display when password reset token is blank -Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();

        JavascriptExecutor js = ( JavascriptExecutor )WEBDRIVER;
        js .executeScript ( "window.scrollBy(0,500)" , "" ) ;
        ForgotPasswordPage forgotPasswordPage = homePage.gotoForgotPasswordPage();
        forgotPasswordPage.enterEmailAddress(Constant.USERNAME);
        forgotPasswordPage.clickSendInstructionButton();
        Assert.assertEquals(false, "BUG SEP 01!");

    }
}
