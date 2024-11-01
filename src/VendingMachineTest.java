import compartments.CashCompartment;
import compartments.SnacksCompartment;
import compartments.SoftDrinksCompartment;
import products.Snack;
import products.SoftDrink;

public class VendingMachineTest {
    public static void main(String[] args) {
        testVendingMachinePurchases();
        testVendingMachineBalance();
        testVendingMachineCancelPurchase();
        testVendingMachineInvalidInputs();

        testCashCompartment();
        testSnacksCompartment();
        testSoftDrinksCompartment();
    }

    // Original VendingMachine tests...

    private static void testVendingMachinePurchases() {
        VendingMachine vendingMachine = new VendingMachine(10.0, 5.0);

        // Initial setup
        vendingMachine.restockSnack("Chips", 10);
        vendingMachine.restockSoftDrink("Cola", 10);

        // Test purchasing snacks
        vendingMachine.insertCash(30.0); // Insert enough cash
        Snack[] snacks = vendingMachine.buySnack("Chips", 5);
        assert snacks.length == 5 : "Should dispense 5 Chips";

        // Test purchasing soft drinks
        SoftDrink[] drinks = vendingMachine.buySoftDrink("Cola", 3);
        assert drinks.length == 3 : "Should dispense 3 Colas";

        // Test insufficient funds for snack
        vendingMachine.cancelPurchase(); // Reset balance
        vendingMachine.insertCash(5.0); // Insert insufficient cash
        snacks = vendingMachine.buySnack("Chips", 1);
        assert snacks.length == 0 : "Should not dispense snacks due to insufficient funds";

        // Test insufficient funds for soft drink
        vendingMachine.cancelPurchase(); // Reset balance
        vendingMachine.insertCash(3.0); // Insert insufficient cash
        drinks = vendingMachine.buySoftDrink("Cola", 1);
        assert drinks.length == 0 : "Should not dispense soft drinks due to insufficient funds";
    }

    private static void testVendingMachineBalance() {
        VendingMachine vendingMachine = new VendingMachine(10.0, 5.0);

        // Insert cash and check balance
        vendingMachine.insertCash(20.0);
        vendingMachine.revealBalance(); // Output should be "Present balance: 20.0"

        // Insert more cash and check balance
        vendingMachine.insertCash(10.0);
        vendingMachine.revealBalance(); // Output should be "Present balance: 30.0"

        // Cancel purchase and check balance
        vendingMachine.cancelPurchase(); // Should reset the balance
        vendingMachine.revealBalance(); // Output should be "Present balance: 0.0"
    }

    private static void testVendingMachineCancelPurchase() {
        VendingMachine vendingMachine = new VendingMachine(10.0, 5.0);

        // Insert cash and cancel purchase
        vendingMachine.insertCash(25.0);
        vendingMachine.cancelPurchase(); // Should release the entire balance
        vendingMachine.revealBalance(); // Output should be "Present balance: 0.0"
    }

    private static void testVendingMachineInvalidInputs() {
        VendingMachine vendingMachine = new VendingMachine(10.0, 5.0);

        // Test invalid cash insertion
        vendingMachine.insertCash(-10.0); // Invalid input, should not change balance
        vendingMachine.revealBalance(); // Output should be "Present balance: 0.0"

        // Test purchasing when no products available
        Snack[] snacks = vendingMachine.buySnack("NonExistentSnack", 1);
        assert snacks.length == 0 : "Should not dispense non-existent snack";

        SoftDrink[] drinks = vendingMachine.buySoftDrink("NonExistentDrink", 1);
        assert drinks.length == 0 : "Should not dispense non-existent drink";
    }

    // New tests for CashCompartment, SnacksCompartment, and SoftDrinksCompartment

    private static void testCashCompartment() {
        CashCompartment cashCompartment = new CashCompartment();

        // Test balance increase
        cashCompartment.increaseBalance(20.0);
        assert cashCompartment.getBalance() == 20.0 : "Balance should be 20.0";

        // Test process purchase with enough balance
        boolean purchaseProcessed = cashCompartment.processPurchase(10.0);
        assert purchaseProcessed : "Purchase should be processed";
        assert cashCompartment.getBalance() == 10.0 : "Remaining balance should be 10.0";

        // Test process purchase with insufficient balance
        purchaseProcessed = cashCompartment.processPurchase(15.0);
        assert !purchaseProcessed : "Purchase should not be processed";
        assert cashCompartment.getBalance() == 10.0 : "Balance should remain 10.0";

        // Test release balance
        double releasedBalance = cashCompartment.releaseBalance();
        assert releasedBalance == 10.0 : "Released balance should be 10.0";
        assert cashCompartment.getBalance() == 0.0 : "Balance should be 0.0 after release";
    }

    private static void testSnacksCompartment() {
        SnacksCompartment snacksCompartment = new SnacksCompartment();

        // Test stocking snacks
        snacksCompartment.fillCompartment("Chips", 10);
        assert snacksCompartment.countProducts("Chips") == 10 : "Should have 10 Chips in stock";

        // Test adding more snacks
        snacksCompartment.fillCompartment("Chips", 5);
        assert snacksCompartment.countProducts("Chips") == 15 : "Should have 15 Chips after restocking";

        // Test dispensing snacks
        Snack[] dispensedSnacks = snacksCompartment.disposeProducts("Chips", 5);
        assert dispensedSnacks.length == 5 : "Should dispense 5 Chips";
        assert snacksCompartment.countProducts("Chips") == 10 : "Should have 10 Chips left";

        // Test dispensing more than available
        dispensedSnacks = snacksCompartment.disposeProducts("Chips", 20);
        assert dispensedSnacks.length == 10 : "Should dispense only 10 Chips";
        assert snacksCompartment.countProducts("Chips") == 0 : "Should have 0 Chips left";
    }

    private static void testSoftDrinksCompartment() {
        SoftDrinksCompartment softDrinksCompartment = new SoftDrinksCompartment();

        // Test stocking soft drinks
        softDrinksCompartment.fillCompartment("Cola", 10);
        assert softDrinksCompartment.countProducts("Cola") == 10 : "Should have 10 Colas in stock";

        // Test adding more soft drinks
        softDrinksCompartment.fillCompartment("Cola", 5);
        assert softDrinksCompartment.countProducts("Cola") == 15 : "Should have 15 Colas after restocking";

        // Test dispensing soft drinks
        SoftDrink[] dispensedDrinks = softDrinksCompartment.disposeProducts("Cola", 5);
        assert dispensedDrinks.length == 5 : "Should dispense 5 Colas";
        assert softDrinksCompartment.countProducts("Cola") == 10 : "Should have 10 Colas left";

        // Test dispensing more than available
        dispensedDrinks = softDrinksCompartment.disposeProducts("Cola", 20);
        assert dispensedDrinks.length == 10 : "Should dispense only 10 Colas";
        assert softDrinksCompartment.countProducts("Cola") == 0 : "Should have 0 Colas left";
    }
}
