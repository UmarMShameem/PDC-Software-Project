package ticketsystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class BookingApp {
    private static final double TRIP_COST = 65.5;
    
    public BookingApp() { // Empty constructor used to access BookingApp methods.
    }
    
    // Allows user to create a new user account or sign in with an existing one.
    private User signIn() {
        Scanner scan = new Scanner(System.in);
        String input = "";
        boolean registered = false;
        User currentUser = new User(input, "", "");
        
        UserIO ui = new UserIO();
        
        while (!(input.equalsIgnoreCase("x")) && !(registered)) {
            System.out.println("Enter a new or existing username: ");
            System.out.print("> ");
            input = scan.nextLine();
            
            if (ui.containsUser(input, false) && !(input.equalsIgnoreCase("x"))) {
                // User is registered.
                currentUser = ui.loadUser(input);
                registered = true;
            }
            else if (ui.containsMember(input) && !(input.equalsIgnoreCase("x"))) {
                // User is registered as a member.
                currentUser = ui.loadMember(input);
                registered = true;
            }
            
            if (registered) {
                System.out.println("\nWelcome back, "+currentUser.getUsername());
                System.out.println("Please enter your password: ");
                boolean valid = false;
                
                while(!(valid)) {
                    System.out.print("> ");
                    input = scan.nextLine();
                    
                    if (input.equals(currentUser.getPassword())) {
                        valid = true;
                        System.out.println("Successfully logged in.");
                    }
                    else {
                        System.out.println("\nThe password you typed is incorrect.");
                        System.out.println("Try entering your password again: ");
                    }
                }
            }
            else if (!(input.equalsIgnoreCase("x"))) { // Not registered/create account or try again
                // User is not registered.
                System.out.println("You are not currently registered under that username. ");
                currentUser.setUsername(input);
                
                boolean valid = false;
                
                while (!(valid) && !(input.equalsIgnoreCase("x")) && !(registered)) {
                    System.out.println("Select one of the following options by entering their corresponding number, or 'x' to quit.\n");
                
                    System.out.println("[1] Create a new account under the username: "+input);
                    System.out.println("[2] Try another username.");
                    System.out.println("[x] Quit.");
                    System.out.print("> ");

                    input = scan.nextLine();

                    try {
                        int i = Integer.parseInt(input);
                        if (i == 1) { // Create new account
                            boolean validPassword = false;
                            String password = "";
                            String fullname = "";
                            
                            while (!(validPassword) && !(input.equalsIgnoreCase("x"))) {
                                System.out.println("Enter your new password.");
                                System.out.println("Passwords must be 8-20 characters in length and must not contain any spaces.");
                                System.out.print("> ");
                                input = scan.nextLine();
                                
                                if (input.length() >= 8 && input.length() <= 20 && !(input.contains(" "))) {
                                    validPassword = true;
                                    password = input;
                                }
                                else if (!(input.equalsIgnoreCase("x"))) {
                                    System.out.println("Invalid password. Please try again.\n");
                                }
                                
                                if (validPassword) {
                                    System.out.println("Enter your full name: ");
                                    System.out.print("> ");
                                    input = scan.nextLine();
                                    
                                    if (!(input.equalsIgnoreCase("x"))) {
                                        fullname = input;
                                        currentUser = new User(currentUser.getUsername(), password, fullname);
                                        registered = true;
                                        System.out.println("Account registered.");
                                        ui.saveUser(currentUser);
                                    }
                                }
                            }
                            
                        }
                        else if (i == 2) {
                            valid = true;
                        }
                    }
                    catch (NumberFormatException e) {
                        if (!(input.equalsIgnoreCase("x"))) {
                            System.out.println("You did not enter a number. Try again. ");
                        }
                        else {
                            valid = true;
                        }
                    }
                }
            }
        }
        
        if (registered) {
            return currentUser;
        }
        return null;
    }
    
    // Let the program pause before printing out the next line of text.
    public void pauseMain(long miliseconds) {
        try {
            Thread.sleep(miliseconds); // seconds * 1000
        } 
        catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }
    
    // Calls managePayAcc or changePassword based on user input.
    private User editUserSettings(User user) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        
        while (!(input.equalsIgnoreCase("x")) && !(input.equalsIgnoreCase("b"))) {
            System.out.println("    User settings");
            System.out.println("=====================\n");
            
            System.out.println("[1] Change password");
            System.out.println("[2] Add/remove payment method");
            System.out.println("[b] Back to main menu");
            System.out.println("[x] Quit");
            System.out.print("> ");
            
            input = scan.nextLine();
            
            UserIO ui = new UserIO();
            
            if (!(input.equalsIgnoreCase("x")) && !(input.equalsIgnoreCase("b"))) {
                try {
                    switch (Integer.parseInt(input)) {
                        case 1:
                        {
                            String newPassword = new BookingApp().changePassword(user);
                            if (!(newPassword.equalsIgnoreCase("x")) && !(newPassword.equalsIgnoreCase("b"))) {
                                user.setPassword(newPassword);
                                if (user instanceof Member) {
                                    ui.removeMember((Member) user);
                                    ui.saveMember((Member) user);
                                }
                                else {
                                    ui.removeUser(user);
                                    ui.saveUser(user);
                                }
                            }
                            break;
                        }
                        case 2:
                        {
                            user.setPayAccount(new BookingApp().managePayAcc(user));
                            if (user instanceof Member) {
                                ui.removeMember((Member) user);
                                ui.saveMember((Member) user);
                            }
                            else {
                                ui.removeUser(user);
                                ui.saveUser(user);
                            }
                            break;
                        }
                        default:
                        {
                            System.out.println("Invalid number. Please try again.");
                            break;
                        }
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid character. Please try again.");
                }
            }
        }
        
        return user;
    }
    
    // Adds and removes PayAccounts from user and Users file.
    private PayAcc managePayAcc(User user) {
        Scanner scan = new Scanner(System.in);
        String input = "";
        String email = "";
        String password = "";
        boolean validInput = false;
        
        if (user.getPayAccount() == null) {
            System.out.println("You do not currently have a payment method on this account.");
            while (!(validInput)) {
                System.out.println("Add a new PayAccount?\n");

                System.out.println("[y] Yes.");
                System.out.println("[n] No, go back.");
                System.out.print("> ");

                input = scan.nextLine();
                
                if (input.equalsIgnoreCase("y")) {
                    while (!(validInput)) {
                        System.out.println("Enter your email address.");
                        System.out.print("> ");
                        input = scan.nextLine();
                        if (new PayAccIO().containsPayAcc(input)) {
                            System.out.println("That email address is already in use. Please try again.");
                        }
                        else if (input.contains("@") && input.contains(".") && !(input.contains(" "))) {
                            email = input;
                            while (!(validInput)) {
                                System.out.println("Enter your password.");
                                System.out.println("Password must be 8-20 characters in length and not contain any spaces.");
                                System.out.print("> ");
                                input = scan.nextLine();
                                
                                if (input.length() >= 8 && input.length() <= 20 && !(input.contains(" "))) {
                                    password = input;
                                    System.out.println("New PayAccount added.");
                                    validInput = true;
                                }
                                else {
                                    System.out.println("Invalid password. Please try again.");
                                }
                            }
                        }
                        else {
                            System.out.println("That does not look like a valid email address. Please try again.");
                        }
                    }
                }
                else if (input.equalsIgnoreCase("n")) {
                    return null;
                }
                else {
                    System.out.println("Invalid input.");
                }
            }
            
        }
        else {
            System.out.println("Your current PayAccount:");
            System.out.println("========================\n");
            
            System.out.println("Email: "+user.getPayAccount().getEmail());
            System.out.println("Password: ************\n");
            
            while (!(validInput)) {
                System.out.println("[1] Remove PayAccount");
                System.out.println("[b] Back");
                System.out.println("[x] Quit");
                System.out.print("> ");
                input = scan.nextLine();
                
                if (input.equals("1")) {
                    new PayAccIO().removePayAcc(user.getPayAccount());
                    return null;
                }
                else if (input.equalsIgnoreCase("b") || input.equalsIgnoreCase("b")) {
                    return user.getPayAccount();
                }
            }
        }
        
        PayAcc newAcc = new PayAcc(email, password);
        new PayAccIO().savePayAcc(newAcc);
        return newAcc;
    }
    
    // User inputs a new password which is passed into user.password and the user file.
    private String changePassword(User user) {
        System.out.println("    Change password");
        System.out.println("=======================\n");
        
        String input = "";
        Scanner scan = new Scanner(System.in);
        
        boolean validInput = false;
                            
        while (!(validInput)) {
            System.out.println("Enter your current password: ");
            System.out.print("> ");
            input = scan.nextLine();
                                
            if (input.equals(user.getPassword())) {
                while (!(validInput)) {
                    System.out.println("Enter your new password.");
                    System.out.println("Passwords must be 8-20 characters in length and must not contain any spaces or be set to the previous password.");
                    System.out.print("> ");
                                        
                    input = scan.nextLine();
                                        
                    if (input.length() >= 8 && input.length() <= 20 && !(input.contains(" ")) && !(input.equals(user.getPassword()))) {
                        System.out.println("New password set.\n");
                        validInput = true;
                    }
                    else if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("b")) {
                        validInput = true;
                    }
                    else {
                        System.out.println("The password you entered does not meet the specified requirements.");
                    }
                }
            }
            else if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("b")) {
                validInput = true;
            }
            else {
                System.out.println("The password you entered is not correct. Please try again.");
            }
        }
        
        return input;
    }
    
    private Ticket bookTicket(User user) {
        // Allow the user to book a new ticket.
        BookTicket book = new BookTicket();
        
        Destination destination = book.selectDestination();
        LocalDate travelDate = book.selectDate();
        LocalTime departTime = book.selectTime();
        Meal meal = book.selectMeal();
        Drink drink = book.selectDrink();
        
        double cost = TRIP_COST + meal.getPrice() + drink.getPrice();
        
        if (user instanceof Member) {
            cost = applyDiscount(cost);
        }
        
        boolean validInput = false;
        String input = "";
        Scanner scan = new Scanner(System.in);
        Ticket newTicket = new Ticket(cost, meal, drink, travelDate, departTime, destination, user.getUsername());
        
        while (!(validInput)) {
            System.out.println(newTicket);
            System.out.println("Confirm booking? \n");
            
            System.out.println("[1] Confirm");
            System.out.println("[2] Restart booking");
            System.out.println("[b] Back to main menu");
            System.out.println("[x] Close");
            System.out.print("> ");
            input = scan.nextLine();
            
            if (input.equals("1")) {
                validInput = true;
                System.out.println("Confirmed booking no. "+newTicket.getTicketNo());
                new TicketIO().saveTicket(newTicket);
            }
            else if (input.equals("2")) {
                validInput = true;
                return bookTicket(user);
            }
            else if (input.equalsIgnoreCase("b") || input.equalsIgnoreCase("x")) {
                validInput = true;
                return null;
            }
            else {
                System.out.println("Invalid input.");
            }
        }
        
        return newTicket;
    }
    
    // Assigns a member constructor to currentUser if user chooses to become a member.
    // Can print expiry date and ID for membership.
    public User manageMembership(User currentUser) {
        System.out.println("    Membership");
        System.out.println("==================\n");
        
        String input = "";
        Scanner scan = new Scanner(System.in);
                            
        if (currentUser instanceof Member) {
            LocalDate expiry = ((Member) currentUser).getExpiry();
                                
            System.out.println("You currently have an active membership with us.");
            System.out.println("Membership ID: "+((Member) currentUser).getMemberID());
            System.out.println("Membership expiry date: "+expiry+"\n");
                                
            System.out.println("Enjoy discounted rides for the duration of your membership. \n");
                                
            boolean validInput = false;
                                
            while (!(validInput)) {
                System.out.println("[b] Back to main menu");
                System.out.println("[x] Quit");
                System.out.print("> ");
                                    
                input = scan.nextLine();
                                    
                if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("b")) {
                    validInput = true;
                }
                else {
                    System.out.println("You entered an invalid character. Please try again.");
                }
            }
        }
        else { // currentUser is not a member
            System.out.println("You do not have an active membership.\n");
                                
            boolean validInput = false;
                                
            while (!(validInput)) {
                System.out.println("[1] Subscribe for $24.90 a month");
                System.out.println("[b] Back to main menu");
                System.out.println("[x] Quit");
                System.out.print("> ");

                input = scan.nextLine();
                                    
                if (input.equals("1") && currentUser.getPayAccount() != null) {
                    currentUser = new Member(currentUser.getUsername(), currentUser.getPassword(), currentUser.getFullname(), currentUser.getPayAccount(), currentUser.getBookings());
                    validInput = true;
                    System.out.println("Thanks for becoming a member!");
                    UserIO ui = new UserIO();
                    ui.removeUser(currentUser);
                    ui.saveMember((Member) currentUser);
                }
                else if (currentUser.getPayAccount() == null) {
                    System.out.println("You do not currently have a payment method saved.");
                    validInput = true;
                }
                else if (!(input.equalsIgnoreCase("b")) && !(input.equalsIgnoreCase("x"))) {
                    System.out.println("Invalid input. Please try again.");
                }
                else {
                    validInput = true;
                }
            }
                                
        }
        
        return currentUser;
    }
    
    // Called if user is a member, applies discount to the amount paid.
    public double applyDiscount(Double cost) {
        return cost * (1.0 - Member.MEMBER_DISCOUNT);
    }
    
    public static void main(String[] args) {
        System.out.println("||===============================||");
        System.out.println("||InterIslander Ferry Booking App||");
        System.out.println("||===============================||");
        System.out.println("\nEnter 'x' at any time to exit the app.\n");

        BookingApp app = new BookingApp();
        
        User currentUser = app.signIn();
        Scanner scan = new Scanner(System.in);
        String input = "";
        
        while (!(input.equalsIgnoreCase("x")) && currentUser != null) {
            System.out.println("\n  Main menu");
            System.out.println("=============\n");
            
            System.out.println("Select one of the following options by entering their corresponding number, or 'x' to quit.\n");

            app.pauseMain(1500);
            
            System.out.println("[1] Book a ticket");
            System.out.println("[2] Open your tickets");
            System.out.println("[3] Membership");
            System.out.println("[4] Account settings");
            System.out.println("[x] Quit");
            System.out.print("> ");
            
            input = scan.nextLine();
            
            if (!(input.equalsIgnoreCase("x"))) {
                try {
                    switch (Integer.parseInt(input)) {
                        case 1: // Book ticket
                        {
                            if (currentUser.getPayAccount() == null) {
                                System.out.println("You must add a payment method before booking a ticket.");
                            }
                            else {
                                Ticket newTicket = app.bookTicket(currentUser);
                                HashSet<Ticket> currentBookings = currentUser.getBookings();
                                currentBookings.add(newTicket);
                                currentUser.setBookings(currentBookings);
                                UserIO ui = new UserIO();
                                if (currentUser instanceof Member) {
                                    ui.removeMember((Member) currentUser);
                                    ui.saveMember((Member) currentUser);
                                }
                                else {
                                    ui.removeUser(currentUser);
                                    ui.saveUser(currentUser);
                                }
                            }
                            break;
                        }
                        case 2: // Open tickets
                        {
                            if (currentUser.getBookings().isEmpty()) {
                                System.out.println("You do not currently have any bookings with us.");
                            }
                            else {
                                Iterator it = currentUser.getBookings().iterator();
                                while (it.hasNext()) {
                                    System.out.println(it.next());
                                }
                                
                                boolean validInput = false;
                                
                                while (!(validInput)) {
                                    System.out.println("[b] Back to main menu");
                                    System.out.println("[x] Quit");
                                    System.out.print("> ");

                                    input = scan.nextLine();

                                    if (input.equalsIgnoreCase("b") || input.equalsIgnoreCase("x")) {
                                        validInput = true;
                                    }
                                    else {
                                        System.out.println("Invalid input. Please try again.");
                                    }
                                }
                            }
                            break;
                        }
                        case 3:
                        {
                            currentUser = app.manageMembership(currentUser);
                            break;
                        }
                        case 4:
                        {
                            currentUser = app.editUserSettings(currentUser);
                            break;
                        }
                        default:
                        {
                            System.out.println("Invalid number. Please try again.");
                            app.pauseMain(2000);
                            break;
                        }
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid character. Please try again.");
                    app.pauseMain(2000);
                }
            }
        }
        
        // These methods clean out empty lines left by the remove methods.
        UserIO ui = new UserIO();
        ui.cleanUser();
        ui.cleanMember();
        
        new TicketIO().cleanTicket();
        new PayAccIO().cleanPayAcc();
    }
    
}
