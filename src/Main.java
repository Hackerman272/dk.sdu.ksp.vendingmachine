import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static VendingMachine vendingMachine = new VendingMachine(13.37, 22.8);

    private static final Scanner scanner = new Scanner(System.in);


    public static void handleInputChoice(String userChoice) {
        switch (userChoice) {
            case "1":
                vendingMachine.printStocks();
                break;
            case "2":
                String inputCash = scanner.nextLine();
                double insertedCash = Double.parseDouble(inputCash);
                vendingMachine.insertCash(insertedCash);
                break;
            case "3":
                String inputSoftDrinkName = scanner.nextLine();
                String inputSoftDrinkAmountStr = scanner.nextLine();
                int inputSoftDrinkAmount = Integer.parseInt(inputSoftDrinkAmountStr);
                vendingMachine.buySoftDrink(inputSoftDrinkName, inputSoftDrinkAmount);
                break;
            case "4":
                String inputSnackName = scanner.nextLine();
                String inputSnackAmountStr = scanner.nextLine();
                int inputSnackAmount = Integer.parseInt(inputSnackAmountStr);
                vendingMachine.buySnack(inputSnackName, inputSnackAmount);
                break;
            case "5":
                String restockingSoftDrinkName = scanner.nextLine();
                String restockingSoftDrinkAmountStr = scanner.nextLine();
                int restockingSoftDrinkAmount = Integer.parseInt(restockingSoftDrinkAmountStr);
                vendingMachine.restockSoftDrink(restockingSoftDrinkName, restockingSoftDrinkAmount);
                break;
            case "6":
                String restockingSnackName = scanner.nextLine();
                String restockingSnackAmountStr = scanner.nextLine();
                int restockingSnackAmount = Integer.parseInt(restockingSnackAmountStr);
                vendingMachine.restockSnack(restockingSnackName, restockingSnackAmount);
                break;
            case "7":
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