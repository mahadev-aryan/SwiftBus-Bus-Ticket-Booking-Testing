# SwiftBus – Bus Ticket Booking System Testing

An end-to-end software testing project that automates and evaluates a bus ticket booking web application using Selenium WebDriver, Java, and JUnit 5.

## Project Overview

SwiftBus is a demo bus ticket booking application developed to demonstrate software testing concepts and automation techniques.

The project focuses on validating the application's functionality, identifying input-related errors, and verifying user workflows through automated testing.

## Objectives

- Apply Black-Box Testing techniques.
- Apply White-Box Testing techniques.
- Automate application workflows using Selenium WebDriver.
- Implement automated test cases using JUnit 5.
- Apply Page Object Model (POM) architecture.
- Evaluate test coverage using Control Flow Graphs and Cyclomatic Complexity.

## Technologies Used

- Java
- Selenium WebDriver
- JUnit 5
- Maven
- HTML
- CSS
- JavaScript
- Visual Studio Code
- Git and GitHub

## Application Features

- Journey source and destination selection.
- Journey date validation.
- Bus search functionality.
- Seat selection.
- Passenger information validation.
- Mock payment and booking confirmation.

**Note:** This is a demonstration application. It does not process real payments or make actual bus reservations.

## Testing Techniques

### 1. Black-Box Testing

**Equivalence Class Partitioning (ECP)**
- Valid and invalid routes.
- Empty and valid passenger details.
- Valid and invalid age groups.
- Valid and invalid mobile numbers.
- Available and booked seats.

**Boundary Value Analysis (BVA)**
- Age boundaries: 4, 5, 6, 52, 99, 100, 101.
- Mobile number boundaries: 9, 10, and 11 digits.
- Journey date boundaries: yesterday, today, and tomorrow.

### 2. White-Box Testing

- Control Flow Graph (CFG).
- Cyclomatic Complexity.
- Basis Path Testing.

The passenger validation logic has a Cyclomatic Complexity of **4**, resulting in four independent basis paths.

### 3. Automation Testing

- Selenium WebDriver for browser automation.
- JUnit 5 for test execution.
- Parameterized tests using `@ParameterizedTest` and `@CsvSource`.
- Page Object Model for maintainable test architecture.

## Project Structure

```text
SwiftBus-Bus-Ticket-Booking-Testing/
│
├── application/
│   ├── index.html
│   ├── style.css
│   └── app.js
│
├── automation/
│   ├── pom.xml
│   └── src/test/java/swiftbus/
│       ├── SwiftBusTests.java
│       └── pages/
│           ├── SearchPage.java
│           ├── BusPage.java
│           └── PassengerPage.java
│
├── documentation/
│   ├── README.md
│   ├── formal-qa-notes.md
│   ├── test-cases.csv
│   └── evidence/
│
└── README.md
```

## Test Execution Results

| Metric | Result |
|---|---:|
| Total Tests | 13 |
| Passed | 13 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |
| Build Status | SUCCESS |

All 13 automated test cases passed successfully in the latest confirmed execution.

## Running the Tests

### Prerequisites

- Java JDK
- Apache Maven
- Google Chrome
- Visual Studio Code

### Execution

1. Clone or download this repository.
2. Open the project in Visual Studio Code.
3. Open the `automation` folder as the working directory.
4. Run the Maven test lifecycle:

```bash
mvn clean test
```

## Documentation and Evidence

Detailed project documentation, formal QA analysis, test cases, and execution evidence are available in the `documentation` folder.

- [Formal QA Notes](documentation/formal-qa-notes.md)
- [Detailed Project Documentation](documentation/README.md)
- [Test Cases](documentation/test-cases.csv)

## Limitations

- The application uses simulated booking and payment functionality.
- No real payment gateway is integrated.
- No production backend or database is connected.
- Testing focuses on the implemented demonstration application's functionality.

## Conclusion

This project demonstrates the practical application of software testing principles through manual test design, formal QA techniques, and Selenium-based automation.

It combines black-box testing, white-box testing, and automated functional testing to evaluate a multi-step bus ticket booking workflow.

## Repository

GitHub: https://github.com/mahadev-aryan/SwiftBus-Bus-Ticket-Booking-Testing