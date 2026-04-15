package tests;

import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.PropertyReader;

public class LoginBaseTest extends BaseTest{

    @BeforeMethod
    public void setUp() {
        super.setUp();
        new LoginPage(getDriver()).login(PropertyReader.get("valid.username"),
                                         PropertyReader.get("valid.password"));
    }
}
