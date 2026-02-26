package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.*;
import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SystemFlowRunner {

    public static void run() {
        System.out.println("Welcome to Car Hut");

        System.out.println("Loading existing system");
        SystemDatabase database = SystemDatabase.getInstance();
        System.out.println("Existing system loaded");

        Scanner scanner = InputReader.SCANNER;
        MainMenu mainMenu = new MainMenu(scanner);

        while (true) {
            System.out.println("\n\n\n");

            int selectedOperation = mainMenu.showAndSelectOperation();

            if (selectedOperation == 9) {
                database.saveSystem();
                return;
            }

            handleMainMenuOperation(selectedOperation, database, scanner);
        }
    }

    private static void handleMainMenuOperation(int op, SystemDatabase database, Scanner scanner) {
        switch (op) {
            case 1 -> addSeller(database);
            case 2 -> addBuyer(database);
            case 3 -> addCar(database, scanner);
            case 4 -> showInventory(database);
            case 5 -> showSellerList(database);
            case 6 -> showBuyerList(database);
            case 7 -> createOrder(database, scanner);
            case 8 -> showInvoices(database);
            default -> System.out.println("Invalid operation.");
        }

        // in original flow, most operations go back to main menu after "press 0"
        if (op != 7 && op != 9 && op >= 1 && op <= 8) {
            promptToViewMainMenu(scanner);
        }
    }

    private static void addSeller(SystemDatabase database) {
        System.out.println("\n\n\nAdd new seller");
        database.getSellers().add(new Seller());
    }

    private static void addBuyer(SystemDatabase database) {
        System.out.println("\n\n\nAdd new customer");
        database.getBuyers().add(new Buyer());
    }

    private static void showInventory(SystemDatabase database) {
        System.out.println("\n\n\nInventory list");
        database.showInventory();
    }

    private static void showSellerList(SystemDatabase database) {
        System.out.println("\n\n\nSeller's list");
        database.showSellerList();
    }

    private static void showBuyerList(SystemDatabase database) {
        System.out.println("\n\n\nCustomer's list");
        database.showBuyerList();
    }

    private static void showInvoices(SystemDatabase database) {
        System.out.println("\n\n\nInvoice list");
        database.showInvoices();
    }

    private static void promptToViewMainMenu(Scanner scanner) {
        System.out.print("\n\nEnter 0 to view main menu: ");
        int val;

        do {
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.print("Enter 0 to view main menu: ");
            }
            val = scanner.nextInt();
            scanner.nextLine(); // consume newline
        } while (val != 0);
    }

    private static void addCar(SystemDatabase database, Scanner scanner) {
        System.out.println("\n\n\nAdd new vehicle");

        System.out.println("Please enter the type of vehicle [1-5]: ");
        System.out.println("1. Bus");
        System.out.println("2. Car");
        System.out.println("3. Hatchback");
        System.out.println("4. Sedan");
        System.out.println("5. SUV");

        int vehicleType = readIntInRange(scanner, "Enter your choice: ", 1, 5);

        Vehicle newItem = createVehicleByType(vehicleType);

        database.getVehicles().add(newItem);
    }

    private static Vehicle createVehicleByType(int vehicleType) {
        return switch (vehicleType) {
            case 1 -> {
                System.out.println("\n\nCreate new bus");
                yield new Bus();
            }
            case 2 -> {
                System.out.println("\n\nCreate new car");
                yield new Car();
            }
            case 3 -> {
                System.out.println("\n\nCreate new hatchback");
                yield new Hatchback();
            }
            case 4 -> {
                System.out.println("\n\nCreate new sedan");
                yield new Sedan();
            }
            default -> {
                System.out.println("\n\nCreate new SUV");
                yield new SUV();
            }
        };
    }

    private static void createOrder(SystemDatabase systemDatabase, Scanner scanner) {
        System.out.println("\n\n\nCreate order");

        ShoppingCart cart = new ShoppingCart();

        while (true) {
            printOrderMenu();

            int selectedOperation = readIntInRange(scanner, "Enter your choice: ", 1, 5);

            switch (selectedOperation) {
                case 1 -> cart.addItem();
                case 2 -> cart.removeItem();
                case 3 -> cart.viewCart();
                case 4 -> {
                    createInvoice(systemDatabase, scanner, cart);
                    promptToViewMainMenu(scanner);
                    return;
                }
                case 5 -> {
                    // return to main menu
                    return;
                }
                default -> {
                    // unreachable because readIntInRange enforces range
                }
            }
        }
    }

    private static void printOrderMenu() {
        System.out.println("Please enter the type of operation: [1-5]");
        System.out.println("1. Add new vehicle to cart");
        System.out.println("2. Remove vehicle from cart");
        System.out.println("3. View cart");
        System.out.println("4. Confirm purchase");
        System.out.println();
        System.out.println("5. Return to main menu");
    }

    private static void createInvoice(SystemDatabase database, Scanner scanner, ShoppingCart cart) {
        Buyer buyer = readExistingBuyer(database, scanner);
        Seller seller = readExistingSeller(database, scanner);

        Invoice invoice = new Invoice(buyer, seller, cart);
        invoice.printInvoice();
        database.getInvoices().add(invoice);
    }

    private static Buyer readExistingBuyer(SystemDatabase database, Scanner scanner) {
        Buyer buyer = null;
        while (buyer == null) {
            System.out.print("Enter buyer id: ");
            String buyerId = scanner.nextLine();
            buyer = database.findBuyerById(buyerId);

            if (buyer == null) {
                System.out.println("Buyer not found. Try again!");
            }
        }
        return buyer;
    }

    private static Seller readExistingSeller(SystemDatabase database, Scanner scanner) {
        Seller seller = null;
        while (seller == null) {
            System.out.print("Enter seller id: ");
            String sellerId = scanner.nextLine();
            seller = database.findSellerById(sellerId);

            if (seller == null) {
                System.out.println("Seller not found. Try again!");
            }
        }
        return seller;
    }

    private static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Please enter a number.");
                System.out.print(prompt);
            }

            int value = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (value < min || value > max) {
                System.out.println("Enter a valid option (" + min + "-" + max + ").");
                continue;
            }

            return value;
        }
    }
}