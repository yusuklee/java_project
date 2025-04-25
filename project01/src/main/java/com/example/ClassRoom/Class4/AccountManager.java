package com.example.ClassRoom.Class4;

import java.time.LocalDateTime;
import java.util.Random;

public class AccountManager {
    
    public static void main(String[] args) {
        Random rnd = new Random();
        Account account1 = new Account("이유석", 5, LocalDateTime.of(2025,4,25,11,3));
        System.out.println(account1);
        while (account1.balance<=10000) {
            Account.count++;
            account1.receiveIncome(100);
            account1.receiveInterest();
            if(Account.count==12){
                
                int num = rnd.nextInt(10);
                if(num==1){account1.receiveIncome(100);
                    System.out.println("이벤트 당첨!");}
            }
            if(Account.count==36){
                account1.increaseYearlyInterest(2);
                System.out.println("가입한지 3년이 지나서 이자율이 2%인상됨.");
            }
            
        }
        System.out.println(account1);
    }
}
