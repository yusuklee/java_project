package com.example.ClassRoom.Class2;

public class StaffManager {
    public static void main(String[] args) {
        Staff staff1 = new Staff("James Wright", 29);
        staff1.setDepartment("Accounting");
        staff1.setWorkDays(365);
        staff1.setVacationDays(15);

        Staff staff2 = new Staff("Peter coolidge", 32);
        staff2.setDepartment("R&D");
        staff2.setWorkDays(1095);
        staff2.setVacationDays(7);

        Staff staff3 = new Staff("Amy Smith", 27);

        System.out.println(staff1);
        System.out.println(staff2);
        System.out.println(staff3);

        boolean predict1 = staff1.sameCareer(staff2, staff3);
        if(predict1)System.out.println("Same");
        else System.out.println("Not same");

        staff3.setDepartment("R&D");
        staff3.setWorkDays(1095);

        boolean predict2 = staff1.sameCareer(staff2, staff3);
        if(predict2)System.out.println("Same");
        else System.out.println("Not same");

        staff1.vacation(10);
        staff3.vacation(20);
        staff3.vacation(1);
    }
}
