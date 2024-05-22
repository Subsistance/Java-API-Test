# Project Title

A brief description of your project.

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
git clone https://github.com/yourusername/your-repository.git
cd your-repository
```

### Build the Project

Use Gradle to build the project:

```bash
./gradlew build
```

## Running Tests
### Run All Tests

To run all tests in the project, use the following command:

```bash
./gradlew test
```

But it's better to use the custom command i've provided, that does clean, running tests and then opens the Allure report.

```bash
./gradlew clean.test.allureserve
```

You can also manually open the report file after running tests by using build/reports/tests/test/index.html

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