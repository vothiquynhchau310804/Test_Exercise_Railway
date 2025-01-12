package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

import static common.Constant.WEBDRIVER;

public class HomePage extends GeneralPage{
    //locators
    private final By tabBookTicket = By.xpath("//*[@id='menu']/ul/li[6]/a");
    private final By tabMyTicket = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabChangePassword = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabRegister = By.xpath("//a[@href='/Account/Register.cshtml']");
    private final By tabTimetable = By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[4]/a");
    private final By _lnkForgotPassword = By.xpath("//html/body/div[1]/div[2]/ul/li[3]/a");
    private final By myTicketTab = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By changePasswordTab = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");
    private final By logoutTab = By.xpath("//a[@href='/Account/Logout']");
    public HomePage open()
    {
        WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }

    public BookTicketPage gotoBookTicketPage() {
        WEBDRIVER.findElement(tabBookTicket).click();
        return new BookTicketPage();
    }

    public MyTicketPage gotoMyTicketPage() {
        WEBDRIVER.findElement(tabMyTicket).click();
        return new MyTicketPage();
    }

    public ChangePasswordPage gotoChangePasswordPage() {
        WEBDRIVER.findElement(By.linkText("Change password")).click();
        return new ChangePasswordPage();
    }
    public RegisterPage gotoRegisterPage() {
        WEBDRIVER.findElement(tabRegister).click();
        return new RegisterPage();
    }

    public TimetablePage gotoTimetablePage() {
        WEBDRIVER.findElement(tabTimetable).click();
        return new TimetablePage();
    }
    public WebElement getLnkForgotPassword(){
        return WEBDRIVER.findElement(_lnkForgotPassword);
    }

    public ForgotPasswordPage gotoForgotPasswordPage() {
        this.getLnkForgotPassword().click();

        return new ForgotPasswordPage();
    }

    public boolean isMyTicketTabDisplayed() {
        return isElementDisplayed(myTicketTab);
    }

    public boolean isChangePasswordTabDisplayed() {
        return isElementDisplayed(changePasswordTab);
    }

    public boolean isLogoutTabDisplayed() {
        return isElementDisplayed(logoutTab);
    }

    public MyTicketPage clickMyTicketTab() {
        WEBDRIVER.findElement(myTicketTab).click();
        return new MyTicketPage();
    }

    public ChangePasswordPage clickChangePasswordTab() {
        WEBDRIVER.findElement(changePasswordTab).click();
        return new ChangePasswordPage();
    }

    public LoginPage clickLogoutTab() {
        WEBDRIVER.findElement(logoutTab).click();
        return new LoginPage();
    }

    private boolean isElementDisplayed(By elementLocator) {
        try {
            return WEBDRIVER.findElement(elementLocator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }


}