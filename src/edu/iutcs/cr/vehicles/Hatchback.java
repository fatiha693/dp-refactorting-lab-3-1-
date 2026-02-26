package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.InputReader;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class Hatchback extends Vehicle {

    private boolean isCompact;

    public Hatchback() {
        super();
        setCompact();
    }

    public boolean isCompact() {
        return isCompact;
    }

    public void setCompact() {
        System.out.print("Is the hatchback compact? (true/false): ");
        this.isCompact = InputReader.SCANNER.nextBoolean();
        InputReader.SCANNER.nextLine();
    }

    @Override
    public String toString() {
        return "Hatchback{" + super.toString() + ", " +
                "isCompact=" + isCompact() +
                "}";
    }
}
