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
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TicketIO {
    // Method searches file for ticket number and returns true if found.
    public boolean containsTicket(int ticketNo) {
        try {
            BufferedReader br = readTicket();
            Scanner sc = new Scanner(br);
            int lineIndex = 1;
            String s = "";
            Integer i = 0;
            
            while (sc.hasNext()) {
                s = sc.next();
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                }
                if (i.equals(ticketNo) && lineIndex == 1) {
                    br.close();
                    return true;
                }
                if (s.equals("-")) {
                    if (lineIndex == 9) {
                        lineIndex = 1;
                    }
                    else {
                        lineIndex++;
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
        return false;
    }
    
    public Ticket loadTicket(int ticketNo) {
        // Initialise variables to be passed into Ticket constructor.
        double amountPaid = 0.0;
        Meal meal = new Meal("", "", 0);
        Drink drink = new Drink("", "", 0);
        LocalDate dateBooked = LocalDate.now();
        LocalDate travelDate = LocalDate.now();
        LocalTime departTime = LocalTime.now();
        Destination destination = Destination.WELLINGTON;
        String username = "";
        
        try {
            // Read and return Ticket object from file.
            BufferedReader br = readTicket();
            Scanner sc = new Scanner(br);
            String s = "";
            int index = 1;
            boolean found = false;
            
            String mealName = "";
            String drinkName = "";
            
            while (sc.hasNext()) {
                s = sc.next();
                if (s.equals("-")) {
                    index++;
                    if (index == 10) {
                        index = 1;
                    }
                }
                else if (index == 1 && !(found)) {
                    try {
                        Integer i = Integer.parseInt(s);
                        if (i.equals(ticketNo)) {
                            found = true;
                        }
                    }
                    catch (NumberFormatException e) {
                    }
                }
                else if (found) {
                    switch (index) {
                    case 2: // amountPaid
                    {
                        amountPaid = Double.parseDouble(s);
                        break;
                    }
                    case 3: // meal
                    {
                        mealName += s+" ";
                        break;
                    }
                    case 4: // drink
                    {
                        drinkName += s+" ";
                        break;
                    }
                    case 5: // dateBooked
                    {
                        int day = Integer.parseInt(s);
                        int month = Integer.parseInt(sc.next());
                        int year = Integer.parseInt(sc.next());
                        dateBooked = LocalDate.of(year, month, day);
                        break;
                    }
                    case 6: // travelDate
                    {
                        int day = Integer.parseInt(s);
                        int month = Integer.parseInt(sc.next());
                        int year = Integer.parseInt(sc.next());
                        travelDate = LocalDate.of(year, month, day);
                        break;
                    }
                    case 7: // departTime
                    {
                        int hour = Integer.parseInt(s);
                        int minute = Integer.parseInt(sc.next());
                        departTime = LocalTime.of(hour, minute);
                        break;
                    }
                    case 8: // destination
                    {
                        if (s.equals("WELLINGTON")) {
                            destination = Destination.WELLINGTON;
                        }
                        else {
                            destination = Destination.PICTON;
                        }
                        break;
                    }
                    case 9: // username
                    {
                        username = s;
                        break;
                    }
                }
                }
                br.close();
            }
            
            Menu menu = new Menu();

            Set eSet = menu.meals.entrySet();
            Iterator it = eSet.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Meal m = (Meal) entry.getValue();
                if (mealName.contains(m.getName())) {
                    meal = m;
                }
            }
            
            eSet = menu.drinks.entrySet();
            it = eSet.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Drink d = (Drink) entry.getValue();
                if (drinkName.contains(d.getName())) {
                    drink = d;
                }
            }
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
        
        return new Ticket(ticketNo, amountPaid, meal, drink, dateBooked, travelDate, departTime, destination, username);
    }
    
    // Print ticket data onto file.
    public void saveTicket(Ticket ticket) {
        try {
            PrintWriter pw = writeTicket();
            
            pw.print("\n"+ticket.getTicketNo()+" -");
            pw.print(" "+ticket.getAmountPaid()+" -");
            pw.print(" "+ticket.getMeal().getName()+" -");
            pw.print(" "+ticket.getDrink().getName()+" -");
            
            pw.print(" "+ticket.getDateBooked().getDayOfMonth());
            pw.print(" "+ticket.getDateBooked().getMonthValue());
            pw.print(" "+ticket.getDateBooked().getYear()+" -");
            
            pw.print(" "+ticket.getTravelDate().getDayOfMonth());
            pw.print(" "+ticket.getTravelDate().getMonthValue());
            pw.print(" "+ticket.getTravelDate().getYear()+" -");
            
            pw.print(" "+ticket.getDepartTime().getHour());
            pw.print(" "+ticket.getDepartTime().getMinute()+" -");
            
            pw.print(" "+ticket.getDestination()+" -");
            pw.print(" "+ticket.getUser()+" -");
            
            pw.close();
        } 
        catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
    
    // Remove empty lines from file.
    public void cleanTicket() {
        try {
            BufferedReader br = readTicket();
            
            String line = "";
            String out = "";
            while((line = br.readLine()) != null) {
                if (!(line.isEmpty())) {
                    out += line+"\n";
                }
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/Tickets.txt"));
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
    
    // Return BufferedReaders for file reading.
    public BufferedReader readTicket() throws FileNotFoundException {
        return new BufferedReader(new FileReader("./resources/Tickets.txt"));
    }
    
    public PrintWriter writeTicket() throws FileNotFoundException {
        return new PrintWriter(new FileOutputStream(("./resources/Tickets.txt"), true));
    }
}
