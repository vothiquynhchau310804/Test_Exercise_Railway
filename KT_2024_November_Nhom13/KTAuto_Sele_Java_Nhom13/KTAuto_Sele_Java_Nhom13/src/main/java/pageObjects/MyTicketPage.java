package pageObjects;


import common.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static common.Constant.WEBDRIVER;

public class MyTicketPage extends GeneralPage {

    private final By cancelButtonLocator = By.xpath("//input[@value='Cancel']");
    private final By myTicketTab = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    public void open() {
        WebElement myTicketTab = WEBDRIVER.findElement(By.linkText("My ticket"));
        myTicketTab.click();
    }

    public List<WebElement> getCancelButtons() {
        return WEBDRIVER.findElements(cancelButtonLocator);
    }

    public boolean cancelFirstTicket() {
        List<WebElement> cancelButtons = getCancelButtons();
        if (!cancelButtons.isEmpty()) {
            WebElement cancelButton = cancelButtons.get(0);

            String onClickValue = cancelButton.getAttribute("onclick");
            String idString = onClickValue.split("\\(")[1].split("\\)")[0];
            int ticketId = Integer.parseInt(idString);

            ((JavascriptExecutor) WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", cancelButton);
            cancelButton.click();

            try {
                Alert alert = WEBDRIVER.switchTo().alert();
                alert.accept(); // Accept the alert
            } catch (NoAlertPresentException e) {
                System.out.println("No confirmation alert appeared.");
            }

            WebDriverWait wait = new WebDriverWait(WEBDRIVER, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOf(cancelButton));

            boolean isTicketCancelled = !WEBDRIVER.getPageSource().contains(Integer.toString(ticketId));
            if (isTicketCancelled) {
                System.out.println("Ticket with ID " + ticketId + " has been successfully cancelled.");
            } else {
                System.out.println("Ticket cancellation failed for ID " + ticketId);
            }
            return isTicketCancelled;
        } else {
            System.out.println("No tickets available to cancel.");
            return false;
        }
    }

    public MyTicketPage clickMyTicketTab() {
        WEBDRIVER.findElement(myTicketTab).click();
        return this;
    }
    public boolean isMyTicketPageDisplayed() {
        return WEBDRIVER.getCurrentUrl().contains("ManageTicket");
    }
}