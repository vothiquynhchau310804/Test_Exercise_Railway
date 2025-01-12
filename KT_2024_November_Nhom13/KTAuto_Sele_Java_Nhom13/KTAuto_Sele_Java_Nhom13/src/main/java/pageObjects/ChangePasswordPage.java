package pageObjects;

import common.Constant;
import org.openqa.selenium.By;

import static common.Constant.WEBDRIVER;

public class ChangePasswordPage extends GeneralPage {

    private final By currentPasswordField = By.id("currentPassword");
    private final By newPasswordField = By.id("newPassword");
    private final By confirmPasswordField = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By successMessage = By.xpath("//p[@class='message success']");
    private final By changePasswordTab = By.xpath("//a[@href='/Account/ChangePassword']");

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        WEBDRIVER.findElement(currentPasswordField).sendKeys(currentPassword);
        WEBDRIVER.findElement(newPasswordField).sendKeys(newPassword);
        WEBDRIVER.findElement(confirmPasswordField).sendKeys(confirmPassword);
        WEBDRIVER.findElement(changePasswordButton).click();
    }

    public String getChangePasswordMessage() {
        return WEBDRIVER.findElement(successMessage).getText();
    }


    public ChangePasswordPage clickChangePasswordTab() {
        WEBDRIVER.findElement(changePasswordTab).click();
        return this;
    }

    public boolean isChangePasswordPageDisplayed() {
        return WEBDRIVER.getCurrentUrl().contains("ChangePassword");
    }
}

