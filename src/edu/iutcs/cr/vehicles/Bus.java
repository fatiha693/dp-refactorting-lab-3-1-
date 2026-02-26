package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.InputReader;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Bus extends Vehicle {

    int passengerCapacity;

    public Bus() {
        super();
        setPassengerCapacity();
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    private void setPassengerCapacity() {
        System.out.print("Enter new passenger capacity: ");
        this.passengerCapacity = InputReader.SCANNER.nextInt();
        InputReader.SCANNER.nextLine();
    }

    @Override
    public String toString() {
        return "Bus{" + super.toString() + ", " +
                "passengerCapacity=" + getPassengerCapacity() +
                "}";
    }
}
