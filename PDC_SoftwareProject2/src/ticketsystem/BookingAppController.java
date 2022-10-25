package ticketsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingAppController implements ActionListener  {
    public BookingAppModel model;
    public BookingAppView view;
    
    public BookingAppController(BookingAppModel model, BookingAppView view) {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this);
    }
    
    @Override // Handles user interactions with the View.
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Retrieve text from the button which was pressed.
        switch (command) {
            case "Create Account":
                this.model.createAccount();
                break;
            case "Login":
                // Password is retrieved from passowrd field as a char[], so read characters into a String.
                String username = this.view.jtfUsername.getText();
                String password = "";
                for (char c: this.view.jtfPassword.getPassword()) {
                    password += c;
                }
                // Check if the login credentials entered are valid, then login.
                if (this.model.validLoginCredentials(username, password)) {
                    this.model.login(username);
                }
                break;
            case "Confirm & Create Account":
                String newUsername = this.view.jtfUName.getText();
                String fullName = this.view.jtfFName.getText();
                
                String newPassword = "";
                for (char c: this.view.jtfNewPass.getPassword()) {
                    newPassword += c;
                }
                
                String confirmPassword = "";
                for (char c: this.view.jtfConfirmPass.getPassword()) {
                    confirmPassword += c;
                }
                
                if (this.model.validAccountDetails(newUsername, fullName, newPassword, confirmPassword)) {
                    this.model.confirmCreateAccount(newUsername, fullName, newPassword);
                }
                break;
        }
    }
    
}
