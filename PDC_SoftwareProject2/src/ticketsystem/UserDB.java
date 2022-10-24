package ticketsystem;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UserDB extends DBManager {
    public UserDB() {
        super();
        createTable();
    }
    
    public boolean containsUser(String username) {
        String sqlQuery = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

// Finds a row in the USERS table matching username, returns User object with data from that row.
    public User loadUser(String username) {
        String sqlQuery = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                // If PA_EMAIL is empty, return a User object without an associated PayAcc object.
                if (rs.getString("PA_EMAIL") == null) {
                    return new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FULLNAME"));
                }
                else {
                    return new User(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FULLNAME"), new PayAccDB().loadPayAcc(rs.getString("PA_EMAIL")));
                }
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Save user data into the USERS table.
    public void saveUser(User user) {
        String userInsert = "";
        // Have to insert null value into PA_EMAIL column if User has no PayAcc object associated with it.
        if (user.getPayAccount() == null) {
            userInsert = "INSERT INTO USERS VALUES ('"
                +user.getUsername()+"','"
                +user.getPassword()+"','"
                +user.getFullname()+"', null)";
        }
        else {
            userInsert = "INSERT INTO USERS VALUES ('"
                +user.getUsername()+"','"
                +user.getPassword()+"','"
                +user.getFullname()+"','"
                +user.getPayAccount().getEmail()+"')";
        }
        
        try {
            statement.executeUpdate(userInsert);
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createTable() {
        // Creatte user table if it does not exist in the database.
        if (!this.containsTable("USERS")) {
            String userCreate = "CREATE TABLE USERS (USERNAME VARCHAR(20), PASSWORD VARCHAR(20), FULLNAME VARCHAR(30), PA_EMAIL VARCHAR(20))";
            try {
                statement.executeUpdate(userCreate);
            } 
            catch (SQLException ex) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
