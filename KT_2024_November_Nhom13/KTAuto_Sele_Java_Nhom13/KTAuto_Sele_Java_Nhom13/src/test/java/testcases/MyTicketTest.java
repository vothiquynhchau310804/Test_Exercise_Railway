package testcases;

import common.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.BookTicketPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyTicketPage;

import java.util.List;

public class MyTicketTest {

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
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");
        
        String departFrom = "Quảng Ngãi";
        String arriveAt = "Nha Trang";
        String seatType = "Soft seat";
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

        MyTicketPage myTicketPage = new MyTicketPage();
        myTicketPage.open();
        boolean isTicketCancelled = myTicketPage.cancelFirstTicket();

        Assert.assertTrue(isTicketCancelled, "Ticket cancellation failed.");
    }
}