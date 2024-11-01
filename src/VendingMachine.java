import compartments.CashCompartment;
import compartments.SnacksCompartment;
import compartments.SoftDrinksCompartment;
import products.SoftDrink;
import products.Snack;

import java.util.Arrays;

public class VendingMachine {
    private SoftDrinksCompartment softDrinksCompartment;
    private SnacksCompartment snacksCompartment;
    private CashCompartment cashCompartment;
    private double priceSnack;
    private double priceSoftDrink;

    public VendingMachine(double priceSnack, double priceSoftDrink) {
        this.priceSnack = priceSnack;
        this.priceSoftDrink = priceSoftDrink;
        snacksCompartment = new SnacksCompartment();
        softDrinksCompartment = new SoftDrinksCompartment();
        cashCompartment = new CashCompartment();

        snacksCompartment.fillCompartment("default snack", snacksCompartment.countProducts());

        softDrinksCompartment.fillCompartment("default soda", softDrinksCompartment.countProducts());
    }

    public void insertCash(double credit) {
        if (credit > 0) {
            cashCompartment.increaseBalance(credit);
            System.out.println("Balance increased. New balance: " + cashCompartment.getBalance());
        } else {
            System.out.println("Invalid payment amount");
        }
    }

    public void cancelPurchase() {
        System.out.println("ReleasedCash: " + cashCompartment.releaseBalance());
    }

    public Snack[] buySnack(String productName, int amount) {
        int numberOfAvailableProducts = snacksCompartment.countProducts(productName);
        if (numberOfAvailableProducts == 0) {
            System.out.println("No available products");
            return new Snack[0];
        }

        int numberToDispense = Math.min(numberOfAvailableProducts, amount);

        double totalCost = numberToDispense * priceSnack;

        boolean isPurchaseProcessed = cashCompartment.processPurchase(totalCost);
        if (!isPurchaseProcessed) {
            System.out.println("Not enough cash inserted");
            return new Snack[0];
        } else {
            System.out.println("Product " + productName + " has been dispensed in the amount of " + numberToDispense +
                    " items." +
                    "\nTotal cost: " + totalCost + " DDK." +
                    "\nChange returned: " + cashCompartment.releaseBalance() + " DDK.");
            return snacksCompartment.disposeProducts(productName, numberToDispense);
        }
    }


    public SoftDrink[] buySoftDrink(String productName, int amount) {
        int numberOfAvailableProducts = softDrinksCompartment.countProducts(productName);
        if (numberOfAvailableProducts == 0) {
            System.out.println("No available products");
            return new SoftDrink[0];
        }

        int numberToDispense = Math.min(numberOfAvailableProducts, amount);

        double totalCost = numberToDispense * priceSoftDrink;

        boolean isPurchaseProcessed = cashCompartment.processPurchase(totalCost);
        if (!isPurchaseProcessed) {
            System.out.println("Not enough cash inserted");
            return new SoftDrink[0];
        } else {
            System.out.println("Product " + productName + " has been dispensed in the amount of " + numberToDispense +
                    " items." +
                    "\nTotal cost: " + totalCost + " DDK." +
                    "\nChange returned: " + cashCompartment.releaseBalance() + " DDK.");
            return softDrinksCompartment.disposeProducts(productName, numberToDispense);
        }
    }

    public void restockSnack(String productName, int amount) {
        snacksCompartment.fillCompartment(productName, amount);
    }

    public void restockSoftDrink(String productName, int amount) {
        softDrinksCompartment.fillCompartment(productName, amount);
    }

    public void revealBalance() {
        System.out.println("Present balance: " + cashCompartment.getBalance());
    }

    public void revealWholeCash() {
        System.out.println("Whole cash: " + cashCompartment.getPurchasesTotal());
    }

    public void printInstructions() {
        System.out.println(
                """
                Vending Machine Menu:
                1) Print stock
                2) Insert cash
                3) Purchase soft drink
                4) Purchase snack
                5) Restock soft drink
                6) Restock snack
                7) Print total balance
                """);
    }

    public void printStocks() {
        System.out.println("Stocks of snacks: " + Arrays.toString(snacksCompartment.getProductsNames()) + "\n");
        System.out.println("Stocks of soft drinks: " + Arrays.toString(softDrinksCompartment.getProductsNames()));
    }
}
