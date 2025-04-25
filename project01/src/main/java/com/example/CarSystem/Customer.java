package com.example.CarSystem;

public class Customer {
    private String name;
    private boolean isVip;
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
    @Override
    public String toString(){
        if(this.isVip()){
            return this.name+"(VIP)";
        }else{
            return this.name;
        }
    }
}
