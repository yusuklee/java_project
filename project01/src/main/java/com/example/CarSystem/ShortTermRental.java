package com.example.CarSystem;

public class ShortTermRental extends RentalOrder{
    public ShortTermRental(Car car, Customer customer, int rentalDays) {
        super(car, customer, rentalDays);
    }

    
    @Override
    public double calculateTotalPrice() {
        double basePrice = car.getDailyRate() * rentalDays;
        if (customer.isVip()) {
            return basePrice * 0.9;
        }
        return basePrice;
    }
}
