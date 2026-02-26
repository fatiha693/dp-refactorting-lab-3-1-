package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.InputReader;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class Sedan extends Vehicle {

    private boolean hasSunroof;

    // Constructor
    public Sedan() {
        super();
        setHasSunroof();
    }

    // Getters and setters
    public boolean hasSunroof() {
        return hasSunroof;
    }

    public void setHasSunroof() {
        System.out.print("Does the sedan have a sunroof? (true/false): ");
        this.hasSunroof = InputReader.SCANNER.nextBoolean();
        InputReader.SCANNER.nextLine();
    }

    @Override
    public String toString() {
        return "Sedan{" + super.toString() + ", " +
                "hasSunroof=" + hasSunroof() +
                "}";
    }
}
