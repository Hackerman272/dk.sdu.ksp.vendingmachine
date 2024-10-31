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
    }

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
}