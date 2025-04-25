package com.example.ClassRoom.Class2;

public class Staff {
    private String name;
    private int age;
    private String department;
    private int workDays;
    private int vacationDays;
    public Staff(String name, int age){
        this.name = name;
        this.age = age;
        this.department = null;
        this.workDays = 0;
        this.vacationDays = 20;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setWorkDays(int workDays) {
        this.workDays = workDays;
    }
    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }
    public String getName() {
        return name;
    }
    public boolean sameCareer(Staff staff1, Staff staff2){
        return staff1.department.equals(staff2.department)&&(staff1.workDays==staff2.workDays);
    }
    public String toString(){
        return "Name:"+this.name+", Age:"+this.age+", Department:"+this.department+", workDays:"+this.workDays
        +", VacationDays:"+this.vacationDays;
    }

    public void vacation(int days){
        if(days>this.vacationDays)System.out.println("남은 휴가일수 부족");
        else {
            System.out.printf("%s,휴가 %d 사용.남은 휴가 일수:%d\n",this.name,days,this.vacationDays);
            this.vacationDays-=days;
        }
    }
}
