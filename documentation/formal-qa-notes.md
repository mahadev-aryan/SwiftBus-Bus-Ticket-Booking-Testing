# Formal QA Documentation — SwiftBus Bus Ticket Booking System

## 1. Equivalence Class Partitioning (ECP)

Equivalence Class Partitioning is a black-box testing technique that divides input data into valid and invalid groups. One representative value from each group is selected for testing.

### 1.1 Route Validation

| Input condition | Equivalence class | Representative input | Expected result |
|---|---|---|---|
| Source is empty | Invalid | Empty source | Error message displayed |
| Destination is empty | Invalid | Empty destination | Error message displayed |
| Source and destination are identical | Invalid | Mysuru → Mysuru | Error message displayed |
| Source and destination are different | Valid | Mysuru → Bengaluru | Bus results displayed |

### 1.2 Journey Date Validation

| Input condition | Equivalence class | Representative input | Expected result |
|---|---|---|---|
| Date is empty | Invalid | No date | Error message displayed |
| Date is in the past | Invalid | Yesterday | Error message displayed |
| Date is today | Valid | Today's date | Bus results displayed |
| Date is in the future | Valid | Tomorrow | Bus results displayed |

### 1.3 Passenger Age Validation

The application accepts integer passenger ages from 5 to 100, inclusive.

| Input condition | Equivalence class | Representative input | Expected result |
|---|---|---|---|
| Age below 5 | Invalid | 4 | Validation error |
| Age between 5 and 100 | Valid | 25 | Passenger details accepted |
| Age above 100 | Invalid | 101 | Validation error |
| Non-integer age | Invalid | 5.5 | Validation error |

### 1.4 Mobile Number Validation

The application accepts mobile numbers containing exactly 10 digits.

| Input condition | Equivalence class | Representative input | Expected result |
|---|---|---|---|
| Mobile number is empty | Invalid | Empty | Validation error |
| Fewer than 10 digits | Invalid | 987654321 | Validation error |
| Exactly 10 digits | Valid | 9876543210 | Accepted |
| More than 10 digits | Invalid | 98765432101 | Validation error |
| Contains alphabetic characters | Invalid | 98765ABCDE | Validation error |

### 1.5 Seat Selection

| Input condition | Equivalence class | Representative input | Expected result |
|---|---|---|---|
| Seat is already booked | Invalid | A2 | Seat cannot be selected |
| Seat is available | Valid | A1 | Seat can be selected |
| No seat selected | Invalid | No selection | Error displayed when continuing |

---

## 2. Boundary Value Analysis (BVA)

Boundary Value Analysis is a black-box testing technique that checks values at, immediately below, and immediately above the boundaries of an input range.

### 2.1 Passenger Age Boundaries

The valid age range is 5 to 100.

| Test case | Age | Boundary condition | Expected result |
|---|---:|---|---|
| TC01 | 4 | Minimum - 1 | Invalid |
| TC02 | 5 | Minimum | Valid |
| TC03 | 6 | Minimum + 1 | Valid |
| TC04 | 52 | Nominal value | Valid |
| TC05 | 99 | Maximum - 1 | Valid |
| TC06 | 100 | Maximum | Valid |
| TC07 | 101 | Maximum + 1 | Invalid |

### 2.2 Mobile Number Boundaries

The valid mobile number length is exactly 10 digits.

| Test case | Number of digits | Expected result |
|---|---:|---|
| TC08 | 9 | Invalid |
| TC09 | 10 | Valid |
| TC10 | 11 | Invalid |

### 2.3 Journey Date Boundaries

| Test case | Journey date | Expected result |
|---|---|---|
| TC11 | Yesterday | Invalid |
| TC12 | Today | Valid |
| TC13 | Tomorrow | Valid |

---

## 3. White-Box Testing — Control Flow Graph (CFG)

White-box testing examines the internal logic and execution paths of a program. For this project, the passenger validation handler inside `application/app.js` is selected for CFG analysis.

### Selected Code

The selected code begins with:

```javascript
$("payBtn").addEventListener("click",()=>{
```

The handler performs three validation checks:

1. Checks whether passenger name, age or mobile number is empty.
2. Checks whether the passenger's age is invalid (not an integer or outside the range 5–100).
3. Checks whether the mobile number is invalid (not exactly 10 digits).

### 3.1 CFG Nodes

| Node | Description |
|---|---|
| N1 | Start |
| N2 | Read passenger details and clear previous error |
| N3 | Are any required fields missing? |
| N4 | Display missing-fields error and return |
| N5 | Is passenger age invalid? |
| N6 | Display invalid-age error and return |
| N7 | Is mobile number invalid? |
| N8 | Display invalid-mobile error and return |
| N9 | Store valid passenger details |
| N10 | Display payment section |
| N11 | End |

### 3.2 CFG Edges

| Edge | Connection |
|---|---|
| E1 | N1 → N2 |
| E2 | N2 → N3 |
| E3 | N3 (Yes) → N4 |
| E4 | N4 → N11 |
| E5 | N3 (No) → N5 |
| E6 | N5 (Yes) → N6 |
| E7 | N6 → N11 |
| E8 | N5 (No) → N7 |
| E9 | N7 (Yes) → N8 |
| E10 | N8 → N11 |
| E11 | N7 (No) → N9 |
| E12 | N9 → N10 |
| E13 | N10 → N11 |

### 3.3 Control Flow Graph Representation

