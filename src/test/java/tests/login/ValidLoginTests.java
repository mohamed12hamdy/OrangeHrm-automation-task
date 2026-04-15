package tests.login;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTest;
import utils.PropertyReader;

public class ValidLoginTests extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testValidLogin() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login(
                PropertyReader.get("valid.username"),
                PropertyReader.get("valid.password")
        );
        log.info("Attempted login with valid credentials");
        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Login should be successful and dashboard should appear"
        );
    }
}
