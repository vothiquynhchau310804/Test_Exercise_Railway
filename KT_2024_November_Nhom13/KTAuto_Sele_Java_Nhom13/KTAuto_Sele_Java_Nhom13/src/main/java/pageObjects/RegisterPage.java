package pageObjects;

import common.Constant;
import org.openqa.selenium.By;

public class RegisterPage {

    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By confirmPasswordField = By.id("confirmPassword");
    private final By pidField = By.id("pid");
    private final By registerButton = By.xpath("//input[@type='submit' and @value='Register' and @title='Register']");
    private By msgError = By.xpath("//*[@id='content']/p[@class='message error']");
    private String lblValidationError = "//*[@for='%s' and contains(@class, 'validation-error')]";

    public void enterEmail(String email) {
        Constant.WEBDRIVER.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        Constant.WEBDRIVER.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        Constant.WEBDRIVER.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void enterPid(String pid) {
        Constant.WEBDRIVER.findElement(pidField).sendKeys(pid);
    }

    public void clickRegisterButton() {
        Constant.WEBDRIVER.findElement(registerButton).click();
    }

    public String getErrorMessage() {
        return Constant.WEBDRIVER.findElement(msgError).getText();
    }

    public String getValidationPasswordError() {
        By passwordError = By.xpath(String.format(lblValidationError, "password"));
        return Constant.WEBDRIVER.findElement(passwordError).getText();
    }

    public String getValidationPIDError() {
        By pidError = By.xpath(String.format(lblValidationError, "pid"));
        return Constant.WEBDRIVER.findElement(pidError).getText();
    }
}