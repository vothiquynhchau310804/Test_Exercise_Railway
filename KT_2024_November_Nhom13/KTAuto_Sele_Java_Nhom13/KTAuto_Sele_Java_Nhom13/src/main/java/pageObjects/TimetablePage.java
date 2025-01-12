package pageObjects;

import org.openqa.selenium.By;
import static common.Constant.WEBDRIVER;

public class TimetablePage extends GeneralPage {
    private final By _linkBookTicketHueToSaigon = By.xpath("//td[contains(text(),'Huế')]/following-sibling::td[contains(text(),'Sài Gòn')]/following-sibling::td/a");
    public void clickBookTicketHueToSaigon() {
        WEBDRIVER.findElement(_linkBookTicketHueToSaigon).click();
    }
}