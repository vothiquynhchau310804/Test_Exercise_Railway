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
import pageObjects.BookTicketPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.TimetablePage;

import java.time.Duration;

import static common.Constant.WEBDRIVER;

public class BookTicketTest {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition: Setting up WebDriver");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition: Closing WebDriver");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");

        String departFrom = "Đà Nẵng";
        String arriveAt = "Sài Gòn";
        String seatType = "Soft bed with air conditioner";
        String ticketAmount = "1";
        int daysToAdd = 5;

        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();
        bookTicketPage.selectDepartDate(daysToAdd);
        bookTicketPage.selectDepartFrom(departFrom);
        bookTicketPage.selectArriveAt(arriveAt);
        bookTicketPage.selectSeatType(seatType);
        bookTicketPage.selectTicketAmount(ticketAmount);
        bookTicketPage.clickBookTicketButton();

        Assert.assertTrue(bookTicketPage.isTicketBookedSuccessfullyDisplayed(), "Ticket booked successfully message is not displayed.");

        String expectedDepartDate = bookTicketPage.getDepartDate();
        boolean ticketDetailsMatch = bookTicketPage.verifyTicketDetails(departFrom, arriveAt, seatType, expectedDepartDate, ticketAmount);

        System.out.println("Depart Station: " + bookTicketPage.getDepartStation());
        System.out.println("Arrive Station: " + bookTicketPage.getArriveStation());
        System.out.println("Seat Type: " + bookTicketPage.getSeatType());
        System.out.println("Depart Date: " + expectedDepartDate);
        System.out.println("Amount: " + bookTicketPage.getAmount());

        Assert.assertTrue(ticketDetailsMatch, "Ticket details do not match.");
    }




}
