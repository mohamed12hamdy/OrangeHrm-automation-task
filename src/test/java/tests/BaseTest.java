package tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.DriverManager;
import utils.LoggerManager;
import utils.PropertyReader;

public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;

    protected static final Logger log = LoggerManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(PropertyReader.get("base.url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
