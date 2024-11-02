# Vending Machine Program Documentation

Java-based program for the high-level imitation of work of basic vending machine with snacks and soft drinks.

---

## I. Solution Overview

The program consists of:

1. **`VendingMachine`**: Central controller, for example for: purchases, restocking, and balance operations.
2. **Compartment Classes**:
- `SoftDrinksCompartment`, `SnacksCompartment`, `CashCompartment` manage specific products or cash.
3. **Product Classes**:
- `SoftDrink` and `Snack` represent individual products with basic attributes.
4. **`Main` Class**: Manages user input, commands, and calls methods of the `VendingMachine`.

---

## II. Class Diagram Description

The UML diagram shows:

- **Composition**: `VendingMachine` owns `SoftDrinksCompartment`, `SnacksCompartment`, and `CashCompartment`. Compartments (my assumption) could not exist separately in the normal way.
- **Associations**: Product classes (`SoftDrink`, `Snack`) are stored in the relevant compartments, forming logical associations.

The design encapsulates each compartment within `VendingMachine`.
Based on the access modifiers and structure of the program it is impossible to change some attributes of the used classes directly.
Mostly public modifier of the methods gives a possibility to use them from not only their package, what is necessary for the program flexibility and compliance with the project structure requirements.

---

## III. Requirement Compliance

### 1. Balance and Change Release

- **Implementation**: `buySnack` and `buySoftDrink` in `VendingMachine` release remaining balance post-purchase using `cashCompartment.releaseBalance()`.
- **Compliance**: Matches requirement to return balance after each transaction.

### 2. Capacity Limits

- **Implementation**: `fillCompartment` in `SoftDrinksCompartment` and `SnacksCompartment` shows additions at capacity, printing feedback if limits exceed.
- **Compliance**: Prevents overstock, respecting compartment size.

### 3. Insufficient Funds

- **Implementation**: `processPurchase` in `CashCompartment` checks balance; if insufficient, it returns `false`, and `VendingMachine` aborts purchase with an empty array.
- **Compliance**: Meets requirement to verify funds before completing a purchase.

### 4. Transaction Feedback

- **Implementation**: `buySnack` and `buySoftDrink` output transaction details with product name, quantity, and change.
- **Compliance**: Provides necessary user feedback post-purchase.

---

## IV. Class-by-Class Summary

### `VendingMachine`
- **Role**: Manages operations (purchases, restocking, balance).
- **Methods**:
- `buySnack(String productName, int amount)`: Handles snack purchase by checking stock and balance, updating balance, and returning the purchased items.
- `buySoftDrink(String productName, int amount)`: Similar to `buySnack`, but for soft drinks.
- `insertCash(double credit)`: Increases balance if the amount is valid.
- `revealBalance()`: Displays current balance.
- `printInstructions()`: Prints available commands for the user.
- `printStocks()`: Shows current snack and drink inventory in compartments.

### `SoftDrinksCompartment` and `SnacksCompartment`
- **Role**: Store items and manage inventory for drinks and snacks, respectively.
- **Methods**:
- `countProducts()`: Returns the count of non-null items in the compartment.
- `countProducts(String productName)`: Counts specific items by name.
- `fillCompartment(String productName, int amount)`: Adds items to available slots up to capacity.
- `disposeProducts(String productName, int amount)`: Removes and returns the specified number of items.
- `getProductsNames()`: Returns an array of product names currently in the compartment.

### `CashCompartment`
- **Role**: Manages cash operations and tracks balance.
- **Methods**:
- `getBalance()`: Returns current balance.
- `increaseBalance(double paymentAmount)`: Adds a specified amount to the balance if valid.
- `releaseBalance()`: Resets balance and returns the total to release.
- `processPurchase(double totalCost)`: Deducts the specified amount if balance is sufficient; otherwise, returns `false`.
- `getPurchasesTotal()`: Returns the sum of all purchases made.

### `SoftDrink` and `Snack`
- **Role**: Represent individual products with basic attributes.
- **Methods**:
- `getName()`: Returns the name of the product.

### `Main`
- **Role**: Controls user interaction and directs commands to `VendingMachine`.
- **Methods**:
- `handleInputChoice(String userChoice)`: Directs input to the appropriate `VendingMachine` method based on the command.
- `main(String[] args)`: Main execution loop for handling user inputs and actions.

---

## V. Conclusion

The vending machine program satisfies core requirements, covering compartmentalization, balance management, inventory control, and transaction feedback.
The UML diagram and composition relationships align with assignment specifications, providing a structured, modular solution.