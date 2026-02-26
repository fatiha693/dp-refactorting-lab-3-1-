package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.InputReader;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Car extends Vehicle {

    int seatingCapacity;

    public Car() {
        super();
        setSeatingCapacity();
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    private void setSeatingCapacity() {
        System.out.print("Enter new seating capacity: ");
        this.seatingCapacity = InputReader.SCANNER.nextInt();
        InputReader.SCANNER.nextLine();
    }

    @Override
    public String toString() {
        return "Car{" + super.toString() + ", " +
                "seatingCapacity=" + getSeatingCapacity() +
                "}";
    }
}
