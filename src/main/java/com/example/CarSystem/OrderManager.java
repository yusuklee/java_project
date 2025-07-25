package com.example.CarSystem;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements RentalObserver {
    private List<String> logs;
    private int completedOrders;

    public OrderManager() {
        logs = new ArrayList<>();
        completedOrders = 0;
    }

    @Override
    public void update(RentalOrder order) {
        String log = "Order completed: Customer " + order.getCustomer().getName() +
                     " has returned car " + order.getCar().getBrand() + " " + order.getCar().getModel() +
                     "\nCar " + order.getCar().getBrand() + " " + order.getCar().getModel() + " is now available";
        System.out.println(log);
        logs.add(log);
        completedOrders++;
    }

    public void printAllLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public int getCompletedOrders() {
        return completedOrders;
    }
}

