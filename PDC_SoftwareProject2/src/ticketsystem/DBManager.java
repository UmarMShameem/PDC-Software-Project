package ticketsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdcproj2";
    private static final String URL = "jdbc:derby://localhost:1527/BookingSystemDB";
    
    Connection connection;
    
    public DBManager() {
        establishConnection();
    }
    
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
}