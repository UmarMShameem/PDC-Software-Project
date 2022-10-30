package ticketsystem;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;

public class BookingAppModel extends Observable {
    UserDB userDB;
    MemberDB memberDB;
    TicketDB ticketDB;
    PayAccDB payDB;
    MenuDB menuDB;
    
    User currentUser;
    Output output;
    
    boolean listsFilled;
    
    public BookingAppModel() {
        userDB = new UserDB();
        memberDB = new MemberDB();
        ticketDB = new TicketDB();
        payDB = new PayAccDB();
        menuDB = new MenuDB();
        
        output = new Output();
        
        listsFilled = false;
    }
    
    // If input email and password are valid, then add them to the PAY_ACCOUNTS table.
    public void addPayAccount(String email, String password) {
        if (payDB.containsPayAcc(email)) {
            // Check if the Pay account email is already in the database.
            output.action = Output.PA_IN_USE;
        }
        else if (!(email.contains("@") && email.contains(".") && email.length() > 5)) {
            // Email address must be a valid address(contains @, ., and must be greater than 5 characters in length).
            output.action = Output.INVALID_PA_EMAIL;
        }
        else if (password.length() < 8 || password.length() > 20) {
            // Passwords have a length limit of 8-20 characters.
            output.action = Output.INVALID_PA_PASSWORD;
        }
        else {
            // Create a new Pay account object, save the info to the PAY_ACCOUNTS table.
            // Update the user's PA_EMAIL field in the USERS or MEMBERS table and set the
            // Pay account for the User object.
            output.action = Output.ADD_PAY_ACCOUNT_SUCCESS;
            output.outputString1 = email;
            PayAcc payAccount = new PayAcc(email, password);
            currentUser.setPayAccount(payAccount);
            payDB.savePayAcc(payAccount);
            if (!(currentUser instanceof Member)) {
                userDB.updatePayAccount(currentUser);
            }
            else {
                memberDB.updatePayAccount((Member) currentUser);
            }
        }
        this.setChanged();
        this.notifyObservers(output);
    }

