package ticketsystem;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;

final public class Member extends User {
    public static final double MEMBER_DISCOUNT = 0.12;
    private int memberID;
    private LocalDate expiry;
    
    // Constructor for loading an existing member
    public Member(String username, String password, String fullname, PayAcc payAccount, HashSet<Ticket> bookings, int memberID, LocalDate expiry) {
        super(username, password, fullname, payAccount);
        this.setMemberID(memberID);
        this.setExpiry(expiry);
    }

    // Constructor for new members.
    // Assigns a new 9-digit ID and expiry date 1 year after system date.
    public Member(String username, String password, String fullname, PayAcc payAccount) {
        super(username, password, fullname, payAccount);
        this.setMemberID(generateID());
        this.setExpiry(LocalDate.now().plusYears(1));
    }
    
    // Generates a new, random 9-digit ID. 
    // Method uses recursion for when the new ID already exists in the file.
    private int generateID() {
        int newID = new Random().nextInt(100000000, 999999999);
        
        if (new UserIO().containsMemberID(newID)) {
            return generateID();
        }
        
        return newID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }
    
    @Override
    public String toString() {
        String out = super.toString()+"\n" // Print user details and member details
                + "Membership details: \n"
                + "-------------------\n"
                + "Membership ID: "+memberID+"\n"
                + "Expiry date: "+expiry.getDayOfMonth()+"/"+expiry.getMonthValue()+"/"+expiry.getYear();
        
        return out;
    }
    
}
