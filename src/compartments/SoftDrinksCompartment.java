package compartments;

import products.SoftDrink;

public class SoftDrinksCompartment {
    public static final int defaultCapacity = 42;
    private int capacity;
    private SoftDrink[] softDrinks;

    public SoftDrinksCompartment(int capacity) {
        this.capacity = capacity;
        softDrinks = new SoftDrink[capacity];
    }

    public SoftDrinksCompartment() {
        this(defaultCapacity);
    }

    public int countProducts() {
        int count = 0;
        for (SoftDrink softDrink : softDrinks) {
            if (softDrink != null) {
                count++;
            }
        }
        return count;
    }

    public int countProducts(String productName) {
        int count = 0;
        for (SoftDrink softDrink : softDrinks) {
            if (softDrink != null && softDrink.getName().equals(productName)) {
                count++;
            }
        }
        return count;
    }

    public String[] getProductsNames() {
        String[] names = new String[this.countProducts()];
        int index = 0;

        for (SoftDrink softDrink : this.softDrinks) {
            if (softDrink != null) {
                names[index] = softDrink.getName();
                index++;
            }
        }

        return names;
    }

    public void fillCompartment(String productName, int amount) {
        int productsAdded = 0;
        for (int i = 0; i < softDrinks.length; i++) {
            if (softDrinks[i] == null) {
                softDrinks[i] = new SoftDrink(productName);
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

    public SoftDrink[] disposeProducts(String productName, int amount) {
        int matchingProductsAmount = this.countProducts(productName);
        if (amount > matchingProductsAmount) {
            System.out.println("Not enough products.");
            return new SoftDrink[0];
        }

        SoftDrink[] disposingSoftDrinks = new SoftDrink[amount];
        int localPosition = 0;

        for (int i = 0; i < softDrinks.length; i++) {
            if (this.softDrinks[i] != null &&
                    this.softDrinks[i].getName().equals(productName)) {
                disposingSoftDrinks[localPosition] = this.softDrinks[i];
                this.softDrinks[i] = null;
                localPosition++;
            }
            if (localPosition == amount) {
                break;
            }
        }

        return disposingSoftDrinks;
    }

    public SoftDrink[] disposeProducts(String productName) {
        return this.disposeProducts(productName, 1);
    }
}
