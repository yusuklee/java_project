package com.example.ClassRoom.Class3;

import java.util.Random;

public class City {
    private String name;
    private int locationX;
    private int locationY;


    public void setName(String name) {
        this.name = name;
    }
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }
    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public City(String name, int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.name = name;
    }

    public City(String name){
        this.name = name;
        Random rnd = new Random();
        this.locationX = rnd.nextInt(361);
        this.locationY = rnd.nextInt(361);
    }

    public String getName() {
        return name;
    }
    public int getLocationX() {
        return locationX;
    }
    public int getLocationY() {
        return locationY;
    }
    boolean equals(City c){
        return (this.name==c.name)&&(this.locationX==c.locationX)&&(this.locationY==c.locationY);
    }

    @Override
    public String toString() {
        
        return this.name+", "+this.locationX+", "+this.locationY;
    }

    static double distance(City c1, City c2){
        return Math.sqrt(Math.pow(c1.locationX-c2.locationX,2)+ Math.pow(c1.locationY-c2.locationY,2));
    }
}
