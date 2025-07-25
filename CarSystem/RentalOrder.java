package com.example.CarSystem;


public abstract class RentalOrder {
    protected Car car;
    protected Customer customer;
    protected int rentalDays;

    public RentalOrder(Car car, Customer customer, int rentalDays) {
        this.car = car;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    
    public abstract double calculateTotalPrice();

    @Override
    public String toString() {
        return String.format(
            "Rental for: %s\n" +
            "Car: %s\n" +
            "Rental Days: %d\n" +
            "Total Price: $%.2f",
            customer.toString(),
            car.toString(),
            rentalDays,
            calculateTotalPrice()
        );
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}
