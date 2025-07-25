package com.example.CarSystem;

import java.util.ArrayList;
import java.util.List;

public class RentalProcess extends Thread {
    private RentalOrder order;
    private List<RentalObserver> observers = new ArrayList<>();
    private CarRentalSystem carRentalSystem;

    // 동시 렌탈 3개 제한용 세마포어
    private static final java.util.concurrent.Semaphore rentalSemaphore = new java.util.concurrent.Semaphore(3);

    public RentalProcess(RentalOrder order) {
        this.order = order;
    }
    public RentalProcess(RentalOrder order, CarRentalSystem carRentalSystem) {
    this.order = order;
    this.carRentalSystem = carRentalSystem;
    }

    public void addObserver(RentalObserver obs) {
        observers.add(obs);
    }

    private void notifyObservers() {
        for (RentalObserver obs : observers) {
            obs.update(order);
        }
    }

    @Override
    public void run() {
        try {
            // 최대 3개만 실행
            rentalSemaphore.acquire();
            Customer customer = order.getCustomer();
            Car car = order.getCar();
            String brand = car.getBrand();
            String model = car.getModel();
            int days = order.getRentalDays();
            for (int i = 1; i <= days; i++) {
                System.out.println("order in progress: " + brand + " " + model + " - day " + i + "/" + days);
                customer.addPoints(1);
                Thread.sleep(3000); 
            }

            // 자동차 반납 처리 
            synchronized (order.getCar()) {
                order.getCar().setAvailability(true);
            }

            notifyObservers(); // 렌탈 완료 알림
            carRentalSystem.assignCarToNextCustomer(order.getCar());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rentalSemaphore.release(); 
        }
    }
}

