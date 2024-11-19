package pwdsystem;

import java.util.Scanner;

public class pwd { 
    public void addPWD(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nPWD First Name: ");
        String fname = sc.nextLine();
        System.out.print("PWD Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Date of Birth (mm/dd/yy): ");
        String db = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("PWD Status: ");
        String status = sc.nextLine();

        System.out.println("\n--------- DOCTOR ------------");
        System.out.print("Doctor Full Name: ");
        String dfname = sc.nextLine();
        System.out.print("Doctor Specialization: ");
        String ds = sc.nextLine();
        System.out.print("Contact Number: ");
        String cn = sc.nextLine();
        while (!cn.matches("\\d{11}")) {
            System.out.println("Invalid input. Please enter a valid 11-digit phone number.");
            System.out.print("Enter Contact Number Again: ");
            cn = sc.nextLine();
        };
        
        String sql = "INSERT INTO pwd (s_fname, s_lname, s_db, s_address, s_status, dr_name, dr_spec, dr_cnum) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, db, address, status, dfname, ds, cn);
    }
    
    public void viewPWD() {   
        String qry = "SELECT * FROM pwd ";
        String[] hdrs = {"PWD ID", "First Name", "Last Name", "Birth Date", "Address", "PWD Status"};
        String[] clms = {"s_id", "s_fname", "s_lname", "s_db", "s_address", "s_status"};

        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void viewDr() {
        String qry = "SELECT * FROM pwd";
        String[] hdrs = {"Doctor Full Name", "Specialization", "Contact Number"};
        String[] clms = {"dr_name", "dr_spec", "dr_cnum"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updatePWD(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("Enter PWD ID to Update: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter PWD ID to Update: ");
        }
        id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT s_id FROM pwd WHERE s_id = ?", id)) == 0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Enter PWD ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter PWD ID again: ");
            }
            id = sc.nextInt();
        } 
        
        System.out.print("Enter new Address: ");
        String address = sc.next();
        System.out.print("Enter new Status: ");
        String status = sc.next();
        
        String qry = "UPDATE pwd SET s_address = ?, s_status = ? WHERE s_id = ?";
        conf.updateRecord(qry, address, status, id);       
    }
    
    public void deletePWD(){
        Scanner sc= new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("Enter PWD ID to Delete: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter PWD ID to Delete: ");
        }
        id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT s_id FROM pwd WHERE s_id = ?", id)) == 0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Enter PWD ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter PWD ID again: ");
            }
            id = sc.nextInt();
        }
        
        String qry = "DELETE FROM pwd WHERE s_id = ?";
        conf.deleteRecord(qry, id);
    }
    
    public void ePWD(){
    Scanner sc = new Scanner(System.in);
    pwd pd = new pwd();
        boolean exit = true;
        int action;
        
        do{
            System.out.println("\n---------- WELCOME TO THE SYSTEM ---------");
            System.out.println("| 1. Add PWD and Doctor                  |");
            System.out.println("| 2. List of PWD's and Doctor's          |");
            System.out.println("| 3. Update PWD Information              |");
            System.out.println("| 4. Delete PWD Information              |");
            System.out.println("| 5. Exit                                |");
            System.out.println("------------------------------------------");
            System.out.print("Enter Action: ");
            while (!sc.hasNextInt()) {
            System.out.println("Invalid Action. Please enter a valid number.");
            sc.next();
            System.out.print("Enter Action: ");
            }
            action = sc.nextInt();
            
            switch(action){
                case 1:
                    pd.addPWD();
                break;
                    
                case 2:
                    System.out.println("\n---- PWD INFORMATION ----");
                    pd.viewPWD();
                    System.out.println("\n----- DOCTOR INFORMATIOM ----");
                    pd.viewDr();
                break;
                
                case 3:
                    System.out.println("\n---- PWD INFORMATION ----");
                    pd.viewPWD();
                    pd.updatePWD();
                    System.out.println("\n---- PWD INFORMATION ----");
                    pd.viewPWD();
                break;
                
                case 4:
                    System.out.println("\n---- PWD INFORMATION ----");
                    pd.viewPWD();
                    System.out.println("\n---- DOCTOR INFORMATION ----");
                    pd.viewDr();
                    pd.deletePWD();
                    System.out.println("\n---- PWD INFORMATION ----");
                    pd.viewPWD();
                    System.out.println("\n---- DOCTOR INFORMATION ----");
                    pd.viewDr();
                break;
                
                case 5:
                    System.out.print("Do you want to exit? (yes/no): ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Action Error, There's no such number");      
            }
        }while(exit);
}
}