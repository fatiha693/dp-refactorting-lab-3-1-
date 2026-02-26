package edu.iutcs.cr;

import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class MainMenu {

    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 9;

    private final Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void showMenu() {
        System.out.println("Please enter the type of vehicle [1-9]: ");
        System.out.println("1. Add new seller");
        System.out.println("2. Add new customer");
        System.out.println("3. Add car");
        System.out.println("4. View inventory");
        System.out.println("5. View seller list");
        System.out.println("6. View buyer list");

        System.out.println();
        System.out.println("7. Add new order");
        System.out.println("8. View all invoices");

        System.out.println();
        System.out.println("9. Save System and Exit");
    }

    public int showAndSelectOperation() {
        showMenu();

        int selectedOperation = -1;

        while (selectedOperation < MIN_OPTION || selectedOperation > MAX_OPTION) {
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            try {
                selectedOperation = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                selectedOperation = -1;
            }

            if (selectedOperation < MIN_OPTION || selectedOperation > MAX_OPTION) {
                System.out.println("Enter a valid operation (" + MIN_OPTION + "-" + MAX_OPTION + ").");
            }
        }

        return selectedOperation;
    }
}