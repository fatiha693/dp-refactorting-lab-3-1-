package edu.iutcs.cr.persons;

import java.io.Serializable;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Buyer extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String paymentMethod;

    public Buyer() {
        super();
        // No console input here.
        // paymentMethod can be set later via setter, or you can remove this constructor if paymentMethod is required.
    }

    public Buyer(String id) {
        super(id);
    }

    public Buyer(String id, String paymentMethod) {
        super(id);
        this.paymentMethod = validatePaymentMethod(paymentMethod);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = validatePaymentMethod(paymentMethod);
    }

    private String validatePaymentMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("paymentMethod cannot be null or blank");
        }
        return paymentMethod.trim();
    }

    @Override
    public String toString() {
        return super.toString() + ", paymentMethod='" + paymentMethod + '\'';
    }
}