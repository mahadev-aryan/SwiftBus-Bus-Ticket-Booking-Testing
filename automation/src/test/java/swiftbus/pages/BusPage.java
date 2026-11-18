package swiftbus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BusPage {

    WebDriver driver;
    WebDriverWait wait;

    public BusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public void selectBus(String busName) {
        By busButton = By.cssSelector(
            "[data-bus='" + busName + "']"
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(busButton)
        ).click();
    }

    public boolean isSeatDisabled(String seatName) {
        By seat = By.xpath(
            "//button[normalize-space()='" + seatName + "']"
        );

        return !driver.findElement(seat).isEnabled();
    }

    public void selectSeat(String seatName) {
        By seat = By.xpath(
            "//button[normalize-space()='" + seatName + "']"
        );

        driver.findElement(seat).click();
    }

    public void continueToPassengerDetails() {
        driver.findElement(By.id("continueBtn")).click();
    }
}