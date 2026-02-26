package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.InputReader;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SUV extends Vehicle {

    private boolean isOffRoad;

    // Constructor
    public SUV() {
        super();
        setOffRoad();
    }

    // Getters and setters
    public boolean isOffRoad() {
        return isOffRoad;
    }

    public void setOffRoad() {
        System.out.print("Is the SUV for off-road use? (true/false): ");
        this.isOffRoad = InputReader.SCANNER.nextBoolean();
        InputReader.SCANNER.nextLine();
    }

    @Override
    public String toString() {
        return "SUV{" + super.toString() + ", " +
                "isOffRoad=" + isOffRoad() +
                "}";
    }
}
