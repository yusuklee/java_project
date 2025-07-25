package com.example.CarSystem;

public class Car {
    private String brand;
    private String model;
    private int year;
    private double dailyRate;       
    private boolean available;      

   
    public Car(String brand, String model, int year, double dailyRate) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.available = true;
    }


     public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
    
    public double getDailyRate() {
        return dailyRate;
    }

    
    public double getRentalPrice(int days) {
        return dailyRate * days;
    }

   
    public boolean isAvailable() {
        return available;
    }


    public void setAvailability(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s (%d) - $%.2f/day-%s",  
            brand, model, year, dailyRate,
            available ? "Available" : "Not Available"
        );
    }
}
