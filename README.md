# OpenWeatherMap test suite
This project was created to provide a small testing framework for openweathermap.org 

The main goal is to test Api key generation via UI and send GET requests to REST API

 
## Getting Started

### Prerequisites

* Google Chrome version 83.x
* Java 8
* Maven 3.6.x


### Main tools used

* TestNG testing framework

For UI tests:
* Selenium Webdriver

For REST API tests:
* RestAssured Java-based library


### Installing

To install this application, run the following commands:

```
git clone https://github.com/gintareurban/OpenWeatherMapProject.git
```

This will get a copy of the project installed locally.


### Using


Run below comand to run the test suite, which will execute all tests and open the test report on completion:
```
mvn clean test -DsuiteXmlFile=OpenWeatherMapSuite1.xml
```

In order to run single tests separately, TestGenerateAPIKey has to be executed at least once in order to generate a key which is used in all other tests. It could be done either by executing it as part of the test suite - OpenWeatherMapSuite1.xml, or by executing test on it's own (see below).

Run below comand to execute a single test, passing test class name as argument:
```
mvn clean test -Dtest=TestGenerateAPIKey
```
