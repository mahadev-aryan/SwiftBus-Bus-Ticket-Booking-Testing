package swiftbus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    By searchButton = By.id("searchBtn");
    By source = By.id("source");
    By destination = By.id("destination");
    By journeyDate = By.id("journeyDate");
    By searchError = By.id("searchError");
    By results = By.id("results");
    By busList = By.id("busList");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public void searchBuses() {
        driver.findElement(searchButton).click();
    }

    public void enterDestination(String value) {
        driver.findElement(destination).clear();
        driver.findElement(destination).sendKeys(value);
    }

    public String getErrorMessage() {
        return driver.findElement(searchError).getText();
    }

    public boolean areResultsVisible() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(results)
        ).isDisplayed();
    }

    public String getBusResults() {
        return driver.findElement(busList).getText();
    }
}