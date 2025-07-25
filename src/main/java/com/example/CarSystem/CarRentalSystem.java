
package com.example.CarSystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.zip.DataFormatException;

public class CarRentalSystem {
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<RentalOrder> orders = new ArrayList<>();
    private OrderManager orderManager = new OrderManager();
    private HashMap<Car, Queue<WaitingCustomer>> waitingList = new HashMap<>();

    public CarRentalSystem() {
        // 초기화는 이미 ArrayList로 됨
    }

    // Car 관리
    public void addCar(Car car) {
        cars.add(car);
        System.out.println("Car added successfully!\n");
    }

    public void listAvailableCars() {
        System.out.println("Available Cars:");
        boolean any = false;
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).isAvailable()) {
                System.out.println(i + ". " + cars.get(i).toString());
                any = true;
            }
        }
        if (!any) System.out.println("No cars available.");
    }

    public boolean hasAvailableCars() {
        for (Car car : cars) {
            if (car.isAvailable()) return true;
        }
        return false;
    }

    public void addCustomer(Customer customer) {
        if (customerExists(customer.getName())) {
            System.out.println("Customer already exists.");
        } else {
            customers.add(customer);
            System.out.println("Customer registered: " + customer.getName());
        }
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        System.out.println("Registered Customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("[%d] %s%n", i, customers.get(i).toString());
        }
    }

    public boolean customerExists(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Customer findCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    // 대여 처리
    public void rentCar(String customerName, int carIndex, int rentalDays) {
        if (!customerExists(customerName)) {
            System.out.println("Customer not found. Please register first.");
            return;
        }
        if (carIndex < 0 || carIndex >= cars.size()) {
            System.out.println("Invalid car selection.");
            return;
        }
        Car selected = cars.get(carIndex);
        if (!selected.isAvailable()) {
            System.out.println("Selected car is not available.");
            return;
        }
        Customer cust = findCustomer(customerName);
        RentalOrder order = (rentalDays > 7) ?
                new LongTermRental(selected, cust, rentalDays) :
                new ShortTermRental(selected, cust, rentalDays);

        orders.add(order);
        selected.setAvailability(false);

        System.out.println("Rental Success!");
        System.out.println(order);
        RentalProcess process = new RentalProcess(order,this);
        process.addObserver(orderManager);
        process.start();
    }


    public synchronized void addCustomerToQueue(Car car, Customer customer, int rentalDays) {
    Queue<WaitingCustomer> queue = waitingList.getOrDefault(car, new LinkedList<>());
    queue.offer(new WaitingCustomer(customer, rentalDays));
    waitingList.put(car, queue);
    System.out.println("Customer " + customer.getName() + " added to waiting list for " + car.getBrand() + " " + car.getModel());
    }
    

    public synchronized WaitingCustomer getNextWaitingCustomer(Car car) {
    Queue<WaitingCustomer> queue = waitingList.get(car);
    if (queue != null && !queue.isEmpty()) {
        return queue.poll();
    }
    return null;
    }


    public synchronized void assignCarToNextCustomer(Car car) {
    WaitingCustomer next = getNextWaitingCustomer(car);
    if (next != null) {
        RentalOrder order = (next.getRentalDays() > 7) ?
                new LongTermRental(car, next.getCustomer(), next.getRentalDays()) :
                new ShortTermRental(car, next.getCustomer(), next.getRentalDays());
        orders.add(order);
        car.setAvailability(false);
        System.out.println("Assigned car to waiting customer: " + next.getCustomer().getName());
        RentalProcess process = new RentalProcess(order, this);
        process.addObserver(orderManager);
        process.start();
    } else {
        System.out.println("No waiting customers for " + car.getBrand() + " " + car.getModel());
    }
}


    public void listOrders() {
        if (orders.isEmpty()) {
            System.out.println("No rental orders found.");
            return;
        }
        for (RentalOrder order : orders) {
            System.out.println(order);
        }
    }

    public void saveAllData(String filename) {
        try {
            FileHandler<CarRentalSystem> handler = new CSVHandler(this);
            handler.saveData(filename);
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("I/O error occurred while accessing file.");
        }
    }

    public void loadAllData(String filename) {
        try {
            FileHandler<CarRentalSystem> handler = new CSVHandler(this);
            CarRentalSystem loaded = handler.loadData(filename);
            this.cars = loaded.cars;
            this.customers = loaded.customers;
            this.orders = loaded.orders;
            System.out.println("Data loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating new file...");
        } catch (DataFormatException e) {
            System.out.println("File has invalid format. Load failed.");
        } catch (IOException e) {
            System.out.println("I/O error occurred while accessing file.");
        }
    }

    public ArrayList<Car> getCars() {
    return cars;
    }

    public ArrayList<Customer> getCustomers() {
    return customers;
    }

    public ArrayList<RentalOrder> getOrders() {
    return orders;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarRentalSystem crs = new CarRentalSystem();

        boolean running = true;
        while (running) {
            System.out.println("\n===== Car Rental Menu =====");
            System.out.println("1. Add Car");
            System.out.println("2. List Available Cars");
            System.out.println("3. Register Customer");
            System.out.println("4. List Customers");
            System.out.println("5. Rent a Car");
            System.out.println("6. View Rental Orders");
            System.out.println("7. Save Data");
            System.out.println("8. Load Data");
            System.out.println("9. View Customer Points");
            System.out.println("10. View Rental Logs");
            System.out.println("11. Exit");
            System.out.print("Select option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Model: ");
                    String model = sc.nextLine();
                    System.out.print("Year: ");
                    int year = sc.nextInt();
                    System.out.print("Daily Rate: ");
                    double rate = sc.nextDouble();
                    sc.nextLine();
                    crs.addCar(new Car(brand, model, year, rate));
                    break;
                case 2:
                    crs.listAvailableCars();
                    break;
                case 3:
                    System.out.print("Customer Name: ");
                    String name = sc.nextLine();
                    System.out.print("Is VIP? (yes/no): ");
                    boolean vip = sc.nextLine().trim().equalsIgnoreCase("yes");
                    crs.addCustomer(new Customer(name, vip));
                    break;
                case 4:
                    crs.listCustomers();
                    break;
                case 5:

                    for (int i = 0; i < crs.getCars().size(); i++) {
                        Car car = crs.getCars().get(i);
                        String status = car.isAvailable() ? "Available" : "Not Available";
                        System.out.printf("%d. %s [%s]%n", i, car, status);
                    }

                    System.out.print("Enter car index to rent or join waiting list: ");
                    int idx = sc.nextInt();
                    sc.nextLine();

                    if (idx < 0 || idx >= crs.getCars().size()) {
                        System.out.println("Invalid car selection.");
                        break;
                    }

                    System.out.print("Enter your name: ");
                    String custName = sc.nextLine();
                    Customer cust = crs.findCustomer(custName);
                    if (cust == null) {
                        System.out.println("Customer not found. Please register first.");
                        break;
                    }

                    Car selected = crs.getCars().get(idx);

                    if (selected.isAvailable()) {
                        System.out.print("How many days to rent?: ");
                        int days = sc.nextInt();
                        crs.rentCar(custName, idx, days);
                    } else {
                        System.out.println("Selected car is not available.");
                        System.out.print("Do you want to join the waiting list? (yes/no): ");
                        String join = sc.nextLine();
                        if (join.equalsIgnoreCase("yes")) {
                            System.out.print("How many days would you like to rent?: ");
                            int days = sc.nextInt();
                            sc.nextLine();
                            crs.addCustomerToQueue(selected, cust, days);
                        }
                    }
                    break;

                case 6:
                    crs.listOrders();
                    break;
                case 7:
                    System.out.print("Enter filename to save: ");
                    String saveName = sc.nextLine();
                    crs.saveAllData(saveName);
                    break;
                case 8:
                    System.out.print("Enter filename to load: ");
                    String loadName = sc.nextLine();
                    crs.loadAllData(loadName);
                    break;
                case 9:
                    System.out.println("=== Customer Points ===");
                    for (Customer c : crs.getCustomers()) {
                        System.out.println(c.toString() + " : " + c.getPoints() + " points");}
                        break;
                case 10:
                    crs.orderManager.printAllLogs();
                    break;  
                case 11:
                    running = false;
                    System.out.println("Thank you for using the system.");
                    break;          
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}




