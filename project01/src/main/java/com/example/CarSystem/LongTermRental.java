package com.example.CarSystem;

public class LongTermRental extends RentalOrder{
    public LongTermRental(Car car, Customer customer, int rentalDays) {
        super(car, customer, rentalDays);
    }

    @Override
    public double calculateTotalPrice() {
        double basePrice = car.getDailyRate() * rentalDays;
        double discounted = (rentalDays > 7) ? basePrice * 0.85 : basePrice;
        if (customer.isVip()) {
            discounted *= 0.9;
        }
        return discounted;
    }
}
