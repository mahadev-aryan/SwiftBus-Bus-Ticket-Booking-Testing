# SwiftBus – Bus Ticket Booking System Testing

## 1. Project Overview

SwiftBus is a demo bus ticket booking web application developed to demonstrate end-to-end web application testing using Selenium WebDriver, Java and JUnit 5.

The project focuses on testing the application's functionality through automated test cases and formal Software Testing and Quality Assurance techniques.

## 2. Project Objectives

- Automate the bus ticket booking workflow.
- Validate user inputs using black-box testing techniques.
- Analyze application logic using white-box testing techniques.
- Implement Selenium WebDriver with JUnit 5.
- Apply the Page Object Model (POM) design pattern.
- Generate automated test execution results using Maven.

## 3. Application Features

- Bus search using source, destination and journey date.
- Validation for empty fields and identical locations.
- Display of available buses.
- Bus selection and seat selection.
- Display of booked and available seats.
- Passenger information validation.
- Age validation (5–100 years).
- Mobile number validation (exactly 10 digits).
- Mock payment process.
- Booking confirmation and booking history.
- Booking cancellation.

**Note:** This is a demonstration application. It does not use a real backend, real payment gateway or live bus booking service.

## 4. Technologies Used

| Technology | Purpose |
|---|---|
| HTML | Web application structure |
| CSS | Application styling |
| JavaScript | Application functionality and validation |
| Java | Automation programming |
| Selenium WebDriver | Browser automation |
| JUnit 5 | Test execution and assertions |
| Maven | Build and dependency management |
| Google Chrome | Browser used for automation |

## 5. Testing Techniques

### Black-Box Testing

**Equivalence Class Partitioning (ECP)**
- Route validation.
- Journey date validation.
- Passenger age validation.
- Mobile number validation.
- Seat selection validation.

**Boundary Value Analysis (BVA)**
- Passenger age boundaries: 4, 5, 6, 52, 99, 100 and 101.
- Mobile number length boundaries: 9, 10 and 11 digits.
- Journey date boundaries: yesterday, today and tomorrow.

### White-Box Testing

- Control Flow Graph (CFG).
- Cyclomatic Complexity.
- Basis Path Testing.

The passenger validation handler has:
- 11 nodes.
- 13 edges.
- 3 decision points.
- Cyclomatic Complexity = 4.
- 4 independent execution paths.

## 6. Automation Architecture

The project uses the Page Object Model (POM) to separate page elements and interactions from test cases.

```text
automation/
└── src/
    └── test/
        └── java/
            └── swiftbus/
                ├── SwiftBusTests.java
                └── pages/
                    ├── SearchPage.java
                    ├── BusPage.java
                    └── PassengerPage.java
```

### Page Classes

| Class | Responsibility |
|---|---|
| SearchPage | Bus search and route validation |
| BusPage | Bus and seat selection |
| PassengerPage | Passenger details and validation |
| SwiftBusTests | Automated test cases |

## 7. Project Structure

```text
SwiftBus-Testing-Starter/
│
├── application/
│   ├── index.html
│   ├── style.css
│   └── app.js
│
├── automation/
│   ├── pom.xml
│   └── src/
│       └── test/
│           └── java/
│               └── swiftbus/
│                   ├── SwiftBusTests.java
│                   └── pages/
│                       ├── SearchPage.java
│                       ├── BusPage.java
│                       └── PassengerPage.java
│
├── documentation/
│   ├── evidence/
│   ├── test-cases.csv
│   └── formal-qa-notes.md
│
├── README.md
└── .gitignore
```

## 8. Prerequisites

Install the following before running the automation tests:

1. Java Development Kit (JDK).
2. Apache Maven.
3. Google Chrome browser.
4. Git (optional, for repository management).

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

## 9. How to Run the Application

1. Open the project folder.
2. Navigate to the `application` directory.
3. Open `index.html` in Google Chrome.
4. Enter the source, destination and journey date.
5. Click Search Buses to view available buses.

## 10. How to Run Automated Tests

Open a terminal in the project directory.

Navigate to the automation folder:

```bash
cd automation
```

Execute the test suite:

```bash
mvn clean test
```

Maven will compile the test code, launch Chrome through Selenium WebDriver and execute the JUnit 5 test cases.

## 11. Test Execution Results

Latest verified execution:

| Metric | Result |
|---|---:|
| Total tests | 13 |
| Passed | 13 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |
| Build status | BUILD SUCCESS |

The automated suite includes:
- Valid bus route search.
- Same source and destination validation.
- Booked seat validation.
- Passenger age boundary tests.
- Mobile number boundary tests.

## 12. Test Reports and Evidence

Test evidence is maintained in:

```text
documentation/evidence/
```

The evidence includes:
- Maven test execution screenshot.
- Application screenshots.
- Bus search and seat selection screenshots.
- Passenger validation screenshots.
- CFG diagram.

Maven Surefire test reports are generated under:

```text
automation/target/surefire-reports/
```

## 13. Limitations

- The application uses demonstration data.
- No real-time bus information is retrieved.
- No actual payment transaction is processed.
- Booking information is stored using browser local storage.
- Automated test coverage is limited to the implemented test cases.

## 14. Conclusion

The SwiftBus project demonstrates end-to-end web application testing using Selenium WebDriver, Java and JUnit 5.

It combines black-box testing, white-box testing, Page Object Model architecture and automated test execution to evaluate important bus booking functionalities.

The latest verified automation execution completed successfully with 13 tests passed and zero failures.

## 15. Team Details

**Project Title:** End-to-End Bus Ticket Booking System Testing Using Selenium WebDriver, Java & JUnit 5

**Team Members:**
- Member 1: Mahadeva Prasad D 20241ISE0193
- Member 2: Sumeet S Patil 20241ISE0211

**Course:** Software Testing and Quality Assurance