package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitHelper;

public class HomePage {

    private WebDriver driver;

    private final WaitHelper wait;

    private final By userDropdown = By.className("oxd-userdropdown-name");   //includes logged user name

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
    }

    public boolean isLoggedIn() {
        return wait.waitForVisibility(userDropdown).isDisplayed();
    }

    public boolean isWidgetVisible(String widgetName) {
        By locator = By.xpath("//p[text()='" + widgetName + "']");
        try {
            return wait.waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
