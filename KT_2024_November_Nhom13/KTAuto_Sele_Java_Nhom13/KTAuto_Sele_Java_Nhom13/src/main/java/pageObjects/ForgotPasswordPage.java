package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage extends GeneralPage {
    private final By email = By.xpath("//*[@id=\"email\"]");
    private final By sendButton = By.xpath("//*[@id=\"content\"]/form/fieldset/p[2]/input");
    private final WebDriver driver = Constant.WEBDRIVER;
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đợi tối đa 10 giây

    public void enterEmailAddress(String username) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
        emailField.clear();
        emailField.sendKeys(username);
    }

    public void clickSendInstructionButton() {
        WebElement sendBtn = wait.until(ExpectedConditions.elementToBeClickable(sendButton));
        sendBtn.click();
    }
}
