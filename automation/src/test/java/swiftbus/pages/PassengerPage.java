package swiftbus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PassengerPage {

    WebDriver driver;

    By passengerName = By.id("passengerName");
    By age = By.id("age");
    By mobile = By.id("mobile");
    By payButton = By.id("payBtn");
    By payment = By.id("payment");
    By passengerError = By.id("passengerError");

    public PassengerPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPassengerError() {
    return driver.findElement(passengerError).getText();
}

    public void enterPassengerDetails(
        String name, int passengerAge, String phone
    ) {
        driver.findElement(passengerName).clear();
        driver.findElement(passengerName).sendKeys(name);

        driver.findElement(age).clear();
        driver.findElement(age).sendKeys(String.valueOf(passengerAge));

        driver.findElement(mobile).clear();
        driver.findElement(mobile).sendKeys(phone);
    }

    public void clickPay() {
        driver.findElement(payButton).click();
    }

    public boolean isPaymentVisible() {
        return driver.findElement(payment).isDisplayed();
    }
}