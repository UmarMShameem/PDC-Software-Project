package ticketsystem;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDB extends DBManager {
    public TicketDB() {
        super();
        createTable();
    }

    public boolean containsTicketNo(int ticketNo) {
        String sqlQuery = "SELECT * FROM TICKETS WHERE TICKET_NO="+ticketNo;
        ResultSet rs;
        try {
            rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Ticket loadTicket(int ticketNo) {
        String sqlQuery = "SELECT * FROM TICKETS WHERE TICKET_NO="+ticketNo;
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                Destination destination = Destination.WELLINGTON;
                if (rs.getString("DESTINATION").equals("PICTON")) {
                    destination = Destination.PICTON;
                }
                return new Ticket(rs.getInt("TICKET_NO"), rs.getDouble("AMOUNT_PAID"), new Meal(rs.getString("MEAL"), "", 0.0), new Drink(rs.getString("DRINK"), "", 0.0), rs.getDate("DATE_BOOKED").toLocalDate(), rs.getDate("TRAVEL_DATE").toLocalDate(), rs.getTime("DEPART_TIME").toLocalTime(), destination, rs.getString("USERNAME"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void saveTicket(Ticket ticket) {
        String ticketInsert = "INSERT INTO TICKETS VALUES ("
                +ticket.getTicketNo()+", "
                +ticket.getAmountPaid()+", '"
                +ticket.getMeal().getName()+"', '"
                +ticket.getDrink().getName()+"', '"
                +Date.valueOf(ticket.getDateBooked())+"', '"
                +Date.valueOf(ticket.getTravelDate())+"', '"
                +Time.valueOf(ticket.getDepartTime())+"', '"
                +ticket.getDestination().toString()+"', '"
                +ticket.getUser()+"')";
        try {
            statement.executeUpdate(ticketInsert);
        } 
        catch (SQLException ex) {
            Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void createTable() {
        if (!this.containsTable("TICKETS")) {
            String ticketCreate = "CREATE TABLE TICKETS "
                    + "(TICKET_NO INT, "
                    + "AMOUNT_PAID DOUBLE, "
                    + "MEAL VARCHAR(20), "
                    + "DRINK VARCHAR(20), "
                    + "DATE_BOOKED DATE, "
                    + "TRAVEL_DATE DATE, "
                    + "DEPART_TIME TIME, "
                    + "DESTINATION VARCHAR(10), "
                    + "USERNAME VARCHAR(20))";
            try {
                statement.executeUpdate(ticketCreate);
            } 
            catch (SQLException ex) {
                Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
