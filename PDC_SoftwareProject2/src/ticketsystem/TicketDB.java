package ticketsystem;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    
    public void deleteTicket(Ticket ticket) {
        String ticketDelete = "DELETE FROM TICKETS WHERE TICKET_NO="+ticket.getTicketNo();
        try {
            statement.executeUpdate(ticketDelete);
        } 
        catch (SQLException ex) {
            Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Ticket> getTicketList(User user) {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM TICKETS WHERE USERNAME='"+user.getUsername();
        
        MenuDB menuDB = new MenuDB();
        
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()) {
                Destination destination = Destination.WELLINGTON;
                if (rs.getString("DESTINATION").equals("PICTON")) {
                    destination = Destination.PICTON;
                }
                ticketList.add(new Ticket(rs.getInt("TICKET_NO"), 
                        rs.getDouble("AMOUNT_PAID"), 
                        menuDB.getMeal(rs.getString("MEAL")), 
                        menuDB.getDrink(rs.getString("DRINK")), 
                        rs.getDate("DATE_BOOKED").toLocalDate(), 
                        rs.getDate("TRAVEL_DATE").toLocalDate(), 
                        rs.getTime("DEPART_TIME").toLocalTime(), 
                        destination, 
                        rs.getString("USERNAME")));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuDB.closeConnection();
        return ticketList;
    }
    
    public Ticket loadTicket(int ticketNo) {
        String sqlQuery = "SELECT * FROM TICKETS WHERE TICKET_NO="+ticketNo;
        MenuDB menuDB = new MenuDB();
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                Destination destination = Destination.WELLINGTON;
                if (rs.getString("DESTINATION").equals("PICTON")) {
                    destination = Destination.PICTON;
                }
                return new Ticket(rs.getInt("TICKET_NO"), 
                        rs.getDouble("AMOUNT_PAID"), 
                        menuDB.getMeal(rs.getString("MEAL")), 
                        menuDB.getDrink(rs.getString("DRINK")), 
                        rs.getDate("DATE_BOOKED").toLocalDate(), 
                        rs.getDate("TRAVEL_DATE").toLocalDate(), 
                        rs.getTime("DEPART_TIME").toLocalTime(), 
                        destination, 
                        rs.getString("USERNAME"));
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
    
    public boolean userHasBooking(User user, LocalDate travelDate, LocalTime departTime) {
        String sqlQuery = "SELECT * FROM TICKETS WHERE USERNAME='"+user.getUsername()
                +"' AND TRAVEL_DATE='"+Date.valueOf(travelDate)
                +"' AND DEPART_TIME='"+Time.valueOf(departTime)+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean userHasBooking(User user, LocalDate travelDate, String destination) {
        String sqlQuery = "SELECT * FROM TICKETS WHERE USERNAME='"+user.getUsername()
                +"' AND TRAVEL_DATE='"+Date.valueOf(travelDate)
                +"' AND DESTINATION='"+destination.toUpperCase()+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(TicketDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
