package ticketsystem;

import java.util.Observable;

public class BookingAppModel extends Observable {
    UserDB userDB;
    MemberDB memberDB;
    TicketDB ticketDB;
    PayAccDB payDB;
    MenuDB menuDB;
    
    User currentUser;
    
    public BookingAppModel() {
        userDB = new UserDB();
        memberDB = new MemberDB();
        ticketDB = new TicketDB();
        payDB = new PayAccDB();
        menuDB = new MenuDB();
    }
    
    // Notify View to switch to the Create Account JPanel.
    public void createAccount() {
        this.setChanged();
        this.notifyObservers("Create Account");
    }
    
    public void confirmCreateAccount(String username, String fullname, String password) {
        currentUser = new User(username, password, fullname);
        userDB.saveUser(currentUser);
        this.setChanged();
        this.notifyObservers("Log in");
    }
    
    // Confirm whether the user input is valid.
    public boolean validAccountDetails(String username, String fullname, String newPassword, String confirmPassword) {
        if (userDB.containsUser(username)) { // Username already exists in database.
            this.setChanged();
            this.notifyObservers("Username already in use");
            return false;
        }
        else if (username.length() > 15 || username.length() < 5 || username.contains(" ")) {
            // Username must be 5-15 characters in length and must not contain spaces.
            this.setChanged();
            this.notifyObservers("Username requirements not met");
            return false;
        }
        else if (fullname.length() < 5 || fullname.length() > 40) {
            // Full name must be 5-40 characters in length.
            this.setChanged();
            this.notifyObservers("Full name length invalid");
            return false;
        }
        else if (newPassword.length() < 8 || newPassword.length() > 20 || newPassword.contains(" ")) {
            // Password must be 8-20 characters in length and must not contain spaces.
            this.setChanged();
            this.notifyObservers("Password requirements not met");
            return false;
        }
        else if (!newPassword.equals(confirmPassword)) {
            // Both password fields must be equal.
            this.setChanged();
            this.notifyObservers("Unequal password fields");
            return false;
        }
        else {
            // Check if full name only contains letters and spaces.
            for (char c: fullname.toCharArray()) {
                if ((c < 'A' && c != ' ') || c > 'z' || (c > 'Z' && c < 'a')) {
                    this.setChanged();
                    this.notifyObservers("Invalid characters in full name");
                    return false;
                }
            }
        }
        return true;
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
        this.setChanged();
        this.notifyObservers("Invalid login credentials");
        return false;
    }
    
    // After the user logs in, notify View to switch to the home JPanel.
    public void login(String username) {
        currentUser = userDB.loadUser(username);
        this.setChanged();
        this.notifyObservers("Log in");
    }
}
