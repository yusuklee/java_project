package com.example.CarSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.DataFormatException;
public class CSVHandler implements FileHandler<CarRentalSystem> {
    private CarRentalSystem system;

    public CSVHandler(CarRentalSystem system) {
        this.system = system;
    }

    @Override
public boolean saveData(String filename) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Car car : system.getCars()) {
            writer.write(String.format("CAR   %s   %s   %d   %.2f   %b\n",
                car.getBrand(), car.getModel(), car.getYear(), car.getDailyRate(), car.isAvailable()));
        }

        for (Customer customer : system.getCustomers()) {
            writer.write(String.format("CUSTOMER   %s   %b\n",
                customer.getName(), customer.isVip()));
        }

        for (RentalOrder order : system.getOrders()) {
            Customer customer = order.getCustomer();  
            Car car = order.getCar();
            writer.write(String.format("ORDER   %s   %b   %s   %s   %d   %.2f   %b   %d   %.2f\n",
                customer.getName(),
                customer.isVip(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getDailyRate(),
                car.isAvailable(),
                order.getRentalDays(),
                order.calculateTotalPrice()
            ));
        }
        writer.flush();
        return true;
    }
}


    @Override
public CarRentalSystem loadData(String filename) throws IOException, DataFormatException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.trim().split("\\s+");  // 공백 구분

            if (tokens[0].equals("CAR")) {
                if (tokens.length != 6)
                    throw new DataFormatException("Invalid CAR format: " + line);
                String brand = tokens[1];
                String model = tokens[2];
                int year = Integer.parseInt(tokens[3]);
                double rate = Double.parseDouble(tokens[4]);
                boolean available = Boolean.parseBoolean(tokens[5]);

                Car car = new Car(brand, model, year, rate);
                car.setAvailability(available);
                system.addCar(car);

            } else if (tokens[0].equals("CUSTOMER")) {
                if (tokens.length != 3)
                    throw new DataFormatException("Invalid CUSTOMER format: " + line);
                String name = tokens[1];
                boolean isVip = Boolean.parseBoolean(tokens[2]);
                if (!system.customerExists(name)) {
                    system.addCustomer(new Customer(name, isVip));
                }

            } else if (tokens[0].equals("ORDER")) {
                if (tokens.length != 10)
                    throw new DataFormatException("Invalid ORDER format: " + line);
                String custName = tokens[1];
                boolean isVip = Boolean.parseBoolean(tokens[2]);
                String brand = tokens[3];
                String model = tokens[4];
                int year = Integer.parseInt(tokens[5]);
                double rate = Double.parseDouble(tokens[6]);
                boolean available = Boolean.parseBoolean(tokens[7]);
                int days = Integer.parseInt(tokens[8]);
                double total = Double.parseDouble(tokens[9]);

                // 고객은 이미 CUSTOMER 줄에서 추가되었어야 하지만 혹시 없으면 추가
                if (!system.customerExists(custName)) {
                    system.addCustomer(new Customer(custName, isVip));
                }

                Car car = null;
                for (Car c : system.getCars()) {
                    if (c.getBrand().equals(brand) && c.getModel().equals(model) && c.getYear() == year) {
                        car = c;
                        break;
                    }
                }

                if (car != null) {
                    RentalOrder order = (days > 7) ?
                        new LongTermRental(car, system.findCustomer(custName), days) :
                        new ShortTermRental(car, system.findCustomer(custName), days);
                    system.getOrders().add(order);
                    car.setAvailability(available);
                } else {
                    System.out.println("Car not found for order: " + line);
                }
            }
        }
    }
    return system;
}


}
