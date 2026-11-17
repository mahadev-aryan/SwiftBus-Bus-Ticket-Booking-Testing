package swiftbus;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.nio.file.Path;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SwiftBusTests {
    WebDriver driver;
    WebDriverWait wait;
    String appUrl;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(); // Selenium Manager resolves the driver in current Selenium versions.
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        appUrl = Path.of("../application/index.html").toAbsolutePath().normalize().toUri().toString();
        driver.get(appUrl);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void validRouteShowsBusResults() {
        driver.findElement(By.id("searchBtn")).click();
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results"))).isDisplayed());
        assertTrue(driver.findElement(By.id("busList")).getText().contains("Swift Express"));
    }

    @Test
    void sameSourceAndDestinationShowsValidation() {
        driver.findElement(By.id("destination")).clear();
        driver.findElement(By.id("destination")).sendKeys("Mysuru");
        driver.findElement(By.id("searchBtn")).click();
        assertEquals("Source and destination must be different.", driver.findElement(By.id("searchError")).getText());
    }

    @Test
    void bookedSeatIsDisabled() {
        driver.findElement(By.id("searchBtn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-bus='Swift Express']"))).click();
        assertTrue(driver.findElement(By.xpath("//button[normalize-space()='A2']")).isEnabled() == false);
    }

    @ParameterizedTest
    @CsvSource({"4,false","5,true","6,true","52,true","100,true","101,false"})
    void passengerAgeBoundaryValues(int age, boolean expectedValid) {
        // Verify the implemented boundary rule directly through the UI after navigating the flow.
        driver.findElement(By.id("searchBtn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-bus='Swift Express']"))).click();
        driver.findElement(By.xpath("//button[normalize-space()='A1']")).click();
        driver.findElement(By.id("continueBtn")).click();
        driver.findElement(By.id("passengerName")).sendKeys("Test Passenger");
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
        driver.findElement(By.id("mobile")).sendKeys("9876543210");
        driver.findElement(By.id("payBtn")).click();
        boolean accepted = !"true".equals(driver.findElement(By.id("payment")).getAttribute("hidden"));
        assertEquals(expectedValid, accepted, "Age boundary: " + age);
    }
}