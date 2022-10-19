package ticketsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PayAccIO {
    public PayAccIO() {
        // Empty constructor for calling PayAccIO methods.
    }
    
    // Search PayAccounts file for input email, return true if found.
    public boolean containsPayAcc(String email) {
        try {
            BufferedReader br = readPayAcc();
            
            Scanner sc = new Scanner(br);
            String current = "";
            int index = 0;
            
            while (sc.hasNext()) {
                current = sc.next();
                index++;
                if (current.equals(email) && (index % 2 != 0)) {
                    return true;
                }
            }
            
            br.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
        
        return false;
    }
    
    // Remove payAccount data from PayAccounts file.
    // Data is read onto output string if it doesn't match payAccount, then written back onto file.
    public void removePayAcc(PayAcc payAccount) {
        try {
            BufferedReader br = readPayAcc();
            
            String line = "";
            String out = "";
            while ((line = br.readLine()) != null) {
                if (!(line.equals(payAccount.getEmail()+" "+payAccount.getPassword()))) {
                    out += line+"\n";
                }
            }
            
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new PrintWriter("./resources/PayAccounts.txt"));
            bw.append(out);
            bw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    // Find email in PayAccounts file and read data from the line.
    public PayAcc loadPayAcc(String email) {
        String password = "";
        boolean found = false;
        
        try {
            BufferedReader br = readPayAcc();
            Scanner sc = new Scanner(br);
            
            String current = "";
            
            while (sc.hasNext() && !(found)) {
                current = sc.next();
                if (current.equals(email)) {
                    password = sc.next();
                    found = true;
                }
            }
            br.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
        
        return new PayAcc(email, password);
    }
    
    // Print payAccount data onto the PayAccounts file.
    public void savePayAcc(PayAcc payAccount) {
        try {
            PrintWriter pw = writePayAcc();
            
            pw.print("\n"+payAccount.getEmail()+" ");
            pw.print(payAccount.getPassword());
            
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    // Remove blank lines from the file.
    public void cleanPayAcc() {
        try {
            BufferedReader br = readPayAcc();
            
            String line = "";
            String out = "";
            while((line = br.readLine()) != null) {
                if (!(line.isEmpty())) {
                    out += line+"\n";
                }
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/PayAccounts.txt"));
            bw.append(out);
            bw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
     
    public BufferedReader readPayAcc() throws FileNotFoundException {
        return new BufferedReader(new FileReader("./resources/PayAccounts.txt"));
    }
    
    public PrintWriter writePayAcc() throws FileNotFoundException {
        return new PrintWriter(new FileOutputStream(("./resources/PayAccounts.txt"), true));
    }
}
