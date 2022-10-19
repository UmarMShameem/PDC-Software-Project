package ticketsystem;

import java.util.HashSet;

public class User {
    private String username;
    private String password;
    private String fullname;
    private PayAcc payAccount;
    private HashSet<Ticket> bookings;
    
    // Constructor for creating a new User
    public User(String username, String password, String fullname) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
        this.setBookings(new HashSet<Ticket>());
    }
    
    // Constructor for loading an existing User
    public User(String username, String password, String fullname, PayAcc payAccount, HashSet<Ticket> bookings) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
        this.setPayAccount(payAccount);
        this.setBookings(bookings);
    }
    
    public String getUsername() {
        return username;
    }

    final public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    final public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    final public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public HashSet<Ticket> getBookings() {
        return bookings;
    }
    
    final public void setBookings(HashSet<Ticket> bookings) {
        this.bookings = bookings;
    }

    public PayAcc getPayAccount() {
        return payAccount;
    }

    final public void setPayAccount(PayAcc payAccount) {
        this.payAccount = payAccount;
    }
    
}
