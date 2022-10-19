// Add another comment.
package ticketsystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

public class BookTicket {
    private Scanner scan;
    
    public BookTicket() {
        scan = new Scanner(System.in);
    }
    
    public Destination selectDestination() {
        String input = "";
        boolean validInput = false;
        
        Destination destination = Destination.WELLINGTON;
        while(!(validInput)) {
            System.out.println("Select a destination: \n");
            
            System.out.println("[1] Wellington");
            System.out.println("[2] Picton");
            System.out.print("> ");
            input = scan.nextLine();
            
            if (input.equals("1")) {
                destination = Destination.WELLINGTON;
                validInput = true;
            }
            else if (input.equals("2")) {
                destination = Destination.PICTON;
                validInput = true;
            }
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        
        return destination;
    }
    
    public LocalDate selectDate() {
        String input = "";
        boolean validInput = false;
        
        LocalDate date1 = LocalDate.now().plusDays(14);
        LocalDate date2 = LocalDate.now().plusDays(17);
        LocalDate date3 = LocalDate.now().plusDays(25);
        LocalDate date4 = LocalDate.now().plusMonths(1);
        LocalDate date5 = LocalDate.now().plusMonths(2);
        
        LocalDate userDate = LocalDate.now();
        
        while (!(validInput)) {
            System.out.println("Select a travel date: \n");
            
            System.out.println("[1] "+printDate(date1));
            System.out.println("[2] "+printDate(date2));
            System.out.println("[3] "+printDate(date3));
            System.out.println("[4] "+printDate(date4));
            System.out.println("[5] "+printDate(date5));
            System.out.print("> ");
            
            input = scan.nextLine();
            try {
                switch (Integer.parseInt(input)) {
                    case 1:
                    {
                        userDate = date1;
                        validInput = true;
                        break;
                    }
                    case 2:
                    {
                        userDate = date2;
                        validInput = true;
                        break;
                    }
                    case 3:
                    {
                        userDate = date3;
                        validInput = true;
                        break;
                    }
                    case 4:
                    {
                        userDate = date4;
                        validInput = true;
                        break;
                    }
                    case 5:
                    {
                        userDate = date5;
                        validInput = true;
                        break;
                    }
                    default:
                    {
                        System.out.println("Invalid number.");
                        break;
                    }
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }
        }
        
        return userDate;
    }
    
    private String printDate(LocalDate date) {
        return date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
    }
    
    public LocalTime selectTime() {
        String input = "";
        boolean validInput = false;
        
        LocalTime userTime = LocalTime.now();
        
        LocalTime time1 = LocalTime.of(8, 0);
        LocalTime time2 = LocalTime.of(10, 30);
        LocalTime time3 = LocalTime.of(1, 30);
        LocalTime time4 = LocalTime.of(3, 15);
        
        while (!(validInput)) {
            System.out.println("Select a departure time: \n");
            
            System.out.println("[1] "+time1);
            System.out.println("[2] "+time2);
            System.out.println("[3] "+time3);
            System.out.println("[4] "+time4);
            System.out.print("> ");
            
            input = scan.nextLine();
            
            try {
                switch (Integer.parseInt(input)) {
                    case 1:
                    {
                        userTime = time1;
                        validInput = true;
                        break;
                    }
                    case 2:
                    {
                        userTime = time2;
                        validInput = true;
                        break;
                    }
                    case 3:
                    {
                        userTime = time3;
                        validInput = true;
                        break;
                    }
                    case 4:
                    {
                        userTime = time4;
                        validInput = true;
                        break;
                    }
                    default:
                    {
                        System.out.println("Invalid number.");
                        break;
                    }
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }
        }
        
        return userTime;
    }
    
    public Meal selectMeal() {
        HashMap<Integer, Meal> menu = new Menu().meals;
        String input = "";
        boolean validInput = false;
        
        Meal meal = new Meal("", "", 0);
        
        while (!(validInput)) {
            System.out.println("Select a meal:\n");
            for (int i = 1; i <= menu.size(); i++) {
                try {
                    Thread.sleep(500);
                } 
                catch (InterruptedException ex) {
                    System.err.println(ex);
                }
                System.out.println("["+i+"] "+menu.get(i));
            }
            System.out.print("> ");
            input = scan.nextLine();
            
            try {
                int i = Integer.parseInt(input);
                if (i >= 1 && i <= menu.size()) {
                    meal = menu.get(i);
                    validInput = true;
                }
                else {
                    System.out.println("Invalid number.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }
        }
        return meal;
    }
    
    public Drink selectDrink() {
        HashMap<Integer, Drink> menu = new Menu().drinks;
        String input = "";
        boolean validInput = false;
        
        Drink drink = new Drink("", "", 0);
        
        while (!(validInput)) {
            System.out.println("Select a drink:\n");
            for (int i = 1; i <= menu.size(); i++) {
                try {
                    Thread.sleep(500);
                } 
                catch (InterruptedException ex) {
                    System.err.println(ex);
                }
                System.out.println("["+i+"] "+menu.get(i));
            }
            System.out.print("> ");
            input = scan.nextLine();
            
            try {
                int i = Integer.parseInt(input);
                if (i >= 1 && i <= menu.size()) {
                    drink = menu.get(i);
                    validInput = true;
                }
                else {
                    System.out.println("Invalid number.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }
        }
        return drink;
    }
}
