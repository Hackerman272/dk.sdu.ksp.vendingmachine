package compartments;

import java.util.ArrayList;

public class CashCompartment {
    double balance;
    ArrayList<Double> purchases;

    public CashCompartment() {}

    public double getBalance() {
        return balance;
    }

    public void increaseBalance(double paymentAmount) {
        if (paymentAmount >= 0) {
            balance += paymentAmount;
        } else {
            System.out.println("Invalid payment amount");
        }
    }

    public double getPurchasesTotal() {
        double total = 0;
        for (Double purchase : purchases) {
            total += purchase;
        }
        return total;
    }

    public double releaseBalance() {
        double balance_to_release = getBalance();
        balance = 0.0;
        return balance_to_release;
    }

    public boolean processPurchase(double totalCost) {
        if (balance >= totalCost) {
            balance -= totalCost;
            purchases.add(totalCost);
            return true;
        } else {
            return false;
        }
    }
}