    // Send the user back to the home screen.
    public void backToHome() {
        output.action = Output.BACK_TO_HOME;
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Send the user to the Book Ticket panel.
    public void createBooking() {
        output.action = Output.CREATE_BOOKING;
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Check if the current date is later than the membership expiry date, move User info from 
    // the MEMBERS table to the USERS table if true.
    public void revokeMembershipIfExpired() {
        Member currentMember = (Member) currentUser;
        if (currentMember.getExpiry().compareTo(LocalDate.now()) < 0) {
            memberDB.deleteMember(currentMember);
            currentUser = (User) currentUser; // Type-cast currentUser to be a User object instead of a Member object.
            userDB.saveUser(currentUser);
        }
    }
    
    // Iterate through list of tickets retrieved from the TICKETS table, remove each ticket
    // from the database if the current date is later than the travel date on the ticket.
    public void deleteExpiredTickets() {
        ArrayList<Ticket> ticketList = ticketDB.getTicketList(currentUser);
        for (Ticket t: ticketList) {
            if (t.getTravelDate().compareTo(LocalDate.now()) < 0) {
                ticketDB.deleteTicket(t);
            } 
        }
    }
    
    // Check if the selected booking details are valid, then print out details and costs to the screen.
    public void validateBooking(String journey, String travelDate, String departTime, String mealName, String drinkName) {
        // Get the destination from the journey selection.
        String[] splitJourney = journey.split(" ");
        String destination = splitJourney[splitJourney.length - 1];
        
        // Check if the user has a Pay account saved.
        if (currentUser.getPayAccount() == null) {
            output.action = Output.BOOKING_ERROR;
            output.outputString1 = "Please add a payment method before making a booking.\n"
                    + "You can make a booking by going to Account Settings -> Add/Remove Payment Method.";
        }
        else {
            if (ticketDB.userHasBooking(currentUser, toLocalDate(travelDate), toLocalTime(departTime))) {
                // Check if the user already has a ferry booked on the selected date and time.
                output.action = Output.BOOKING_ERROR;
                output.outputString1 = "It seems you already have a booking for the date and time you selected. Please select another date or time.";
            }
            else if (ticketDB.userHasBooking(currentUser, toLocalDate(travelDate), destination)) {
                // Check if the user already has a ferry booked to the selected destination on the selected date.
                output.action = Output.BOOKING_ERROR;
                output.outputString1 = "It seems you have already booked this trip on the date you selected. Please select another date or journey.";
            }
            else if (mealName == null || drinkName == null) {
                // Ensure that the user selects a meal and drink item.
                output.action = Output.BOOKING_ERROR;
                output.outputString1 = "Please select a meal and drink item.";
            }
            else {
                // Calculate and print the costs and the details.
                // Notify the observer to prompt the user to confirm the booking details.
                output.action = Output.CONFIRM_BOOKING_PROMPT;
                output.outputString1 = "Journey: "+journey+"\n"
                        + "Travel Date: "+travelDate+"\n"
                        + "Departure Time: "+departTime+"\n"
                        + "Meal: "+mealName+"\n"
                        + "Drink: "+drinkName;
                
                output.outputString2 = "Ferry fare: $60.00\n";
                DecimalFormat df = new DecimalFormat();
                df.setMinimumFractionDigits(2);
                df.setMaximumFractionDigits(2);
                double mealCost = 0.0;
                double drinkCost = 0.0;
                
                if (!mealName.equals("No meal")) {
                    mealCost = menuDB.getMeal(mealName).getPrice();
                }
                output.outputString2 += "Meal cost: $"+df.format(mealCost)+"\n";
                
                if (!drinkName.equals("No drink")) {
                    drinkCost = menuDB.getDrink(drinkName).getPrice();
                }
                
                double subtotal = 60.0 + mealCost + drinkCost;
                double total = subtotal;
                output.outputString2 += "Drink cost: $"+df.format(drinkCost)+"\n"
                        + "-----------------------\n"
                        + "Subtotal: $"+df.format(subtotal)+"\n";
                
                if (currentUser instanceof Member) {
                    // Apply member's discount to the total if applicable.
                    output.outputString2 += "Discount: -$"+df.format(subtotal * 0.2)+"\n";
                    total = subtotal * 0.8;
                }
                else {
                    output.outputString2 += "Discount: -$0.00\n";
                }
                
                output.outputString2 += "-----------------------\n"
                        + "Total: $"+df.format(total)+"";
            }
        }
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Get the selected booking details, create a new Ticket object, save details to the TICKETS table.
    public void confirmBooking(String journey, String travelDate, String departTime, String mealName, String drinkName, double amountPaid) {
        // Get the Destination enum.
        Destination destination = Destination.WELLINGTON;
        String departLoc = journey.split(" ")[0];
        if (departLoc.equals("Wellington")) {
            destination = Destination.PICTON;
        }
        
        // Convert date and time strings to LocalDate and LocalTime.
        LocalDate travelDateLD = toLocalDate(travelDate);
        LocalTime departTimeLD = toLocalTime(departTime);
        
        // Get the Meal and Drink objects from the database.
        Meal meal = null;
        if (!mealName.equals("No meal")) {
            meal = menuDB.getMeal(mealName);
        }
        
        Drink drink = null;
        if (!drinkName.equals(output)) {
            drink = menuDB.getDrink(drinkName);
        }
        
        // Save the new Ticket info to the TICKETS table.
        Ticket newTicket = new Ticket(amountPaid, meal, drink, travelDateLD, departTimeLD, destination, currentUser.getUsername());
        ticketDB.saveTicket(newTicket);
        
        // Display booking confirmation and info.
        output.action = Output.CONFIRM_BOOKING_SUCCESS;
        output.outputString1 = "Ferry booking confirmed for "+currentUser.getFullname()+" on "+travelDateLD.format(DateTimeFormatter.ofPattern("dd-LLL-yyyy"))+" "+departTime+" from "+journey+".";
        output.outputString2 = "Your ticket number: "+newTicket.getTicketNo();
        output.outputString3 = "You can view your tickets by clicking on View Bookings on the main menu.";
        this.setChanged();
        this.notifyObservers(output);
        
    }

    // Notify View to switch to the Create Account JPanel.
    public void createAccount() {
        output.action = Output.CREATE_ACCOUNT;
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Get Meal list and Drink list from the MEALS and DRINKS table, add items
    // to the JLists.
    public void createMealDrinkLists() {
        output.action = Output.POPULATE_MEAL_DRINK_LIST;
        output.mealList = menuDB.getMealList();
        output.drinkList = menuDB.getDrinkList();
        
        listsFilled = true;
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Check if the user entered the correct current password and that the new password and confirm
    // password fields meet password requirements, then change the user's password in the database
    // and currentUser object.
    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        if (!currentPassword.equals(currentUser.getPassword())) {
            output.action = Output.WRONG_CURRENT_PASSWORD;
        }
        else if (newPassword.length() < 8 || newPassword.length() > 20 || newPassword.contains(" ")) {
            // Password must be 8-20 characters in length and must not contain spaces.
            output.action = Output.INVALID_NEW_PASSWORD;
        }
        else if (!newPassword.equals(confirmPassword)) {
            // New password and confirm password fields must match.
            output.action = Output.NEW_PASSWORD_MISMATCH;
        }
        else {
            // Change the password.
            currentUser.setPassword(newPassword);
            if (userDB.containsUser(currentUser.getUsername())) {
                userDB.changePassword(currentUser, newPassword);
            }
            else {
                memberDB.changePassword((Member) currentUser, newPassword);
            }
            output.action = Output.CHANGE_PASSWORD_SUCCESS;
        }
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Create a new User account, save details to the USERS table, notify View that an account has
    // been created.
    public void confirmCreateAccount(String username, String fullname, String password) {
        currentUser = new User(username, password, fullname);
        userDB.saveUser(currentUser);
        output.action = Output.CREATE_ACCOUNT_SUCCESS;
        output.outputString1 = currentUser.getUsername();
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Get the details of the selected menu item from the database, display them on the panel.
    public void printItemInfo(String item) {
        if (item.equals("No meal")) {
            output.action = Output.PRINT_MEAL_INFO;
            output.outputString1 = "";
        }
        else if (item.equals("No drink")) {
            output.action = Output.PRINT_DRINK_INFO;
            output.outputString1 = "";
        }
        else {
            if (menuDB.containsMeal(item)) { // Check if selected item is a meal or drink.
                Meal selectedMeal = menuDB.getMeal(item);
                output.action = Output.PRINT_MEAL_INFO;
                output.outputString1 = selectedMeal.getDesc();
                output.outputString2 = "Price: $"+String.valueOf(selectedMeal.getPrice())+"0";
            }
            else {
                Drink selectedDrink = menuDB.getDrink(item);
                output.action = Output.PRINT_DRINK_INFO;
                output.outputString1 = selectedDrink.getDesc();
                output.outputString2 = "Price: $"+String.valueOf(selectedDrink.getPrice())+"0";
            }
        }
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Move user details from the USERS table to the MEMBERS table.
    // Set currentUser to be a Member instance.
    public void registerMember() {
        if (currentUser.getPayAccount() == null) {
            // User must have a Pay account saved.
            output.action = Output.REGISTER_MEMBER_ERROR;
        }
        else {
            Member currentUserMember = new Member(currentUser);
            userDB.deleteUser(currentUser);
            memberDB.saveMember(currentUserMember);
            currentUser = currentUserMember;
            
            output.action = Output.REGISTER_MEMBER_SUCCESS;
            // Output the member ID and expiry date to the panel.
            output.outputString1 = String.valueOf(currentUserMember.getMemberID());
            output.outputString2 = currentUserMember.getExpiry().toString();
        }
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Notify the observer to display the user's membership details.
    public void viewMembership() {
        if (currentUser instanceof Member) {
            output.action = Output.VIEW_MEMBERSHIP;
            Member currentMember = (Member) currentUser;
            output.outputString1 = String.valueOf(currentMember.getMemberID());
            output.outputString2 = currentMember.getExpiry().toString();
        }
        else {
            // If the user is not a member, notify the observer to display the membership option prompt.
            output.action = Output.VIEW_MEMBERSHIP_OPTION;
        }
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Notify the observer to display the account settings.
    // Display the user's Pay account email (if applicable), username and full name.
    public void viewAccountSettings() {
        output.action = Output.VIEW_ACCOUNT_SETTINGS;
        output.outputString1 = currentUser.getUsername();
        output.outputString2 = currentUser.getFullname();
        
        if (currentUser.getPayAccount() != null) {
            output.outputString3 = "Saved payment method: "+currentUser.getPayAccount().getEmail();
        }
        else {
            output.outputString3 = "No payment method saved.";
        }
        
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Load and display ticket from the TICKETS table matching the selected ticket number.
    public void viewTicket(int ticketNo) {
        Ticket currentTicket = ticketDB.loadTicket(ticketNo);
        
        output.outputString1 = "                         Ticket no. "+currentTicket.getTicketNo()+"\n\n"
                + "Ticket for: "+currentUser.getFullname()+"\n";
        if (currentTicket.getDestination().equals(Destination.WELLINGTON)) {
            output.outputString1 += "Picton - Wellington";
        }
        else {
            output.outputString1 += "Wellington - Picton";
        }
        output.outputString1 += "\n--------------------------------------------------------------";
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");
        output.outputString2 = "Date booked: "+currentTicket.getDateBooked().format(dtf)+"\n"
                + "Travel date: "+currentTicket.getTravelDate().format(dtf)+"\n"
                + "Departure time: "+currentTicket.getDepartTime()+"\n"
                + "--------------------------------------------------------------\n";
        if (currentTicket.getMeal() == null) {
            output.outputString2 += "Meal: None\n";
        }
        else {
            output.outputString2 += "Meal: "+currentTicket.getMeal().getName()+"\n";
        }
        
        if (currentTicket.getDrink() == null) {
            output.outputString2 += "Drink: None\n\n";
        }
        else {
            output.outputString2 += "Drink: "+currentTicket.getDrink().getName()+"\n\n";
        }
        output.outputString2 += "Amount paid: $"+currentTicket.getAmountPaid()+"0";
        output.action = Output.VIEW_TICKET;
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Get a list of the user's tickets from the TICKETS table, display on the panel.
    public void viewTicketList() {
        output.action = Output.VIEW_TICKET_LIST;
        output.ticketList = ticketDB.getTicketList(currentUser);
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Sign the user out, notify the View to send the user back to the login screen.
    public void logOut() {
        currentUser = null;
        output.action = Output.LOG_OUT;
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Prompt the user to add a Pay account.
    public void managePayAccount() {
        if (currentUser.getPayAccount() == null) {
            output.action = Output.ADD_PAY_ACCOUNT;
        }
        else {
            // If the user already has a Pay account, prompt them to remove it.
            output.action = Output.REMOVE_PAY_ACCOUNT;
        }
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Remove the user's Pay account from the currentUser object and from the database.
    public void removePayAccount() {
        payDB.deletePayAcc(currentUser.getPayAccount());
        currentUser.setPayAccount(null);
        if (currentUser instanceof Member) {
            memberDB.updatePayAccount((Member) currentUser);
        }
        else {
            userDB.updatePayAccount(currentUser);
        }
        output.action = Output.REMOVE_PAY_ACCOUNT_SUCCESS;
        this.setChanged();
        this.notifyObservers(output);
    }
    
    // Convert user-selected date to LocalDate.
    private LocalDate toLocalDate(String date) {
        String[] splitDate = date.split("/");
        return LocalDate.of(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]));
    }
    
    // Convert user-selected time to LocalTime.
    private LocalTime toLocalTime(String time) {
        String[] splitTime = time.split(" ");
        int hour = Integer.parseInt(splitTime[0].split(":")[0]);
        int minute = Integer.parseInt(splitTime[0].split(":")[1]);
        
        if (splitTime[1].equals("PM")) {
            if (hour < 12) {
                hour += 12;
            }
        }
        return LocalTime.of(hour, minute);
    }
    
    // Confirm whether the user input is valid.
    public boolean validAccountDetails(String username, String fullname, String newPassword, String confirmPassword) {
        boolean isValid = false;
        
        if (userDB.containsUser(username)) { // Username already exists in database.
            output.action = Output.USERNAME_EXISTS;
        }
        else if (username.length() > 15 || username.length() < 5 || username.contains(" ")) {
            // Username must be 5-15 characters in length and must not contain spaces.
            output.action = Output.INVALID_USERNAME;
        }
        else if (fullname.length() < 5 || fullname.length() > 40) {
            // Full name must be 5-40 characters in length.
            output.action = Output.INVALID_NAME_LENGTH;
        }
        else if (newPassword.length() < 8 || newPassword.length() > 20 || newPassword.contains(" ")) {
            // Password must be 8-20 characters in length and must not contain spaces.
            output.action = Output.INVALID_NEW_PASSWORD;
        }
        else if (!newPassword.equals(confirmPassword)) {
            // Both password fields must be equal.
            output.action = Output.NEW_PASSWORD_MISMATCH;
        }
        else {
            // Check if full name only contains letters and spaces.
            isValid = true;
            for (char c: fullname.toCharArray()) {
                if ((c < 'A' && c != ' ') || c > 'z' || (c > 'Z' && c < 'a')) {
                    output.action = Output.INVALID_NAME;
                    isValid = false;
                }
            }
        }
        
        // Notify the observer.
        if (!isValid) {
            this.setChanged();
            this.notifyObservers(output);
        }
        return isValid;
    }
    
    // Check USERS and MEMBERS tables to validate credentials entered by user.
    public boolean validLoginCredentials(String username, String password) {
        if (userDB.containsUser(username)) {
            User user = userDB.loadUser(username);
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        else if (memberDB.containsMember(username)) {
            Member member = memberDB.loadMember(username);
            if (member.getPassword().equals(password)) {
                return true;
            }
        }
        output.action = Output.INVALID_LOGIN_CREDENTIALS;
        this.setChanged();
        this.notifyObservers(output);
        return false;
    }
    
    // After the user logs in, notify View to switch to the home JPanel.
    public void login(String username) {
        if (userDB.containsUser(username)) {
            currentUser = userDB.loadUser(username);
        }
        else {
            currentUser = memberDB.loadMember(username);
            revokeMembershipIfExpired();
        }
        deleteExpiredTickets();
        output.action = Output.LOG_IN_SUCCESS;
        output.outputString1 = currentUser.getUsername();
        this.setChanged();
        this.notifyObservers(output);
        
        if (!listsFilled) {
            createMealDrinkLists();
        }
    }
}
