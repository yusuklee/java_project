package com.example.ClassRoom.Class4;

import java.time.LocalDateTime;

public class Account {
    public static int count=0;
    private String name;
    private int yearlyInterest;
    LocalDateTime created;
    int balance;

    public Account(String name, int yearlyInterest, LocalDateTime created){
        this.balance = 0;
        this.name = name;
        this.yearlyInterest = yearlyInterest;
        this.created = created;
    }

    public int getBalance() {
        return balance;
    }

    public void increaseYearlyInterest(int increase){
        this.yearlyInterest+=increase;
    }

    public void receiveIncome(int income){
        this.balance +=income;
    }

    public void receiveInterest(){
        this.balance = this.balance+(int)this.balance*(yearlyInterest/1200);
    }

    @Override
    public String toString() {
        return "이름: "+this.name+" 연이자: "+this.yearlyInterest+" 잔고: "+this.balance+" 가입일: "+this.created;
    }
}
