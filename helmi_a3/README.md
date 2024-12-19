# **ePortfolio Invesment Project - Test Plan**

**Student**: Hamza Elmi  
**Student ID**: 1305966  
**Course Code**: CIS\*2430 (Fall 2024) 
**Due Date**: November 29th 2024 
**Assignment**: Assignment Three


## (1) General Problem-

- **What are we trying to solve?**
  - Investors usually need to manage a large portfolio of investments, such as stocks or mutualfunds. Calculating gains, updating prices, keeping track of investments, all while buying and selling can be worrisome and complicated for anyone.
  - Look no further, this program can assist in simplyfing your portfolio by providing you with a tool that automates these tasks, making it easier for investors to maintain and optimize their portfolio.

## (2) Limitations and Assumptions

- **Limitations**

  - The search function is limited to the user utilizing the symbol, keywords in the investment name, and price range.
  - The program doesn't support actual real time market updates
  - As of now, all investments are limited to stocks and mutualfunds

- **Assumptions**
  - The commission fee for the stocks is fixed at $9.99, and the redemption fee for our mutualfund is fixed at 45.00
  - User should have a decent amount of knowledge about investing
  - Assumes all data entered by user is accurate

## (3) User Guide

### Building the Program

1. **Unzip the folder provided**

   Provided is the zipped folder, unziping it from the courselink dropbox

2. **install any dependencies if needed**

   Ensure you have JDK installed

3. **Compile the Program**

