package edu.iutcs.cr.vehicles;

import edu.iutcs.cr.InputReader;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Vehicle implements Serializable {

    private String make;
    private String model;
    private String year;
    private double price;
    private boolean available;
    private String registrationNumber;

    public Vehicle() {
        setRegistrationNumber();
        setMake();
        setModel();
        setYear();
        setPrice();
        this.available = true;
    }

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber() {
        while (this.registrationNumber == null || registrationNumber.isBlank()) {
            System.out.print("Enter registration number: ");
            this.registrationNumber = InputReader.SCANNER.nextLine();

            if (registrationNumber == null || registrationNumber.isBlank()) {
                System.out.println("Registration number is mandatory!");
            }
        }
    }

    public String getMake() {
        return make;
    }

    public void setMake() {
        while (this.make == null || this.make.isBlank()) {
            System.out.print("Enter make: ");
            this.make = InputReader.SCANNER.nextLine();

            if (make == null || make.isBlank()) {
                System.out.println("Make is mandatory!");
            }
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel() {
        while (this.model == null || this.model.isBlank()) {
            System.out.print("Enter model: ");
            this.model = InputReader.SCANNER.nextLine();

            if (model == null || model.isBlank()) {
                System.out.println("Model is mandatory!");
            }
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear() {
        while (this.year == null || this.year.isBlank()) {
            System.out.print("Enter year: ");
            this.year = InputReader.SCANNER.nextLine();

            if (year == null || year.isBlank()) {
                System.out.println("Year is mandatory!");
            }
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        System.out.print("Enter price: ");
        this.price = InputReader.SCANNER.nextDouble();
        InputReader.SCANNER.nextLine();
    }

    public boolean isAvailable() {
        return available;
    }

    public void setUnavailable() {
        this.available = false;
    }

    @Override
    public String toString() {
        return "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", registrationNumber='" + registrationNumber + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(this.registrationNumber, vehicle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
