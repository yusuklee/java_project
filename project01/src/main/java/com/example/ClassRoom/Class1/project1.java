import java.util.Scanner;

public class project1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("입력: "); 
        String input = scanner.nextLine();
        String[] name_file = input.split(","); //사람이름과 파일이름분리 gil dong go 와 home~~
        String[] nameSplit = name_file[0].split(" "); // 사람이름을 한글자씩 분리 gil | dong | go
        String[] file_split = name_file[1].split("\\."); //homework.ppt를 homework|ppt로 분리
        

        System.out.println("Name Length(Korean) :"+nameSplit.length);
        System.out.println(nameSplit[0].substring(0,1).toUpperCase()+"."+
        nameSplit[1].substring(0,1).toUpperCase()+"."+
        nameSplit[2].substring(0,1).toUpperCase()+
        nameSplit[2].substring(1)+
        " submitted "+file_split[0].substring(0, 1).toUpperCase()+
        file_split[0].substring(1)+".pdf");
       
    }
}
