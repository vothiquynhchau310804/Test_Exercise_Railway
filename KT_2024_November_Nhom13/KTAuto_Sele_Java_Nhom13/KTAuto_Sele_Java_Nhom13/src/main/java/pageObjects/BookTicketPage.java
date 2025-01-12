package pageObjects;

import common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static common.Constant.WEBDRIVER;

public class BookTicketPage extends GeneralPage {
    private final By selectDepartFrom = By.xpath("//select[@name='DepartStation']");
    private final By selectArriveAt = By.xpath("//select[@name='ArriveStation']");
    private final By selectSeatType = By.xpath("//select[@name='SeatType']");
    private final By selectTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
    private final By selectDepartDate = By.xpath("//select[@name='Date']");
    private final By departDateField = By.xpath("//div[@id='content']//td[4]");
    private final By departStationField = By.xpath("//div[@id='content']//td[1]");
    private final By arriveStationField = By.xpath("//div[@id='content']//td[2]");
    private final By seatTypeField = By.xpath("//div[@id='content']//td[3]");
    private final By amountField = By.xpath("//div[@id='content']//td[7]");
    private final By successMessage = By.xpath("//div[@id='content']//h1[contains(text(),'Ticket booked successfully!')]");

    public String getDepartDate() {
        return WEBDRIVER.findElement(departDateField).getText();
    }

    public String getDepartStation() {
        return WEBDRIVER.findElement(departStationField).getText();
    }

    public String getArriveStation() {
        return WEBDRIVER.findElement(arriveStationField).getText();
    }

    public String getSeatType() {
        return WEBDRIVER.findElement(seatTypeField).getText();
    }

    public String getAmount() {
        return WEBDRIVER.findElement(amountField).getText();
    }

    public boolean isTicketBookedSuccessfullyDisplayed() {
        try {
            return WEBDRIVER.findElement(successMessage).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void selectDepartDate(int daysToAdd) {
        LocalDate today = LocalDate.now();
        LocalDate desiredDate = today.plusDays(daysToAdd);

        if (daysToAdd >= 3 && daysToAdd <= 30) {
            String formattedDate = desiredDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            selectOptionInDropdown(selectDepartDate, formattedDate);
        } else {
            System.out.println("Please choose a date between 3 and 30 days ahead.");
        }
    }

    private void selectOptionInDropdown(By dropdownLocator, String option) {
        WebElement dropdown = WEBDRIVER.findElement(dropdownLocator);
        scrollToElement(dropdown);

        WebDriverWait wait = new WebDriverWait(WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));

        Select select = new Select(dropdown);
        boolean optionExists = select.getOptions().stream()
                .anyMatch(opt -> opt.getText().equals(option));

        if (optionExists) {
            select.selectByVisibleText(option);
        } else {
            System.out.println("Option '" + option + "' is not available in the dropdown.");
        }
    }

    public void selectDepartFrom(String station) {
        selectOptionInDropdown(selectDepartFrom, station);
    }

    public void selectArriveAt(String station) {
        selectOptionInDropdown(selectArriveAt, station);
    }

    public void selectSeatType(String seatType) {
        selectOptionInDropdown(selectSeatType, seatType);
    }

    public void selectTicketAmount(String amount) {
        selectOptionInDropdown(selectTicketAmount, amount);
    }

    public void clickBookTicketButton() {
        WebElement button = WEBDRIVER.findElement(btnBookTicket);
        scrollToElement(button);
        button.click();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean verifyTicketDetails(String departStation, String arriveStation, String seatType, String departDate, String amount) {
        WebElement row = WEBDRIVER.findElement(By.xpath("//table[@class='MyTable WideTable']//tr[@class='OddRow']"));

        String actualDepartStation = row.findElement(By.xpath(".//td[1]")).getText();
        String actualArriveStation = row.findElement(By.xpath(".//td[2]")).getText();
        String actualSeatType = row.findElement(By.xpath(".//td[3]")).getText();
        String actualDepartDate = row.findElement(By.xpath(".//td[4]")).getText();
        String actualAmount = row.findElement(By.xpath(".//td[7]")).getText();

        return actualDepartStation.equals(departStation)
                && actualArriveStation.equals(arriveStation)
                && actualSeatType.equals(seatType)
                && actualDepartDate.equals(departDate)
                && actualAmount.equals(amount);
    }

    public void bookTicket(String departStation, String arriveStation, String seatType, int daysToAdd, String ticketAmount) {
        selectDepartDate(daysToAdd);
        selectDepartFrom(departStation);
        selectArriveAt(arriveStation);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);
        clickBookTicketButton();
    }

    public String getDepartFrom() {
        WebElement departFromElement = Constant.WEBDRIVER.findElement(selectDepartFrom);
        Select select = new Select(departFromElement);
        return select.getFirstSelectedOption().getText().trim();
    }

    public String getArriveAt() {
        WebElement arriveAtElement = Constant.WEBDRIVER.findElement(selectArriveAt);
        Select select = new Select(arriveAtElement);
        return select.getFirstSelectedOption().getText().trim();
    }


}
