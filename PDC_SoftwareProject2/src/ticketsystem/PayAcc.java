package ticketsystem;

public class PayAcc {
    private String email; // Must contain '@', otherwise prompt for input again.
    private String password;
    
    public PayAcc(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
}
