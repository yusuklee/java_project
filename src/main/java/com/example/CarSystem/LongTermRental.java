package com.example.CarSystem;

public class LongTermRental extends RentalOrder{
    public LongTermRental(Car car, Customer customer, int rentalDays) {
        super(car, customer, rentalDays);
    }

    @Override
    public double calculateTotalPrice() {
        double basePrice = car.getDailyRate() * rentalDays;
        double discounted = (rentalDays > 7) ? basePrice * 0.85 : basePrice; //7일보다 더 대여시 15% 할인
        if (customer.isVip()) {
            discounted *= 0.9; //추가로 vip 로 더할인
        }
        return discounted;
    }
}
