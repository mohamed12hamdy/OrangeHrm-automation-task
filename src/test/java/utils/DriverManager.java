package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


public class DriverManager {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            String browser = PropertyReader.get("browser", "chrome");
            boolean headless = PropertyReader.getBoolean("headless.mode", false);
            WebDriver driver;

            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver(getChromeOptions(headless));
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver(getFirefoxOptions(headless));
            } else {
                throw new RuntimeException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            threadLocalDriver.set(driver);
        }
        return threadLocalDriver.get();
    }

    /**
     * Configure Chrome options including headless mode, notifications, and performance settings
     */
    private static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        // Disable notifications and popups
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        // Performance optimizations
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        return options;
    }

    /**
     * Configure Firefox options including headless mode and performance settings
     */
    private static FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("--headless");
        }

        return options;
    }

    public static void quitDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove(); // Remove from ThreadLocal to prevent memory leaks
        }
    }
}