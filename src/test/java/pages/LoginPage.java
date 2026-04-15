package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;
import utils.LoggerManager;
import utils.WaitHelper;

public class LoginPage {


    private WebDriver driver;

    private final WaitHelper wait;

    private final ElementActions actions;

    protected static final Logger log = LoggerManager.getLogger(LoginPage.class);
    // ===== Locators =====
    private final By usernameField = By.cssSelector("input[name='username']");
    private final By passwordField = By.cssSelector("input[name='password']");
    private final By loginButton   = By.cssSelector("button[type='submit']");

    // After login (success)
    private final By userDropdownName = By.xpath("//p[contains(@class,'userdropdown-name')]");

    // Invalid login (top message)
    private final By invalidCredentialsMessage =
            By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

    // ===== Constructor =====
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);
        this.actions = new ElementActions(wait);
    }

    // ===== Actions =====
    public LoginPage enterUsername(String username) {
        wait.waitForVisibility(usernameField);
        actions.sendKeys(usernameField, username);
        log.info("Entered username: {}", usernameField);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.waitForVisibility(passwordField);
        actions.sendKeys(passwordField, password);
        log.info("Entered password: {}", passwordField);
        return this;
    }

    public LoginPage clickLogin() {
        wait.waitForClickability(loginButton);
        actions.click(loginButton);
        log.info("User clicked login: {}", loginButton);
        return this;
    }

    public LoginPage login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return this;
    }

    // ===== Success Validation =====
    public boolean isDashboardDisplayed() {
        return wait.waitForVisibility(userDropdownName).isDisplayed();
    }


    public boolean isInvalidCredentialsDisplayed() {
        try {
            return wait.waitForVisibility(invalidCredentialsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ===== Empty Fields Validation =====
    private By fieldError(String fieldName) {
        return By.xpath(
                "//input[@name='" + fieldName + "']/ancestor::div[contains(@class,'oxd-input-group')]//span"
        );
    }

    public String getFieldErrorMessage(String fieldName) {
        return wait.waitForVisibility(fieldError(fieldName))
                .getText()
                .trim();
    }

    public boolean isFieldErrorDisplayed(String fieldName) {
        try {
            return wait.waitForVisibility(fieldError(fieldName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}