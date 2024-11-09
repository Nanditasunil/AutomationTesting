# Java Selenium Testing with TestNG

This project is designed for automated testing using **Selenium** WebDriver with **TestNG** framework. The tests are focused on verifying the functionality of various UI components and features on two websites, including input fields, buttons, dropdown menus, radio buttons, and more. Additionally, the project incorporates data-driven testing using Excel test cases, file upload functionality, SSL permissions testing, and image capture during test execution.

## Websites Tested

1. **User Registration Profile Form (WP Everest)**
   - URL: [https://demo.wpeverest.com/user-registration/profile-registration-form/](https://demo.wpeverest.com/user-registration/profile-registration-form/)

2. **Blaze Demo Application**
   - URL: [https://www.blazedemo.com/](https://www.blazedemo.com/)

## Features Tested

### 1. **Input Fields**
   - Test cases to verify the functionality of input fields such as textboxes and text areas.
   - Ensured validation of proper input handling and error messages.

### 2. **Buttons**
   - Tested the functionality of buttons, ensuring they trigger the correct action when clicked.

### 3. **Dropdown Menus**
   - Verified that dropdown menus function correctly, allowing users to select an option from the available choices.

### 4. **Radio Buttons**
   - Ensured that radio buttons work as expected, with only one option being selectable at a time.

### 5. **Data-Driven Testing**
   - Implemented data-driven testing using Excel files for parameterized test cases.
   - Automated multiple test scenarios by reading input values from Excel sheets to test various combinations of data.

### 6. **SSL Permissions**
   - Verified SSL permissions to ensure the websites are accessible via HTTPS, confirming secure connections.
   - Tested SSL certificate handling to ensure proper security configurations.

### 7. **File Upload**
   - Tested file upload functionality to ensure users can upload files successfully through the application.

### 8. **Capture Image**
   - Implemented image capture functionality to capture screenshots during test execution, ensuring easy debugging and documentation.

## TestNG Report
The project includes an integrated **TestNG** reporting system, which generates detailed test execution reports. These reports provide insights into passed/failed tests, along with any errors or exceptions encountered during execution. 

- **TestNG Reports**: The reports are generated automatically after each test run and can be found in the `/test-output` directory.
- The reports contain detailed logs for each test, helping in analyzing and debugging the results efficiently.

## Prerequisites

- Java 8 or higher
- Selenium WebDriver
- TestNG
- Apache POI (for Excel integration)
- WebDriver (Chrome, Firefox, etc.)
- Maven (for project management)


