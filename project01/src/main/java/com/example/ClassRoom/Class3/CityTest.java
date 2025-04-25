package com.example.ClassRoom.Class3;

public class CityTest {
    public static void main(String[] args) {
        City city1 = new City("Seoul", 23, 45);
        City city2 = new City("Paris", 123, 41);
        City city3 = new City("Racoon City");
        City city4 = new City("Mega City");

        System.out.println(city1);
        System.out.println(city2);
        System.out.println(city3);
        System.out.println(city4);

        System.out.printf("Seoul-Paris:%.2f\n",City.distance(city1,city2));
        System.out.printf("Seoul-Racoon City:%.2f\n",City.distance(city1,city3));
        System.out.printf("Seoul-Mega City:%.2f",City.distance(city1,city4));
    }
}
