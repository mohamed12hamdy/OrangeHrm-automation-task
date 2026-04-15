package data;

import org.testng.annotations.DataProvider;

public class InvalidLoginDataProvider {

    @DataProvider(name = "InvalidLoginData")
    public static Object[][] loginData() {
        return new Object[][]{
                {"Admin", "admin122"},  //valid username, invalid password
                {"Admin12", "admin123"},  //invalid username, valid password
                {"Both Invalid", "wronguser"}, //both username and password are invalid
        };
    }
}
