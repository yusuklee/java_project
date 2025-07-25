package com.example.CarSystem;

public class Customer {
    private String name;
    private boolean isVip;
    private int points;

    public Customer(String name, boolean isVip){
        this.name = name;
        this.isVip = isVip;
    }

    public String getName(){
        return this.name;
    }
    public boolean isVip(){
        return this.isVip;
    }

     public void addPoints(int amount) {
        this.points += amount;
    }

    public int getPoints() {
        return this.points;
    }
    
    @Override
    public String toString(){
        if(this.isVip()){
            return this.name+"(VIP)";
        }else{
            return this.name;
        }
    }
}
