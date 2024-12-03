package pwdsystem;

import java.util.Scanner;

public class rhu {
    public void pRHU() {
        Scanner sc = new Scanner(System.in);
        rhu rh = new rhu();
        boolean exit = true;
        int action;
                
        do {
            System.out.println("\n----- WELCOME TO THE SYSTEM -----");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Record");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Exit");
            System.out.println("---------------------------------");
            System.out.print("Enter Action: ");
            while (!sc.hasNextInt()) {
            System.out.println("Invalid Action. Please enter a valid number.");
            sc.next();
            System.out.print("Enter Action: ");
            }
            action = sc.nextInt();
            
            switch (action) {                   
                case 1:
                    pwd pd = new pwd();
                    System.out.println("\nList of Healthcare Provider");
                    pd.viewDr();
                    System.out.println("\nList of PWD Patient");
                    pd.viewPWD();
                    rh.addApp();
                    break;
                    
                case 2:
                    System.out.println("\nList of PWD Patient");
                    rh.viewP();
                    System.out.println("\nList of Appointment");
                    rh.viewRec();
                    break;
                    
                case 3:
                    System.out.println("\nList of Appointment");
                    rh.viewRec();
                    rh.updateApp();
                    System.out.println("\nList of Appointment");
                    rh.viewRec();
                    break;
                
                case 4:
                    System.out.println("\nList of Appointment");
                    rh.viewRec();
                    rh.deleteApp();
                    System.out.println("\nList of Appointment");
                    rh.viewRec();
                    break;
                    
                case 5:
                    System.out.print("Do you want to exit? (yes/no): ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Action Error, There's no "+action+" in choices");
            }

        } while (exit);
        System.out.println("Thank You!");
    }
    
    public void addApp(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int pid;
        
        System.out.print("Dotor Name: ");
        String did = sc.nextLine();
        System.out.print("Enter PWD ID: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter PWD ID: ");
        }
        pid = sc.nextInt();       
        
        sc.nextLine();
        System.out.print("Date (mm/dd/yy): ");
        String dd = sc.nextLine();
        System.out.print("Reason for Checkup: ");
        String rc = sc.nextLine();
        
        String sql = "INSERT INTO record (dr_name, s_id, r_dd, r_rc) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, did, pid, dd, rc);
    }
    
    public void viewP(){
        String qry = "SELECT * FROM pwd";
        String [] hdrs = {"Patient ID", "First Name", "Last Name"};
        String [] clms = {"s_id", "s_fname", "s_lname"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void viewRec(){
        String qry = "SELECT record.r_id, pwd.dr_name, pwd.s_id, pwd.s_fname, record.r_dd, record.r_rc FROM record "
                + "INNER JOIN pwd ON pwd.s_id = record.s_id";
        String[] hdrs = {"Appointment ID", "Doctor Name", "PWD ID", "PWD Name", "Date", "Reason for Checkup"};
        String[] clms = {"r_id", "dr_name", "s_id", "s_fname" , "r_dd", "r_rc"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updateApp() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("Enter Appointment ID: ");
         while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Appointment ID: ");
        }
        id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT s_id FROM pwd WHERE s_id = ?", id)) == 0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Enter Appointment ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Appointment ID again: ");
            }
            id = sc.nextInt();
        }
        
        sc.nextLine();
        System.out.print("New Date: ");
        String dd = sc.nextLine();
        System.out.print("New Reason of Checkup: ");
        String rc = sc.nextLine();
        
        String sql = "UPDATE record SET r_dd = ?, r_rc = ? WHERE r_id = ?";
        conf.updateRecord(sql, dd, rc, id);
    }
    
    public void deleteApp(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("\nEnter Appointment ID to Delete: ");
         while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Appointment ID to Delete: ");
        }
        id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT r_id FROM record WHERE r_id = ?", id)) == 0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Enter Appointment ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Appointment ID again: ");
            }
            id = sc.nextInt();
        }
        
        String sql = "DELETE FROM record WHERE r_id = ?";
        conf.deleteRecord(sql, id);
    }
}