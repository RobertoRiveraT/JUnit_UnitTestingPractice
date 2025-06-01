
# 🧪 JUnit Unit Testing Practice – `Demo.java`

This project is a programming exercise focused on learning how to create **effective unit tests using JUnit**, including how to simulate input/output for programs that interact with the console.

---

## 📁 Project Structure

```
JUnit_UnitTestingPractice/
├── build.gradle
├── gradlew / gradlew.bat
├── settings.gradle
├── src/
│   ├── main/java/Demo.java
│   └── test/java/DemoTest.java
└── build/reports/tests/test/index.html
```

---

## ✅ Objective

- Write JUnit tests for the `Demo.java` class, which reads 3 numbers and checks if they form a valid triangle.
- Test both the logic method `isTriangle(a, b, c)` and the `main()` method which handles user input/output.

---

## 🧪 What We Tested

### 1. Unit Tests for `isTriangle()`
We covered a variety of test types:

| Test Type                  | Description |
|---------------------------|-------------|
| ✅ Positive Test           | Valid triangle: `3, 4, 5` |
| ❌ Negative Test           | Invalid triangle: `1, 2, 3` |
| 📏 Boundary Test           | Edge of triangle inequality: `5, 5, 10` |
| 🧮 Equivalence Classes     | Valid group: `6, 7, 8`, Invalid group: `1, 1, 5` |
| ⚠️ Edge Cases              | Zero, negative numbers, very large values |

### 2. Simulated Console Input/Output for `main()`

We used `ByteArrayInputStream` and `ByteArrayOutputStream` to:

- Simulate user input (`System.in`) with values like `"3\n4\n5\n"`.
- Capture printed output (`System.out`) and verify expected messages:
  - `"This is a triangle."`
  - `"This is not a triangle."`

---

## 🛠 Commands Used

### ▶️ To run all tests (Windows PowerShell or CMD):

```bash
.\gradlew.bat clean build
```

### ▶️ To run all tests (macOS / Linux / Git Bash):

```bash
./gradlew clean build
```

### 📂 To view test results:

Open the following file in your browser:

```
build/reports/tests/test/index.html
```

This HTML report shows:
- ✔️ Which tests passed
- ❌ Which tests failed
- Details about each execution

---

## 📝 What We Learned

- How to write meaningful and varied **unit tests** with JUnit
- How to use **boundary and equivalence partitioning** in test design
- How to **simulate user input/output** in unit tests for `main()` methods
- How to use Gradle to **build and test Java applications** via the command line
- How to interpret **JUnit HTML reports** to evaluate test coverage and correctness

---

## 📌 Notes

- The `Demo.java` class was **not modified** for testing. All test logic was handled externally in `DemoTest.java`.
- Redirection of `System.in` and `System.out` is **temporary during tests** and restored afterward.
