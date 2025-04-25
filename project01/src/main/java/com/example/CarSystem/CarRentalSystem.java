package com.example.CarSystem;

import java.util.Scanner;

public class CarRentalSystem {
    private Car[] cars;
    private int carCount;

    private Customer[] customers;
    private int customerCount;

    private RentalOrder[] orders;
    private int orderCount;

    public CarRentalSystem(int maxCars, int maxCustomers, int maxOrders) {
        this.cars = new Car[maxCars];
        this.carCount = 0;
        this.customers = new Customer[maxCustomers];
        this.customerCount = 0;
        this.orders = new RentalOrder[maxOrders];
        this.orderCount = 0;
    }

    // Car 관리
    public void addCar(Car car) {
        cars[carCount++] = car;
        System.out.println("Car added successfully!\n");
    }

    public void listAvailableCars() {
        System.out.println("Available Cars:");
        boolean any = false;
        for (int i = 0; i < carCount; i++) {
            if (cars[i].isAvailable()) {
                System.out.println(i + ". " + cars[i].toString());
                any = true;
            }
        }
        if (!any) System.out.println("No cars available.");
    }

    public boolean hasAvailableCars() {
        for (int i = 0; i < carCount; i++) {
            if (cars[i].isAvailable()) return true;
        }
        return false;
    }


    public void addCustomer(Customer customer) {
        if (customerExists(customer.getName())) {
            System.out.println("Customer already exists.");
        } else {
            customers[customerCount++] = customer;
            System.out.println("Customer registered: " + customer.getName());
        }
    }

    public void listCustomers() {
        if (customerCount == 0) {
            System.out.println("No customers registered.");
            return;
        }
        System.out.println("Registered Customers:");
        for (int i = 0; i < customerCount; i++) {
            System.out.printf("[%d] %s%n", i, customers[i].toString());
        }
    }

    public boolean customerExists(String name) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Customer findCustomer(String name) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getName().equalsIgnoreCase(name)) {
                return customers[i];
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
        if (carIndex < 0 || carIndex >= carCount) {
            System.out.println("Invalid car selection.");
            return;
        }
        Car selected = cars[carIndex];
        if (!selected.isAvailable()) {
            System.out.println("Selected car is not available.");
            return;
        }
        Customer cust = findCustomer(customerName);
        RentalOrder order = (rentalDays > 7)? new LongTermRental(selected, cust, rentalDays):new ShortTermRental(selected, cust, rentalDays);

        orders[orderCount++] = order;
        selected.setAvailability(false);

        System.out.println("Rental Success!");
        System.out.println(order);
    }

    public void listOrders() {
        if (orderCount == 0) {
            System.out.println("No rental orders found.");
            return;
        }
        
        for (int i = 0; i < orderCount; i++) {
            System.out.println(orders[i]);
            
        }
    }

    // 메인 메뉴
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CarRentalSystem crs = new CarRentalSystem(100, 100, 100);

        boolean running = true;
        while (running) {
            System.out.println("\n===== Car Rental Menu =====");
            System.out.println("1. Add Car");
            System.out.println("2. List Available Cars");
            System.out.println("3. Register Customer");
            System.out.println("4. List Customers");
            System.out.println("5. Rent a Car");
            System.out.println("6. View Rental Orders");
            System.out.println("7. Exit");
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
                    if (!crs.hasAvailableCars()) {
                        System.out.println("No cars available for rent.");
                        break;
                    }
                    crs.listAvailableCars();
                    System.out.print("Enter car index to rent: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter your name: ");
                    String custName = sc.nextLine();
                    System.out.print("How many days to rent?: ");
                    int days = sc.nextInt();
                    crs.rentCar(custName, idx, days);
                    break;
                case 6:
                    crs.listOrders();
                    break;
                case 7:
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




