package ticketsystem;

// Main payment method for the booking system, similar to PayPal.
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
