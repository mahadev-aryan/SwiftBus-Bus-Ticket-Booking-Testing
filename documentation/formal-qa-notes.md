# Formal QA Notes — SwiftBus

## Equivalence Partitioning (ECP)
Passenger age rule in this demo: integer age 5 through 100 inclusive.
- Invalid class 1: age < 5 (representative: 4)
- Valid class: 5 <= age <= 100 (representative: 25)
- Invalid class 2: age > 100 (representative: 101)
Other partitions: route blank / same route / different route; mobile blank / non-10-digit / 10 digits.

## Boundary Value Analysis (BVA)
For age range [5, 100], 7 values:
4 (min-1), 5 (min), 6 (min+1), 52 (nominal), 99 (max-1), 100 (max), 101 (max+1).

## Control Flow Graph (CFG) target
Use the `payBtn` passenger validation handler in `application/app.js` or create a separate pure Java booking-validation method for white-box analysis. Draw nodes and edges from the actual code used in the submitted version.

## Cyclomatic Complexity
For one connected CFG: V(G) = E - N + 2; equivalently decisions + 1. Count the actual CFG nodes/edges and report the computed result. Do not copy an illustrative number.

## Basis paths
Derive independent paths from the actual CFG. At minimum test missing fields, invalid age, invalid mobile, valid passenger details, payment success and payment failure. Note that UI automation is black-box; source-level CFG analysis applies to the selected JavaScript or Java logic method.

## Evidence checklist
Record actual total/passed/failed/skipped/duration, add screenshots of execution and defects, and do not invent results.
