package tests.home;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import tests.LoginBaseTest;

public class HomePageTests extends LoginBaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void initHomePage() {
        homePage = new HomePage(driver);
    }

    @Test(dataProvider = "widgetsData",dataProviderClass = data.HomeDataProvider.class)
    public void testWidgetIsVisible(String widgetName) {
        Assert.assertTrue(
                homePage.isWidgetVisible(widgetName),
                widgetName + " widget is not visible!"
        );
    }
}
