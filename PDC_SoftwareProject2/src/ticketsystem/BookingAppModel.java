package ticketsystem;

import java.util.Observable;

public class BookingAppModel extends Observable {
    UserDB userDB;
    MemberDB memberDB;
    TicketDB ticketDB;
    PayAccDB payDB;
    MenuDB menuDB;
    
    User currentUser;
    Output output;
    
    public BookingAppModel() {
        userDB = new UserDB();
        memberDB = new MemberDB();
        ticketDB = new TicketDB();
        payDB = new PayAccDB();
        menuDB = new MenuDB();
        
        output = new Output();
    }

    public void backToHome() {
        output.action = Output.BACK_TO_HOME;
        this.setChanged();
        this.notifyObservers(output);
    }

    // Notify View to switch to the Create Account JPanel.
    public void createAccount() {
        output.action = Output.CREATE_ACCOUNT;
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
    
    public void closeDBConnections() {
        userDB.closeConnection();
        memberDB.closeConnection();
        ticketDB.closeConnection();
        payDB.closeConnection();
        menuDB.closeConnection();
    }
    
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
    
    // Sign the user out, notify the View to send the user back to the login screen.
    public void logOut() {
        currentUser = null;
        output.action = Output.LOG_OUT;
        this.setChanged();
        this.notifyObservers(output);
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
        }
        output.action = Output.LOG_IN_SUCCESS;
        output.outputString1 = currentUser.getUsername();
        this.setChanged();
        this.notifyObservers(output);
    }
}
