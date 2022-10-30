/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ticketsystem;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author umars
 */
public class BookingAppModelTest {
    BookingAppModel model;
    
    public BookingAppModelTest() {
        model = new BookingAppModel();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createAccountTest() {
        // Call Model confirmCreateAccount() method, which is called when the user clicks 
        // "Confirm & Create Account" after adding their new account details. This should 
        // set the currentUser instance to a new User object with the input strings and 
        // add the details to the USERS table.
        model.confirmCreateAccount("username_4", "password1234", "New User Name");
        System.out.println("Created new user account: "+model.currentUser.getUsername());
        
        boolean registeredUser = false;
        
        if (model.currentUser.getUsername().equals("username_4") && model.userDB.containsUser("username_4")) {
            registeredUser = true;
            System.out.println("Found "+model.currentUser.getUsername()+" in the USERS table, now deleting...");
            model.userDB.deleteUser(model.currentUser);
            System.out.println("createAccountTest() passed.\n");
        }
        
        assertTrue(registeredUser);
        
    }
    
    @Test
    public void addTicketTest() {
        // Call Model confirmBooking() method, which, when called, takes the user's selected booking options and
        // creates a Ticket object to store in the TICKETS table.
        model.login("junit4");
        System.out.println("Logged in as junit4.");
        
        System.out.println("Adding a booking...");
        model.confirmBooking("Wellington - Picton", "12/3/2023", "8:30 AM", "No meal", "No drink", 60.0);
        System.out.println("Booking added.");
        
        Ticket newTicket = model.ticketDB.getTicketList(model.currentUser).get(0);
        boolean correctDestination = false;
        if (newTicket.getDestination().equals(Destination.PICTON)) {
            System.out.println("Correct destination on ticket.");
            correctDestination = true;
        }
        else {
            System.out.println("Correct destination not on ticket.");
        }
        
        boolean correctDate = false;
        if (newTicket.getTravelDate().equals(LocalDate.of(2023, 3, 12))) {
            System.out.println("Correct travel date on ticket.");
            correctDate = true;
        }
        else {
            System.out.println("Correct travel date not on ticket.");
        }
        
        boolean correctTime = false;
        if (newTicket.getDepartTime().equals(LocalTime.of(8, 30))) {
            System.out.println("Correct departure time on ticket.");
            correctTime = true;
        }
        else {
            System.out.println("Correct departure time not on ticket.");
        }
        
        boolean correctAmountPaid = false;
        if (newTicket.getAmountPaid() == 60.0) {
            System.out.println("Correct amount paid on ticket.");
            correctAmountPaid = true;
        }
        else {
            System.out.println("Correct amount paid not on ticket.");
        }
        
        boolean testPassed = correctDestination && correctDate && correctTime && correctAmountPaid;
        if (testPassed) {
            System.out.println("addTicketTest() passed.");
            model.ticketDB.deleteTicket(newTicket);
        }
        else {
            System.out.println("addTicketTest() failed.");
        }
        assert(testPassed);
    }
    
    @Test
    public void changePasswordTest() {
        // Call Model changePassword() method, which is called when the user enters their current password and
        // their new password twice, and clicks "Change password". This method should change the password of 
        // the currentUser instance and update the password field for the current user in the USERS table.
        model.login("junit4");
        System.out.println("Logged in as junit4.");
        
        System.out.println("Changing password...");
        String newPassword = "junittestpa55";
        model.changePassword("somepassword12", newPassword, newPassword);
        
        boolean currentUserUpdated = false;
        if (model.currentUser.getPassword().equals(newPassword)) {
            currentUserUpdated = true;
            System.out.println("currentUser instance updated correctly.");
        }
        else {
            System.out.println("currentUser instance not updated correctly.");
        }
        
        boolean usersTableUpdated = false;
        if (model.userDB.loadUser("junit4").getPassword().equals(newPassword)) {
            usersTableUpdated = true;
            System.out.println("USERS table updated correctly.");
        }
        else {
            System.out.println("USERS table not updated correctly.");
        }
        
        boolean testPassed = currentUserUpdated && usersTableUpdated;
        if (testPassed) {
            System.out.println("changePasswordTest() passed.\n");
            model.changePassword(newPassword, "somepassword12", "somepassword12");
        }
        else {
            System.out.println("changePasswordTest() failed.\n");
        }
        assertTrue(testPassed);
    }
    
    @Test
    public void addPayAccountTest() {
        // Call Model addPayAccount() method, which is called when the user enters an email address and password
        // and clicks on "Add Pay account". This should add the details to the PAY_ACCOUNTS table, create a new
        // PayAcc object, set the user's Pay account to be the new object, and update the user's PA_EMAIL field
        // in the USERS table to be the new email.
        model.login("junit4");
        System.out.println("Logged in as junit4.");
        
        String email = "test@aut.ac.nz";
        model.addPayAccount(email, "testing123");
        System.out.println("Added Pay account "+email);
        
        boolean currentUserUpdated = false;
        if (model.currentUser.getPayAccount().getEmail().equals(email)) {
            System.out.println("currentUser instance updated correctly.");
            currentUserUpdated = true;
        }
        else {
            System.out.println("currentUser instance not updated correctly.");
        }
        
        boolean usersTableUpdated = false;
        if (model.userDB.loadUser("junit4").getPayAccount().getEmail().equals(email)) {
            System.out.println("USERS table updated correctly.");
            usersTableUpdated = true;
        }
        else {
            System.out.println("USERS table not updated correctly.");
        }
        
        boolean payAccTableUpdated = false;
        if (model.payDB.containsPayAcc(email)) {
            System.out.println("PAY_ACCOUNTS table updated correctly.");
            payAccTableUpdated = true;
        }
        else {
            System.out.println("PAY_ACCOUNTS table not updated correctly.");
        }
        
        boolean testPassed = currentUserUpdated && usersTableUpdated && payAccTableUpdated;
        if (testPassed) {
            System.out.println("addPayAccountTest() passed.\n");
            model.removePayAccount();
        }
        else {
            System.out.println("addPayAccountTest() failed.\n");
        }
        assertTrue(testPassed);
    }
    
    @Test
    public void membershipExpiryTest() {
        // Add a new member to the database with a membership expiry date earlier than current date.
        // Once logging in as this member, their details should move from the MEMBERS table to the 
        // USERS table.
        Member newMember = new Member(new User("test_name", "testpassword1", "New Member Name"));
        newMember.setExpiry(LocalDate.now().minusMonths(2));
        model.memberDB.saveMember(newMember);
        System.out.println("Added new member "+newMember.getUsername()+" with expiry date "+newMember.getExpiry());
        
        boolean isMember = true;
        model.login(newMember.getUsername());
        System.out.println("Logged in as: "+model.currentUser.getUsername());
        
        if (!model.memberDB.containsMember(newMember.getUsername()) && model.userDB.containsUser(newMember.getUsername()) && model.currentUser instanceof User) {
            isMember = false;
            System.out.println("Found "+newMember.getUsername()+" in the USERS table, now deleting...");
            model.userDB.deleteUser(newMember);
            System.out.println("membershipExpiryTest() passed.\n");
        }
        else if (model.memberDB.containsMember(newMember.getUsername())) {
            model.memberDB.deleteMember(newMember);
            System.out.println("membershipExpiryTest() failed.\n");
        }
        
        assertFalse(isMember);
    }
}
