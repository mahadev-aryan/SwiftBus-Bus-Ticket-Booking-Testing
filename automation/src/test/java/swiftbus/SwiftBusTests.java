package swiftbus;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import swiftbus.pages.SearchPage;
import swiftbus.pages.BusPage;
import swiftbus.pages.PassengerPage;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SwiftBusTests {

    WebDriver driver;

    SearchPage searchPage;
    BusPage busPage;
    PassengerPage passengerPage;

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        String appUrl = Path.of("../application/index.html")
                .toAbsolutePath()
                .normalize()
                .toUri()
                .toString();

        driver.get(appUrl);

        searchPage = new SearchPage(driver);
        busPage = new BusPage(driver);
        passengerPage = new PassengerPage(driver);
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void validRouteShowsBusResults() {

        searchPage.searchBuses();

        assertTrue(searchPage.areResultsVisible());

        assertTrue(
            searchPage.getBusResults().contains("Swift Express")
        );
    }

    @Test
    void sameSourceAndDestinationShowsValidation() {

        searchPage.enterDestination("Mysuru");

        searchPage.searchBuses();

        assertEquals(
            "Source and destination must be different.",
            searchPage.getErrorMessage()
        );
    }

    @Test
    void bookedSeatIsDisabled() {

        searchPage.searchBuses();

        busPage.selectBus("Swift Express");

        assertTrue(busPage.isSeatDisabled("A2"));
    }

    @ParameterizedTest
    @CsvSource({
        "4,false",
        "5,true",
        "6,true",
        "52,true",
        "99,true",
        "100,true",
        "101,false"
    })
    void passengerAgeBoundaryValues(
        int age, boolean expectedValid
    ) {

        searchPage.searchBuses();

        busPage.selectBus("Swift Express");

        busPage.selectSeat("A1");

        busPage.continueToPassengerDetails();

        passengerPage.enterPassengerDetails(
            "Test Passenger",
            age,
            "9876543210"
        );

        passengerPage.clickPay();

        assertEquals(
            expectedValid,
            passengerPage.isPaymentVisible(),
            "Age boundary: " + age
        );
    }
    @ParameterizedTest
@CsvSource({
    "123456789,false",
    "1234567890,true",
    "12345678901,false"
})
void mobileNumberBoundaryValues(
    String mobile, boolean expectedValid
) {

    searchPage.searchBuses();

    busPage.selectBus("Swift Express");

    busPage.selectSeat("A1");

    busPage.continueToPassengerDetails();

    passengerPage.enterPassengerDetails(
        "Test Passenger",
        25,
        mobile
    );

    passengerPage.clickPay();

    assertEquals(
        expectedValid,
        passengerPage.isPaymentVisible(),
        "Mobile number length: " + mobile.length()
    );
}
}