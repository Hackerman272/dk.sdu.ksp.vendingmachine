package compartments;

import products.Snack;
import products.SoftDrink;

public class SnacksCompartment {
    public static final int defaultCapacity = 42;
    private int capacity;
    private Snack[] snacks;

    public SnacksCompartment(int capacity) {
        this.capacity = capacity;
        snacks = new Snack[capacity];
    }

    public SnacksCompartment() {
        this(defaultCapacity);
    }

    public int countProducts() {
        int count = 0;
        for (Snack snack : snacks) {
            if (snack != null) {
                count++;
            }
        }
        return count;
    }

    public int countProducts(String productName) {
        int count = 0;
        for (Snack snack : snacks) {
            if (snack != null && snack.getName().equals(productName)) {
                count++;
            }
        }
        return count;
    }

    public String[] getProductsNames() {
        String[] names = new String[this.countProducts()];
        int index = 0;

        for (Snack snack : this.snacks) {
            if (snack != null) {
                names[index] = snack.getName();
                index++;
            }
        }

        return names;
    }

    public void fillCompartment(String productName, int amount) {
        int productsAdded = 0;
        for (int i = 0; i < snacks.length; i++) {
            if (snacks[i] == null) {
                snacks[i] = new Snack(productName);
                productsAdded++;
            }
            if (productsAdded == amount) {
                break;
            }
        }

        if (productsAdded < amount) {
            System.out.println("Added: " + productsAdded + "\nNot enough slots for: " + (amount - productsAdded));
        }
    }

    public Snack[] disposeProducts(String productName, int amount) {
        int matchingProductsAmount = this.countProducts(productName);
        if (amount > matchingProductsAmount) {
            System.out.println("Not enough products.");
            return new Snack[0];
        }

        Snack[] disposingSnacks = new Snack[amount];
        int localPosition = 0;

        for (int i = 0; i < this.countProducts(); i++) {
            if (this.snacks[i] != null &&
                    this.snacks[i].getName().equals(productName)) {
                disposingSnacks[localPosition] = this.snacks[i];
                this.snacks[i] = null;
                localPosition++;
            }
            if (localPosition == amount) {
                break;
            }
        }

        return disposingSnacks;
    }

    public Snack[] disposeProducts(String productName) {
        return this.disposeProducts(productName, 1);
    }
}
