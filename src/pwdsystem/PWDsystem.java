package pwdsystem;

import java.util.Scanner;

public class PWDsystem {
    public static void main(String[] args) {
             boolean exit = true;
       
    do{    
        Scanner sc = new Scanner (System.in);
        System.out.println("\n---------- WELCOME TO PWD SYSTEM ----------");
        System.out.println("1. Person With Disability");
        System.out.println("2. Rural Health Unit");
        System.out.println("3. Exit");
        System.out.println("-------------------------------------------");
        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        
        switch(action){
            case 1: 
                pwd pd = new pwd();
                pd.ePWD();
                break;
                
            case 2:
                rhu cs = new rhu();
                cs.pRHU();
                break;
        
            case 3:
                System.out.print("Exit Selected type 'yes' to continue: ");
                String resp = sc.next();
                if(resp.equalsIgnoreCase("yes")){
                exit = false;
                }
                break;
        }
    }while(exit);
        System.out.println("Thank you for using the System!");
    } 
}