javac ePortfolio/*.java

4. **Running the Program**

java ePortfolio.PortfolioGUI

## (4) How Is the Program Tested for Correctness (Test Plan)

## Test Plan - Test Cases

To ensure the correctness of the program, the following testing strategies were employed:

### Correctness

- Core functionality tested to validate that the program works as it should

### Robustness

- The complete workflow of the program was tested to ensure that different components interact correctly.
- Scenarios such as adding new investments, updating prices, calculating gains, and searching for investments were tested.

### Edge Cases

- The program was run multiple times with different inputs to check for consistency and correctness.
- User feedback was collected to improve usability and identify any issues.

### Input Validation

- The program was tested with multiple tests, i.e inputs to validate if the programs functionality was valid

## Stock Testing:

- **Buy Method Functionality**

  - Test Case 1: Buy a new stock.

    - Input: Symbol = "AAPL" , Name = "Apple Inc.", Quantity = 100, Price = 120.00.

    - The expected output: The stocks added with correct price, bookvalue, quantity

    - My program output: ✅ "New Purchase, 100.0 shares of AAPL @120.0 successfuly bought"

  - Test Case 2: Buying an Existing stock

    - Input: Symbol = "AAPL", Quantity = 50, Price = 130.00

    - Expected Output: The stock’s quantity is updated to 150, and the book value is recalculated

    - My program Output: ✅ "Updated Book Value for AAPL is 18519.98. Additional Purchase, 50.0 shares of AAPL @130.0 successfully bought"

- **Sell Method Functionality**

  - Test Case 1: Sell part of the stock

    - Input: Symbol = "AAPL", Quantity = 50, Price = 140.00

    - Expected Output: The stock’s quantity is reduced correctly, and the gain/loss is calculated

    - Program Output: ✅ "Sale completed. Gain/Loss: $<816.48>"

  - Test Case 2: Attempt to sell more than available quantity

    - Input: Symbol = "AAPL", Quantity = 200, Price = 140.00

    - Expected Output: Error message indicating insufficient quantity

    - Program Output: ✅ "Insufficient quantity for sale."

- **Update Method Functionality**

  - Test Case 1: Update the price of a single stock

    - Input: Symbol = "AAPL", New Price = 150.00

    - Expected Output: The stock’s price is updated to 150.00

    - Program Output: ✅ "Stock Price has been updated."

- **Get Gain Method Functionality**

  - Test Case 1: Calculate gain for all investments (Stock example)

    - Input: 500 shares of AAPL at $110.08 per share, quantity = 500, the price will be $110.08, lets say price changes to 142.23.

    - Expected Output: The total gain is calculated correctly : The stock gain will be (500 × 142.23 – 9.99) – 55,049.99 = 71,105.01 – 55,049.99 = $16,055.02

    - Program Output: ✅ "The total sum of our gains for all investments in portfolio: $16,055.02."

  - Test Case 2: Calculate gain when no investments are present

    - Expected Output: The gain should be zero

    - Program Output: ✅ "The total sum of our gains for all investments in portfolio: $0.00."

- **Search Method Functionality**

  - Test Case 1: Search by symbol

    - Input: Symbol = "AAPL"

    - Expected Output: Display all investments with the symbol "AAPL"

    - Program Output: ✅ "Displaying all investments with the symbol AAPL."

  - Test Case 2: Search by keyword in name

    - Input: Keyword = "Apple"

    - Expected Output: Display all investments with "Apple" in the name

    - Program Output: ✅ "Displaying all investments with the keyword Apple."

## MutualFund Testing:

- **Buy Method Functionality**

  - Test Case 1: Buy a new mutual fund

    - Input: Symbol = "SSETX", Name = "BNY Mellon Growth Fund", Quantity = 450, Price = 53.26

    - Expected Output: The mutual fund is added with the correct price, book value, and quantity

    - Program Output: ✅ "New Purchase, 450.0 units of SSETX @53.26 successfully bought"

- Test Case 2: Buy additional units of an existing mutual fund

  - Input: Symbol = "SSETX", Quantity = 100, Price = 55.00

  - Expected Output: The mutual fund’s quantity is updated to 550, and the book value is recalculated

  - Program Output: ✅ "Updated Book Value for SSETX is $29,4367. Additional Purchase, 100.0 units of SSETX @55.0 successfully bought"

- **Sell Method Functionality**

  - Test Case 1: Sell part of the mutual fund

    - Input: Symbol = "SSETX", Quantity = 150, Price = 60.00

    - Expected Output: The mutual fund’s quantity is reduced correctly, and the gain/loss is calculated

    - Program Output: ✅ "Sale completed. Gain/Loss: $1011.0"

  - Test Case 2: Attempt to sell more than available quantity

    - Input: Symbol = "SSETX", Quantity = 500, Price = 60.00

    - Expected Output: Error message indicating insufficient quantity

    - Program Output: ✅ "Insufficient quantity for sale."

- **Update Method Functionality**

  - Test Case 1: Update the price of a single mutual fund

         Input: Symbol = "SSETX", New Price = 65.00

         Expected Output: The mutual fund’s price is updated to 65.00

         Program Output: ✅ "MutualFund Price has been updated."

  - Test Case 2: Update the price of multiple mutual funds

        Input: Symbols = "SSETX, VFINX", New Prices = 65.00, 70.00

        Expected Output: The prices of both mutual funds are updated correctly

        Program Output: ✅ "MutualFund Prices have been updated."

-**Get Gain Method Functionality**

- Test Case 1: Calculate gain for all investments (MutualFund example)

      Input: 450 units of SSETX at $53.26 per unit, quantity = 450, the price will be $53.26, price goes down to 42.21

      Expected Output: The total gain is calculated correctly: (450 × 42.21 – 45.00) – 23,967.00 = 18,949.50 – 23,967.00 = -$5,017.50

      Program Output: ✅ "The total sum of our gains for all investments in portfolio: -$5107.50."

- Test Case 2: Calculate gain when no investments are present

  - Expected Output: The gain should be zero

  - Program Output: ✅ "The total sum of our gains for all investments in portfolio: $0.00."

- **Search Method Functionality**

  - Test Case 1: Search by symbol

  Input: Symbol = "SSETX"

  Expected Output: Display all investments with the symbol "SSETX"

  Program Output: ✅ "Displaying all investments with the symbol SSETX."

  - Test Case 2: Search by keyword in name

  Input: Keyword = "Growth"

  Expected Output: Display all investments with "Growth" in the name

  Program Output: ✅ "Displaying all investments with the keyword Growth."

## Search Method Edge Cases

### Test Case 1: Empty Search
- **Input:**  
  - Symbol: `""`  
  - Keywords: `""`  
  - Price Range: `""`  
- **Expected Output:** Display all stored investments.
- **Program Output:**  
  - **Stock:**  
    - Symbol: `SSTEX`  
    - Name: `Bob Smith`  
    - Quantity: `40`  
    - Price: `$40.00`  
    - Book Value: `$1609.99`  
  - **Mutual Fund:**  
    - Symbol: `AAPL`  
    - Name: `Bob Smith`  
    - Quantity: `30`  
    - Price: `$35.00`  
    - Book Value: `$1050.00`  

### Test Case 2: Search Non-Existent Investment
- **Input:**  
  - Symbol: `"ABCDEF"`  
  - Keywords: `""`  
  - Price Range: `""`  
- **Expected Output:** "No matches found."
- **Program Output:** ✅ "No matches found."

### Test Case 3: Search by Price Range
- **Input:**  
  - Symbol: `""`  
  - Keywords: `""`  
  - Price Range: `"25-75"`  
- **Expected Output:** Display all investments with price between $25 and $75.
- **Program Output:**  
  - **Stock:**  
    - Symbol: `APPL`  
    - Name: `HELLO`  
    - Quantity: `50`  
    - Price: `$60.00`  
    - Book Value: `$2509.99`  
  - **Mutual Fund:**  
    - Symbol: `MICR`  
    - Name: `HELLO`  
    - Quantity: `50`  
    - Price: `$45.00`  
    - Book Value: `$2509.99`  

### Test Case 4: Search by Name (Single Match)
- **Input:**  
  - Symbol: `""`  
  - Keywords: `"HELLO"`  
  - Price Range: `""`  
- **Expected Output:** Display investment containing "HELLO" in the name.
- **Program Output:** ✅  
  - **Stock:**  
    - Symbol: `50`  
    - Name: `HELLO`  
    - Quantity: `50`  
    - Price: `$50.00`  
    - Book Value: `$2509.99`  

### Test Case 5: Search by Name (Multiple Matches)
- **Input:**  
  - Symbol: `""`  
  - Keywords: `"HELLO"`  
  - Price Range: `""`  
- **Expected Output:** Display all investments containing "HELLO" in the name.
- **Program Output:** ✅  
  - **Stock:**  
    - Symbol: `APPL`  
    - Name: `HELLO`  
    - Quantity: `50`  
    - Price: `$50.00`  
    - Book Value: `$2509.99`  
  - **Mutual Fund:**  
    - Symbol: `MICR`  
    - Name: `HELLO`  
    - Quantity: `50`  
    - Price: `$50.00`  
    - Book Value: `$2509.99`  

---

## Get Gain Functionality

### Test Case 16: Total Gain Calculation
- **Input:**  
  - Prompt: `"gain"`  
- **Expected Output:** Display individual gains and total gain.
- **Program Output:** ✅  
  - Gain (Symbol - A): `-$52559.98`  
  - Gain (Symbol - B): `-$52559.98`  
  - Gain (Symbol - C): `-$52559.98`  
  - Gain (Symbol - D): `-$52559.98`  
  - Total Gain: `-$210239.92`  

---

## Filename Parameter Cases

### Test Case 17: No Filename Parameter
- **Input:** Run `java ePortfolio/Portfolio` without parameters.
- **Expected Output:** Display "No file input" and exit.
- **Program Output:** ✅ "No file input."

### Test Case 18: One Filename Parameter
- **Input:** Run `java ePortfolio/Portfolio ePortfolio/file.txt`
- **Expected Output:** Load investments if the file exists; otherwise, use filename for saving.
- **Program Output:** ✅ "Investments loaded successfully."

### Test Case 19: Multiple Filename Parameters
- **Input:** Run `java ePortfolio/Portfolio ePortfolio/file.txt ePortfolio/file2.txt`
- **Expected Output:** "Only one filename allowed."
- **Program Output:** ✅ "Only one filename allowed."


# (5) Possible Improvements

- **Real-Time Data Integration**: Potentially add real-time market data to provide up-to-date information for investments.
- **Advanced Error Handling**: Implement error handling to manage invalid inputs and edge cases more effectively.
- **User Interface**: Develop a graphical user interface (GUI) to enhance the user experience and make the program more user-friendly.
- **Additional Investment Types**: Extend support to include other types of investments, such as bonds and ETFs
- **Performance Optimization**: Optimize the code to handle larger portfolios