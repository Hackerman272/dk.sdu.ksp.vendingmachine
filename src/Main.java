import products.Snack;
import products.SoftDrink;

import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static VendingMachine vendingMachine = new VendingMachine(13.37, 22.8);

    private static final Scanner scanner = new Scanner(System.in);


    public static void handleInputChoice(String userChoice) {
        switch (userChoice) {
            case "1":
                System.out.println("\n|Revealing stocks|\n");

                vendingMachine.printStocks();
                break;
            case "2":
                System.out.println("\n|Inserting cash|\n");

                System.out.println("Please enter the cash: ");
                String inputCash = scanner.nextLine();
                try {
                    double insertedCash = Double.parseDouble(inputCash);
                    vendingMachine.insertCash(insertedCash);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid cash inserted.");
                }
                break;
            case "3":
                System.out.println("\n|Purchasing soft drink|\n");

                System.out.println("Please enter the product name: ");
                String inputSoftDrinkName = scanner.nextLine();

                System.out.println("Please enter the amount: ");
                String inputSoftDrinkAmountStr = scanner.nextLine();

                int inputSoftDrinkAmount = Integer.parseInt(inputSoftDrinkAmountStr);
                SoftDrink[] purchasedSoftDrinks = vendingMachine.buySoftDrink(inputSoftDrinkName, inputSoftDrinkAmount);

                if (purchasedSoftDrinks.length > 0) {
                    System.out.println("You get: " + purchasedSoftDrinks[0].getName());
                }
                break;
            case "4":
                System.out.println("\n|Purchasing snack|\n");

                System.out.println("Please enter the product name: ");
                String inputSnackName = scanner.nextLine();

                System.out.println("Please enter the amount: ");
                String inputSnackAmountStr = scanner.nextLine();

                int inputSnackAmount = Integer.parseInt(inputSnackAmountStr);
                Snack[] purchasedSnacks = vendingMachine.buySnack(inputSnackName, inputSnackAmount);

                if (purchasedSnacks.length > 0) {
                    System.out.println("You get: " + purchasedSnacks[0].getName());
                }
                break;
            case "5":
                System.out.println("\n|restocking soft drink|\n");

                System.out.println("Please enter the product name: ");
                String restockingSoftDrinkName = scanner.nextLine();

                System.out.println("Please enter the amount: ");
                String restockingSoftDrinkAmountStr = scanner.nextLine();
                int restockingSoftDrinkAmount = Integer.parseInt(restockingSoftDrinkAmountStr);
                vendingMachine.restockSoftDrink(restockingSoftDrinkName, restockingSoftDrinkAmount);
                break;
            case "6":
                System.out.println("\n|restocking snack|\n");

                System.out.println("Please enter the product name: ");
                String restockingSnackName = scanner.nextLine();

                System.out.println("Please enter the amount: ");
                String restockingSnackAmountStr = scanner.nextLine();
                int restockingSnackAmount = Integer.parseInt(restockingSnackAmountStr);
                vendingMachine.restockSnack(restockingSnackName, restockingSnackAmount);
                break;
            case "7":
                System.out.println("\n|revealing balance|\n");

                vendingMachine.revealBalance();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void main(String[] args) {
        while (true) {
            vendingMachine.printInstructions();
            vendingMachine.revealBalance();

            System.out.println("Insert your command:");


            String input = scanner.nextLine();

            handleInputChoice(input);

            System.out.println("\n\n");
        }
    }
}