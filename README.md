# SwiftBus — Bus Ticket Booking System Testing

A small custom bus-booking demo application plus a Selenium WebDriver + Java + JUnit 5 UI automation starter project. It is intended for academic testing only. Payment is simulated; no real payment is processed.

## Repository structure
- `application/` — HTML, CSS and JavaScript demo application.
- `automation/` — Maven project containing Selenium/JUnit 5 tests.
- `documentation/` — test design notes and formal QA templates.
- `presentation/` — add the final CA3 PPT here.
- `screenshots/` — add genuine execution screenshots here.

## Prerequisites
- Java JDK 17+
- Apache Maven 3.9+
- Google Chrome
- VS Code with Extension Pack for Java (recommended)

Check installations in a terminal:
```bash
java -version
mvn -version
```

## Run the application
Open `application/index.html` directly in Chrome, or serve the folder:
```bash
cd application
python -m http.server 8000
```
Then open `http://localhost:8000`.

## Run Selenium tests
Open a second terminal:
```bash
cd automation
mvn clean test
```
Selenium Manager attempts to obtain a compatible ChromeDriver automatically. Ensure Chrome and internet access are available on the first run.

## Test report
After Maven execution, inspect:
`automation/target/surefire-reports/`

## Test scope
Login is not implemented in this starter version. Current implemented flows cover route search, bus results, seat availability, passenger age validation, mock payment, confirmation and cancellation. Extend the app and tests as time permits.

## Formal QA documentation
See `documentation/` for ECP, BVA, CFG, cyclomatic complexity and basis path templates. Use the actual implemented logic and measured execution results in your final submission.

## Important
- Do not enter real payment or personal information.
- Do not claim tests passed until you have run them.
- Replace assumptions in test design with the application's actual validation rules.
