package ticketsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;

public class UserIO {
    public UserIO() {
    }
    
    // Method searches for username and returns true if found.
    public boolean containsUser(String username, boolean isMember) {
        try {
            BufferedReader br = readUser(isMember);
            String line = "";
            
            // Comparing file lines as char arrays with the username as char array.
            char[] userToChar = new char[username.length()];
            userToChar = username.toCharArray();
            
            while ((line = br.readLine()) != null) {
                char[] lineToChar = new char[line.length()];
                lineToChar = line.toCharArray();
                
                boolean match = true;
                
                for (int i = 0; lineToChar[i] != ' '; i++) {
                    if (i >= username.length() || lineToChar[i] != userToChar[i]) {
                        // Username in line is longer than input username or the characters do not match.
                        match = false;
                    }
                }
                
                if (match) {
                    br.close();
                    return match;
                }
                
            }
            br.close();
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
        return false;
    }
    
    public boolean containsMember(String username) {
        return containsUser(username, true);
    }
    
    // Check if memberId exists, return false if not.
    public boolean containsMemberID(int memberID) {
        try {
            BufferedReader br = readUser(true);
            String line = "";
            
            while ((line = br.readLine()) != null) {
                if (line.contains(String.valueOf(memberID))) {
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
    
    // Find line in file containing username then read user data from that line.
    public User loadUser(String username) {
        String password = "";
        String fullname = "";
        String email = "";
        HashSet<Ticket> bookings = new HashSet<>();
        
        try {
            BufferedReader br = readUser(false);
            Scanner sc = new Scanner(br);
            boolean found = false;
            boolean endOfLine = false;
            int index = 1;
            String s = "";
            
            while (sc.hasNext() && index != 0) {
                s = sc.next();
                if (s.equals("-")) {
                    index++;
                    if (index == 6) {
                        if (endOfLine) {
                            index = 0;
                        }
                        else {
                            index = 1;
                        }
                    }
                }
                else if (index == 1 && !(found) && s.equals(username)) {
                    found = true;
                }
                else if (found) {
                    switch (index) {
                    case 2:
                    {
                        password = s;
                        break;
                    }
                    case 3:
                    {
                        fullname += s;
                        s = sc.next();
                        
                        while (!(s.equals("-"))) {
                            fullname += " "+s;
                            s = sc.next();
                        }
                        index++;
                        break;
                    }
                    case 4:
                    {
                        email = s;
                        break;
                    }
                    case 5:
                    {
                        bookings.add(new TicketIO().loadTicket(Integer.parseInt(s)));
                        endOfLine = true;
                        break;
                    }
                    }
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
        
        return new User(username, password, fullname, new PayAccIO().loadPayAcc(email), bookings);
    }
    
    public Member loadMember(String username) {
        String password = "";
        String fullname = "";
        String email = "";
        HashSet<Ticket> bookings = new HashSet<>();
        int memberID = 0;
        LocalDate expiry = LocalDate.now();
        
        try {
            BufferedReader br = readUser(true);
            Scanner sc = new Scanner(br);
            boolean found = false;
            boolean endOfLine = false;
            int index = 1;
            String s = "";
            
            while (sc.hasNext() && !(endOfLine)) {
                s = sc.next();
                if (s.equals("-")) {
                    index++;
                    if (index == 8) {
                        index = 1;
                    }
                }
                else if (index == 1 && !(found) && s.equals(username)) {
                    found = true;
                }
                else if (found) {
                    switch (index) {
                    case 2:
                    {
                        password = s;
                        break;
                    }
                    case 3:
                    {
                        fullname += s;
                        s = sc.next();
                        
                        while (!(s.equals("-"))) {
                            fullname += " "+s;
                            s = sc.next();
                        }
                        index++;
                        break;
                    }
                    case 4:
                    {
                        email = s;
                        break;
                    }
                    case 5:
                    {
                        bookings.add(new TicketIO().loadTicket(Integer.parseInt(s)));
                        break;
                    }
                    case 6:
                    {
                        memberID = Integer.parseInt(s);
                        break;
                    }
                    case 7:
                    {
                        int day = Integer.parseInt(s);
                        int month = Integer.parseInt(sc.next());
                        int year = Integer.parseInt(sc.next());
                        expiry = LocalDate.of(year, month, day);
                        endOfLine = true;
                        break;
                    }
                    }
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
        
        return new Member(username, password, fullname, new PayAccIO().loadPayAcc(email), bookings, memberID, expiry);
    }
    
    // Print user data to the Users file.
    public void saveUser(User user) {
        try {
            PrintWriter pw = writeUser();
            
            pw.print("\n"+user.getUsername()+" -");
            pw.print(" "+user.getPassword()+" -");
            pw.print(" "+user.getFullname()+" -");
            
            if (user.getPayAccount() != null) {
                pw.print(" "+user.getPayAccount().getEmail()+" - ");
            }
            else {
                pw.print(" - ");
            }
            
            for (Ticket t: user.getBookings()) {
                pw.print(t.getTicketNo()+" ");
            }
            
            pw.print("-");
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    // Print member data to the members file.
    public void saveMember(Member member) {
        try {
            PrintWriter pw = writeMember();

            pw.print("\n"+member.getUsername()+" -");
            pw.print(" "+member.getPassword()+" -");
            pw.print(" "+member.getFullname()+" -");

            if (member.getPayAccount() != null) {
                pw.print(" "+member.getPayAccount().getEmail()+" - ");
            }
            else {
                pw.print(" - ");
            }

            for (Ticket t: member.getBookings()) {
                pw.print(t.getTicketNo()+" ");
            }

            pw.print("- "+member.getMemberID()+" -");

            pw.print(" "+member.getExpiry().getDayOfMonth());
            pw.print(" "+member.getExpiry().getMonthValue());
            pw.print(" "+member.getExpiry().getYear()+" -");

            pw.close();
        }
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    // Find line containing user's username, fullname, and password, then remove the line.
    // This method reads all lines to the output string except for the line containing the input user's data.
    // The output string is then written back onto the file.
    public void removeUser(User user) {
        try {
            BufferedReader br = readUser(false);
            
            String line = "";
            String out = "";
            
            while ((line = br.readLine()) != null) {
                if (!(line.contains(user.getUsername())) && !(line.contains(user.getFullname())) && !(line.contains(user.getPassword()))) {
                    out += line+"\n";
                }
            }
            
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/Users.txt"));
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
    
    // Remove member from the Members file.
    public void removeMember(Member member) {
        try {
            BufferedReader br = readUser(true);
            
            String line = "";
            String out = "";
            
            while ((line = br.readLine()) != null) {
                if (!(line.contains(member.getUsername())) && !(line.contains(member.getFullname())) && !(line.contains(member.getPassword()))) {
                    out += line+"\n";
                }
            }
            
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/Members.txt"));
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
    
    // Method cleans blank lines in the file left from remove methods.
    public void cleanUser() {
        try {
            BufferedReader br = readUser(false);
            
            String line = "";
            String out = "";
            while((line = br.readLine()) != null) {
                if (!(line.isEmpty())) {
                    out += line+"\n";
                }
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/Users.txt"));
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
    
    // Remove empty lines from the Members file.
    public void cleanMember() {
        try {
            BufferedReader br = readUser(true);
            
            String line = "";
            String out = "";
            while((line = br.readLine()) != null) {
                if (!(line.isEmpty())) {
                    out += line+"\n";
                }
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/Members.txt"));
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
    
    // Return BufferedReaders and PrintWriters to be used for file reading/writing.
    public BufferedReader readUser(boolean isMember) throws FileNotFoundException {
        if (isMember) {
            return new BufferedReader(new FileReader("./resources/Members.txt"));
        }
        return new BufferedReader(new FileReader("./resources/Users.txt"));
    }
    
    public PrintWriter writeUser() throws FileNotFoundException {
        return new PrintWriter(new FileOutputStream(("./resources/Users.txt"), true));
    }
    
    public PrintWriter writeMember() throws FileNotFoundException {
        return new PrintWriter(new FileOutputStream(("./resources/Members.txt"), true));
    }
}
