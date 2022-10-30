package ticketsystem;

public class User {
    private String username;
    private String password;
    private String fullname;
    private PayAcc payAccount;
    
    // Constructor for creating a new User
    public User(String username, String password, String fullname) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
    }
    
    // Constructor for loading an existing User
    public User(String username, String password, String fullname, PayAcc payAccount) {
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
        this.setPayAccount(payAccount);
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

    public PayAcc getPayAccount() {
        return payAccount;
    }

    final public void setPayAccount(PayAcc payAccount) {
        this.payAccount = payAccount;
    }
    
}
