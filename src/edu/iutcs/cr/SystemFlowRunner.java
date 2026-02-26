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

        Scanner scanner = new Scanner(System.in);
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
            case 1 -> addSeller(database, scanner);
            case 2 -> addBuyer(database, scanner);
            case 3 -> addCar(database, scanner);
            case 4 -> showInventory(database);
            case 5 -> showSellerList(database);
            case 6 -> showBuyerList(database);
            case 7 -> createOrder(database, scanner);
            case 8 -> showInvoices(database);
            default -> System.out.println("Invalid operation.");
        }

        if (op != 7 && op >= 1 && op <= 8) {
            promptToViewMainMenu(scanner);
        }
    }

    private static void addSeller(SystemDatabase database, Scanner scanner) {
        System.out.println("\n\n\nAdd new seller");

        // if you refactored Person to remove Scanner, you must collect these here
        String id = readNonBlank(scanner, "Enter seller id: ");
        String name = readNonBlank(scanner, "Enter seller name: ");
        String email = readNonBlank(scanner, "Enter seller email: ");

        Seller seller = new Seller(id);

        // these setters exist only if you refactored Person to accept parameters
        // if your Person class still uses old interactive setters, you can remove these 2 lines
        seller.setName(name);
        seller.setEmail(email);

        database.getSellers().add(seller);
        System.out.println("Seller added.");
    }

    private static void addBuyer(SystemDatabase database, Scanner scanner) {
        System.out.println("\n\n\nAdd new customer");

        // collect buyer details here (UI layer), not inside Buyer class
        String id = readNonBlank(scanner, "Enter buyer id: ");
        String name = readNonBlank(scanner, "Enter buyer name: ");
        String email = readNonBlank(scanner, "Enter buyer email: ");
        String paymentMethod = readNonBlank(scanner, "Enter payment method: ");

        Buyer buyer = new Buyer(id);

        // these setters exist only if you refactored Person to accept parameters
        // if your Person class still uses old interactive setters, you can remove these 2 lines
        buyer.setName(name);
        buyer.setEmail(email);

        // this is the missing line you said you can't find anywhere
        buyer.setPaymentMethod(paymentMethod);

        database.getBuyers().add(buyer);
        System.out.println("Buyer added.");
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
        System.out.println("Vehicle added.");
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
                    return;
                }
                default -> {
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
            String buyerId = readNonBlank(scanner, "Enter buyer id: ");
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
            String sellerId = readNonBlank(scanner, "Enter seller id: ");
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

    private static String readNonBlank(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("This field is mandatory. Try again.");
        }
    }
}