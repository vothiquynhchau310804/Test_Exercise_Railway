package testcases;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.BookTicketPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.TimetablePage;

public class TimetableTest {
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
    public void TC15() {
        System.out.println("User can open \"Book ticket\" page by clicking on \"Book ticket\" link in \"Train timetable\" page\n");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        TimetablePage timeTable = homePage.gotoTimetablePage();
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement bookTicketLink = Constant.WEBDRIVER.findElement(By.xpath("//a[contains(@href,'BookTicketPage.cshtml?id1=5&id2=1')]"));
        bookTicketLink.click();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        String expectedDepartFrom = "Huế";
        String expectedArriveAt = "Sài Gòn";
        BookTicketPage bookTicketPage = new BookTicketPage();
        String actualDepartFrom = bookTicketPage.getDepartFrom();
        String actualArriveAt = bookTicketPage.getArriveAt();

        Assert.assertEquals(actualDepartFrom, expectedDepartFrom, "Depart from value is incorrect");
        Assert.assertEquals(actualArriveAt, expectedArriveAt, "Arrive at value is incorrect");
    }

}
