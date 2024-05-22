# Java-API-Test

Test API automation project using Java/JUnit5/REST Assured/Gradle/Allure 

## Table of Contents

- [Installation](#installation)
- [Running Tests](#running-tests)
- [Running WordFrequencyAnalyzer](#running-wordfrequencyanalyzer)

## Installation

Follow these steps to set up the project on your local machine.

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) 8 or later
- [Gradle](https://gradle.org/install/)
- [Git](https://git-scm.com/)

### Clone the Repository

Clone the project repository from GitHub:

```bash
git clone https://github.com/Subsistance/Java-API-Test.git
cd Java-API-Test
```

### Build the Project

Open the project using your IDE of choice (i used IntelliJ IDEA)

Use Gradle to build the project by terminal:

```bash
./gradlew build
```

This will compile the project and download all necessary dependencies.

## Running Tests
### Run All Tests

To run all tests in the project, use the following terminal command:

```bash
./gradlew test
```

But it's better to use the custom command i've provided, that does clean, running tests and then opens the Allure report.

```bash
./gradlew clean.test.allureserve
```

You can also manually open the report file after running tests by using build/reports/tests/test/index.html

### Additional notes on tests

Just to be clear: i know that the best practices instruct that actual logic/methods should be separated from test steps
for a more modular structure and separation of concern, etc. 
But keeping in mind that this is a relatively trivial personal project, i found that doing so overcomplicated things.
I ultimately decided to keep it simple and left almost everything in the same test class except for the reusable setup method.

## Running WordFrequencyAnalyzer

The WordFrequencyAnalyzer class fetches posts from a mock API, processes the text, and outputs the top 10 most frequent words in the post bodies.

To run the WordFrequencyAnalyzer class, you need to open com.utils.WordFrequencyAnalyzer.java and simply Run it.

### Output

The output will display the top 10 words and their frequencies in the following format:

```bash
1. Word1 - 10
2. Word2 - 7
...
10. Word10 - 2
```