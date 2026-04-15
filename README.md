# OrangeHRM Automation Task

This project is an automation testing implementation for the OrangeHRM demo application using Java and Selenium WebDriver.

🔗 Application URL:
https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

---

## 🧪 Tech Stack
- Java
- Selenium WebDriver
- TestNG (optional)
- Page Object Model (POM)

---

## 📌 Project Structure (Recommended)
- pages → Page Object classes
- tests → Test cases
- utils → Driver setup & helpers
- data → Test data (if data-driven is used)

---

## 🚀 Test Scenarios

### ✅ Part 1: Successful Login
- Login using valid credentials
- Verify user is redirected to the Home page

---

### ❌ Part 2: Invalid Login Scenarios
- Valid username + invalid password  
- Invalid username + valid password  
- One field is empty (username or password)

---

### ❌ Part 3: Both Fields Empty
- Submit login with empty username and password
- Validate error message is displayed

---

### 📊 Part 4: Home Page Widgets
- Verify visibility of all 7 widgets on the Home page after successful login

---

## 🎯 Goals of This Task
- Practice Selenium automation fundamentals
- Apply Page Object Model (POM)
- Avoid hard-coded data as much as possible
- Improve test structuring and readability

---

## 📈 Notes
- Designed for learning and practice purposes
- Can be extended with data-driven testing and reporting
- Suitable for beginner to intermediate automation engineers
