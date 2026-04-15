package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementActions {

    private WaitHelper wait;

    public ElementActions(WaitHelper wait) {
        this.wait = wait;
    }

    public ElementActions click(By locator) {
        wait.waitForClickability(locator).click();
        return this;
    }


    public ElementActions sendKeys(By locator, String text) {
        WebElement element = wait.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
        return this;
    }

}
