package ticketsystem;

import java.util.Observable;

public class BookingAppModel extends Observable {
    DBManager userDB, memberDB, ticketDB, payDB, menuDB;
    
    public BookingAppModel() {
        userDB = new UserDB();
        memberDB = new MemberDB();
        ticketDB = new TicketDB();
        payDB = new PayAccDB();
        menuDB = new MenuDB();
    }
    
    public void createAccount() {
        this.setChanged();
        this.notifyObservers("Create Account");
    }
}
