package testcases;

import common.Constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.ChangePasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class ChangePasswordTest {
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
    public void TC09() {
        System.out.println("TC09 - Verify that user can change password successfully");

        String username = "tc144@gmail.com";
        String currentPassword = "1234567890";
        String newPassword = "NewPassword123456";

        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(username, currentPassword);

        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        changePasswordPage.changePassword(currentPassword, newPassword, newPassword);

        String actualMessage = changePasswordPage.getChangePasswordMessage();
        String expectedMessage = "Your password has been updated";
        Assert.assertEquals(actualMessage, expectedMessage, "Success message is not displayed as expected.");

    }


}
