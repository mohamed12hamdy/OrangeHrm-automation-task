package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {

    // ThreadLocal to store WebDriver instances per thread for parallel execution support
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            String browser = PropertyReader.get("browser", "chrome");
            WebDriver driver;

            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            threadLocalDriver.set(driver);
        }
        return threadLocalDriver.get();
    }

    public static void quitDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove(); // Remove from ThreadLocal to prevent memory leaks
        }
    }
}