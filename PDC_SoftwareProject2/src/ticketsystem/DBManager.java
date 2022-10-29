package ticketsystem;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DBManager {
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdcproj2";
//    private static final String URL = "jdbc:derby://localhost:1527/BookingSystemDB";
    private static final String URL = "jdbc:derby:BookingSystemDB; create=true";
    
    Connection connection;
    Statement statement;
    
    public DBManager() {
        establishConnection();
        statement = getStatement();
    }
    
    // Establish connection to BookingSystemDB.
    public void establishConnection() {
        if (this.connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } 
            catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } 
            catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Returns Statement object from this connection. Statement is used to execute queries and updates.
    public final Statement getStatement() {
        try {
            return connection.createStatement();
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Return true if BookingSystemDB contains a table matching tableName, false otherwise.
    public final boolean containsTable(String tableName) {
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, "PDC", tableName, null);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Sub-classes will override createTable method to create either User, Member, PayAcc or Ticket tables.
    public abstract void createTable();
}