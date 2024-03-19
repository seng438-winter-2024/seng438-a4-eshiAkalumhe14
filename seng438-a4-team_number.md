**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:             |     |
| --------------        | --- |
| Student Names:        |     |
| Eshilama AKalumhe     | 30140722 |
| Emiko Emiko           |     |
| Marco Truong          |     |
|                       |     |

# Introduction1

# Analysis of 10 Mutants of the Range class 

# Report all the statistics and the mutation score for each test class 

# Analysis drawn on the effectiveness of each of the test classes 

# A discussion on the effect of equivalent mutants on mutation score accuracy 

# A discussion of what could have been done to improve the mutation score of the test suites 

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

### Advantages of Mutation Testing

- Mutation Testing can automated hence reducing manual effort from the tester and allowinf for efficient and scalable testing processes.

- Mutation Testing follows a systematic approach by introducing predefined mutations into the source code of the SUT, enesuring thorough test cioverage and fault detection by the test suite.

- Mutation testing calaculates a mutation score which provides a quantitative measure of the effectiveness of the test suite in identifying potential bugs in the code.

- Mutation Testing assesses the adequacy of  a test suite  by revealing areas which the test cases may be lacking or inefficient inlocating bugs within the system.


### Disadvantages of Mutation Testing
 
- Mutation testing can generate equivalent mutants which do not change the behaviour of the program but is still considered by the test suite which may lead to redundant testing.

- Mutation testing can be computationally expensive and complex. Executing tests agaianst numerous mutants, especially in complex programs with substantial amount of lines of code may require a significant amount of resources and time.

- Mutation testing requires manaul effort to detect equivalent mutants despite the use of assistance of automated tools in generating mutants. 

# Explain your SELENUIM test case design process 8
- Requirement Analysis: In this case - Requirement Analysis: In this case, we selected eBay and TSC (The Shopping Channel) websites as the target applications for our automated tests as they share similar functionalities being both online shopping web applications. Hence we were able to identifiy a list of minimum requirements to be tested such as 
[- Sign In]
[- Add to Cart]
[- Browse Item]
[- Search for Item]
[- View Past Orders]
[- Edit Shopping Cart]
[- Edit Account Information]
[- Checkout]

- Test Case Scenario: For each identified requirement, we formulated test scenarios covering various user actions on the websites, such as signing in, browsing items, adding items to the cart, searching for items, editing shopping cart contents, viewing past orders, and updating account information.
We designed these scenarios to encompass both successful and unsuccessful user interactions to ensure comprehensive test coverage.

- Identify Test Data: we determined the necessary test data required to execute the test cases. This includes input data such as login credentials and search queries, expected outcomes and any necessary preconditions. We ensured that the test data we gathered covers both positive and negative scenarios such as checkout with invalid  credit card information to ensure comprehensive test coverage.

- Writing Test Scripts: Using the selenium driver, we wrote different test scripts to capture our test data and scenarios. During this we made sure to record and handle any preconditions for our various test scripts.

# Explain the use of assertions and checkpoints 9

Assertions are statements within test scripts that verify expected conditions and behaviours during the execution of test suites for a SUT. They are used to evaluate if the actaul outcome of a SUT matches the expected outcome of the program.

Checkpoints are validation points within test scripts that capture the state of the application or certain elements at specific points during the execution of tests. They help pinpoint the state of an aaplication at various stages to ensure it aligns with its expected behaviour at those stages.

# how did you test each functionaity with different test data 10
#### Sign In:

- Positive Test: Use valid login credentials for a registered user to sign in successfully.
- Negative Test: Use invalid login credentials (e.g., incorrect username or password) to verify error handling.

#### Add to Cart
- Positive Test: Add a specific product to the cart with valid stock availability.
- Negative Test: Attempt to add a product that is out of stock or unavailable.

#### Browse Item
- Positive Test: Navigate through different categories and select a product from one of the category.
- Reason for Not Using Different Test Data: Unable to identify unavailable item out of large stock and range of items 

### View Past Orders
- Positive Test: View past orders for a user with previous purchase history.
- Reason for Not Using Different Test Data: Insufficient data provided as previous purchase history to test with different test data for the fucntionality of testing past orders

#### Edit Shopping Cart:
- Positive Test: Modify the quantity of items in the cart and verify updates.
Reason for Not Using Different Test Data: Unable to modify the cart past stocvk availability

#### Edit Account Information:
- Positive Test: Update user information (e.g., name, email, password) with valid data.
- Negative Test: Attempt to update account information with invalid or inappropriate data.

#### Checkout:
- Negative Test: Attempt to checkout without providing necessary information or with invalid payment details.
- Reason for Not Using Different Test Data: Unable to provide payment information in the test for security reasons

# Discuss advantages and disadvantages of Selenium vs. Sikulix 11

### Selenium 

| ADVANTAGES | DISADVANTAGES |
|Selenium supports various web browsers, including Chrome, Firefox, Edge, and Safari, making it suitable for testing web applications across different platforms|Selenium is primarily designed for web application testing and may not be suitable for testing desktop or mobile applications without additional tools or frameworks.|
|Selenium integrates seamlessly with popular test frameworks and various continuous integration tools| Selenium may encounter challenges with dynamic web elements that have changing attributes or positions, requiring explicit wait conditions or workarounds to handle|
|Selenium provides rich APIs for interacting with web elements, enabling actions like clicking, typing, selecting, and verifying text or attributes easily.|Selenium tests can be fragile and prone to breakage when UI elements or page structures change, necessitating frequent maintenance and updates.|

### Sikulix

| ADVANTAGES | DISADVANTAGES |
|SikuliX uses image recognition techniques to automate interactions with graphical user interfaces (GUIs), making it versatile for testing web, desktop, and mobile applications.|SikuliX may lack certain web-specific functionalities available in Selenium, such as browser-specific interactions|
|SikuliX simplifies UI testing by allowing testers to define interactions based on visual cues and patterns, eliminating the need for complex locators or element identification.|SikuliX image recognition may have performance implications, especially when dealing with large or complex images, leading to slower test execution times.|
|SikuliX excels at handling dynamic elements or situations where traditional locators may fail, as it relies on visual patterns rather than static attributes.|SikuliX tests can be more prone to maintenance challenges due to changes in application UI or visual elements, requiring updates to reference images or patterns.|

# How the team work/effort was divided and managed


# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