```text
                 ┌───────────┐
                 │ N1: START │
                 └─────┬─────┘
                       |
                 ┌─────▼─────┐
                 │ N2: Read  │
                 │ passenger │
                 │  details  │
                 └─────┬─────┘
                       |
              ┌────────▼─────────┐
              │ N3: Are required │
              │ fields missing?  │
              └────┬────────┬─────┘
                  Yes       No
                   |         |
             ┌─────▼────┐    |
             │ N4: Show │    |
             │ missing  │    |
             │  error   │    |
             └─────┬────┘    |
                   |         |
                   |    ┌────▼───────────┐
                   |    │ N5: Is age     │
                   |    │ invalid?       │
                   |    └────┬───────┬───┘
                   |        Yes      No
                   |         |        |
                   |    ┌────▼────┐   |
                   |    │ N6: Show│   |
                   |    │ age     │   |
                   |    │ error   │   |
                   |    └────┬────┘   |
                   |         |        |
                   |         |   ┌────▼──────────┐
                   |         |   │ N7: Is mobile │
                   |         |   │ invalid?      │
                   |         |   └────┬──────┬───┘
                   |         |       Yes     No
                   |         |        |       |
                   |         |   ┌────▼────┐  |
                   |         |   │ N8: Show│  |
                   |         |   │ mobile  │  |
                   |         |   │ error   │  |
                   |         |   └────┬────┘  |
                   |         |        |       |
                   |         |        |  ┌────▼─────┐
                   |         |        |  │ N9: Store│
                   |         |        |  │ details  │
                   |         |        |  └────┬─────┘
                   |         |        |       |
                   |         |        |  ┌────▼─────┐
                   |         |        |  │ N10: Show│
                   |         |        |  │ payment  │
                   |         |        |  └────┬─────┘
                   |         |        |       |
                   ▼         ▼        ▼       ▼
                 ┌─────────────────────────────┐
                 │          N11: END           │
                 └─────────────────────────────┘
```

---

## 4. Cyclomatic Complexity

Cyclomatic Complexity measures the number of linearly independent paths through a program.

### Formula 1

**V(G) = E − N + 2P**

Where:

- E = Number of edges
- N = Number of nodes
- P = Number of connected components

For the selected passenger validation handler:

- E = 13
- N = 11
- P = 1

Substituting the values:

V(G) = 13 − 11 + 2(1)

V(G) = 13 − 11 + 2

**V(G) = 4**

### Formula 2

**Cyclomatic Complexity = Number of decision points + 1**

There are three decision points:

1. Required fields validation.
2. Passenger age validation.
3. Mobile number validation.

Therefore:

V(G) = 3 + 1

**V(G) = 4**

### Result

The cyclomatic complexity of the selected passenger validation handler is **4**. Therefore, four independent execution paths are required for basis path testing.

---

## 5. Basis Path Testing

Basis Path Testing identifies independent execution paths through a program using its Control Flow Graph.

Since the calculated cyclomatic complexity is 4, four independent paths are identified.

### 5.1 Independent Paths

| Path | Execution sequence | Expected result |
|---|---|---|
| P1 | N1 → N2 → N3 (Yes) → N4 → N11 | Missing-fields error |
| P2 | N1 → N2 → N3 (No) → N5 (Yes) → N6 → N11 | Invalid-age error |
| P3 | N1 → N2 → N3 (No) → N5 (No) → N7 (Yes) → N8 → N11 | Invalid-mobile error |
| P4 | N1 → N2 → N3 (No) → N5 (No) → N7 (No) → N9 → N10 → N11 | Passenger details stored and payment section displayed |

### 5.2 Path Explanation

**Path 1 — Missing Passenger Details**

The application detects that at least one required passenger field is empty. It displays a missing-fields error and terminates the current validation flow.

**Path 2 — Invalid Passenger Age**

All required fields are filled, but the passenger's age is invalid. The application displays an age validation error and terminates the current validation flow.

**Path 3 — Invalid Mobile Number**

All required fields are filled and the age is valid, but the mobile number does not contain exactly 10 digits. The application displays a mobile validation error.

**Path 4 — Valid Passenger Details**

All passenger details satisfy the validation conditions. The application stores the passenger information and displays the payment section.

### Conclusion

The CFG contains 11 nodes and 13 edges, resulting in a cyclomatic complexity of 4. Four independent paths have been identified to cover the three validation decisions and the successful execution flow.

## 6. Test Execution Evidence

The SwiftBus automated test suite was executed successfully using Maven, Selenium WebDriver, Java and JUnit 5.

### 6.1 Latest Verified Execution Results

| Metric | Result |
|---|---:|
| Total tests | 13 |
| Passed | 13 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |
| Build status | BUILD SUCCESS |

### 6.2 Testing Tools and Technologies

| Tool | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| JUnit 5 | Test execution and assertions |
| Maven | Build and dependency management |
| Google Chrome | Browser used for testing |
| Page Object Model | Test automation architecture |

### 6.3 Automated Test Coverage

The automated test suite includes:

1. Valid bus route search.
2. Same source and destination validation.
3. Booked seat validation.
4. Passenger age boundary testing.
5. Mobile number boundary testing.

Parameterized tests are used to test multiple age and mobile number values.

### 6.4 Evidence Attached

The following screenshots are included or will be included in the project evidence folder:

1. Successful Maven test execution.
2. SwiftBus home/search page.
3. Bus search results.
4. Booked and available seats.
5. Passenger validation.
6. Payment and booking confirmation.
7. Final passenger validation Control Flow Graph.

### 6.5 Conclusion

The Selenium automation suite executed 13 tests successfully, with zero failures and zero errors. The successful execution demonstrates that the implemented automated test cases work as expected for the tested scenarios.

**Note:** The CFG and cyclomatic complexity calculation apply specifically to the passenger validation handler, not the entire application. ECP and BVA cases documented but not included in the automated suite should not be reported as executed.