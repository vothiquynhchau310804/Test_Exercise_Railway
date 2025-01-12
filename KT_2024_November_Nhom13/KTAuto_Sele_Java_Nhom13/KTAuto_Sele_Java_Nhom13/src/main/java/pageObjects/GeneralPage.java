package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class GeneralPage {
    private final By tabLogin = By.xpath("//div[@id= 'menu']//a[@href = '/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id = 'menu']//a[@href = '/Account/Logout']");
    private final By lbWelcomeMessage = By.xpath("//div[@class = 'account']/strong");
    private final By lblErrorMessage = By.xpath("//div[@class='error message']");


    protected WebElement getTabLogin () {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }

    protected WebElement getLbWelcomeMessage () {
        return Constant.WEBDRIVER.findElement(lbWelcomeMessage);
    }
    protected WebElement getLblErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblErrorMessage);
    }

    public String getWelcomeMessage()
    {
        return this.getLbWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }
    public String getErrorMessage() {
        return this.getLblErrorMessage().getText();
    }

}
