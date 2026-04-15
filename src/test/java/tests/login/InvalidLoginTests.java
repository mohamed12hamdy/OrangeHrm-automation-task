package tests.login;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.BaseTest;
import utils.PropertyReader;

public class InvalidLoginTests extends BaseTest {

    @Test(dataProvider = "InvalidLoginData", dataProviderClass = data.InvalidLoginDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
    public void testInvalidLogin(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login(username, password);

        log.info("Attempted login with invalid credentials: " + username + " / " + password);

        Assert.assertTrue(
                loginPage.isInvalidCredentialsDisplayed(),
                "Error message should be displayed for invalid login"
        );
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testEmptyUsername() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.enterPassword(PropertyReader.get("valid.password"))
        .clickLogin();

        log.info("Attempted login with empty username and valid password");
        Assert.assertEquals(
                loginPage.getFieldErrorMessage("username"),
                "Required",
                "Username required message should be displayed"
        );
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testEmptyPassword() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.enterUsername(PropertyReader.get("valid.username"))
                .clickLogin();

        log.info("Attempted login with valid username and empty password");
        Assert.assertEquals(
                loginPage.getFieldErrorMessage("password"),
                "Required",
                "Password required message should be displayed"
        );
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testEmptyUsernameAndPassword() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.clickLogin();

        log.info("Attempted login with empty username and password");
        Assert.assertEquals(
                loginPage.getFieldErrorMessage("username"),
                "Required",
                "Username required message should be displayed"
        );

        Assert.assertEquals(
                loginPage.getFieldErrorMessage("password"),
                "Required",
                "Password required message should be displayed"
        );
    }

}
