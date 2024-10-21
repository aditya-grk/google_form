package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Wrappers {
    protected WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToURL(String url) {
        driver.get(url);
    }

    public void enterText(By locator, String text) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(visibleText);
    }

    public String getText(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }
}
