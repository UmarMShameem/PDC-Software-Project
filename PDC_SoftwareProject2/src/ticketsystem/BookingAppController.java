package ticketsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BookingAppController implements ActionListener, ListSelectionListener  {
    public BookingAppModel model;
    public BookingAppView view;
    
    public BookingAppController(BookingAppModel model, BookingAppView view) {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this);
        this.view.addListSelectionListener(this);
    }
    
    @Override // Handles user interactions with the View.
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Retrieve text from the button which was pressed.
        switch (command) {
            case "Create Account":
                this.model.createAccount();
                break;
            case "Login":
                // Password is retrieved from password field as a char[], so read characters into a String.
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
                // Get user information, check if valid, create account
                String newUsername = this.view.jtfUName.getText();
                String fullName = this.view.jtfFName.getText();
                
                // Convert newPassword and confirmPassword fields to strings.
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
            case "Log out":
                // Send the user back to the login screen.
                this.model.logOut();
                break;
            case "Account Settings":
                this.model.viewAccountSettings();
                break;
            case "Back to main menu":
                this.model.backToHome();
                break;
            case "Change password":
                String currentPassword = "";
                String newUserPassword = "";
                String confirmNewPassword = "";
                for (char c: this.view.jpfSettingsCPass.getPassword()) {
                    currentPassword += c;
                }
                for (char c: this.view.jpfSettingsNPass.getPassword()) {
                    newUserPassword += c;
                }
                for (char c: this.view.jpfSettingsCNPass.getPassword()) {
                    confirmNewPassword += c;
                }
                this.model.changePassword(currentPassword, newUserPassword, confirmNewPassword);
                break;
            case "Add Pay account":
                String paPassword = "";
                for (char c: this.view.jpfPAPassword.getPassword()) {
                    paPassword += c;
                }
                this.model.addPayAccount(this.view.jtfPAEmail.getText(), paPassword);
                break;
            case "Add/Remove Payment Method":
                this.model.managePayAccount();
                break;
            case "Yes":
                this.model.removePayAccount();
                break;
            case "No":
                this.model.viewAccountSettings();
                break;
            case "Membership":
                this.model.viewMembership();
                break;
            case "Become a member":
                this.model.registerMember();
                break;
            case "Make a Booking":
                this.model.createBooking();
                break;
            case "Cancel":
                this.model.backToHome();
                break;
            case "Book Ferry":
                this.model.validateBooking((String)this.view.jcbJourney.getSelectedItem(), 
                        (String) this.view.jcbTravelDate.getSelectedItem(), 
                        (String) this.view.jcbDepartTime.getSelectedItem(), 
                        this.view.mealList.getSelectedValue(), 
                        this.view.drinkList.getSelectedValue());
                break;
            case "Confirm Booking":
                String[] bookingDetails = this.view.jtaBookingDetails.getText().split("\\$");
                double amountPaid = Double.parseDouble(bookingDetails[bookingDetails.length - 1]);
                this.model.confirmBooking((String)this.view.jcbJourney.getSelectedItem(), 
                        (String) this.view.jcbTravelDate.getSelectedItem(), 
                        (String) this.view.jcbDepartTime.getSelectedItem(), 
                        this.view.mealList.getSelectedValue(), 
                        this.view.drinkList.getSelectedValue(), 
                        amountPaid);
                break;
            case "View Bookings":
                this.model.viewTicketList();
                break;
            case "View Ticket":
                String selectedTicket = this.view.ticketList.getSelectedValue();
                if (selectedTicket != null) {
                    this.model.viewTicket(Integer.parseInt(selectedTicket.split(":")[0]));
                }
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList currentList = (JList) e.getSource();
        String selectedItem = (String) currentList.getSelectedValue();
        if (selectedItem != null) {
            this.model.printItemInfo(selectedItem);
        }
    }
}
