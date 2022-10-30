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
    
    // Return true if the USERS table contains the input username, false otherwise.
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
    
    // Delete entry from USERS table containing the user's username.
    public void deleteUser(User user) {
        String userDelete = "DELETE FROM USERS WHERE USERNAME='"+user.getUsername()+"'";
        try {
            statement.executeUpdate(userDelete);
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Find table entry containing the user's username, set PASSWORD field to the new password.
    public void changePassword(User user, String newPassword) {
        String passwordUpdate = "UPDATE USERS SET PASSWORD = '"+newPassword+"' WHERE USERNAME = '"+user.getUsername()+"'";
        try {
            statement.executeUpdate(passwordUpdate);
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Updates a user's PA_EMAIL field upon adding a new Pay Account to the database.
    // Method also updates PA_EMAIL to be null when the Pay Account has been removed.
    public void updatePayAccount(User user) {
        String paUpdate = "";
        if (user.getPayAccount() == null) {
            paUpdate = "UPDATE USERS SET PA_EMAIL = null WHERE USERNAME = '"+user.getUsername()+"'";
        }
        else {
            paUpdate = "UPDATE USERS SET PA_EMAIL = '"+user.getPayAccount().getEmail()+"' WHERE USERNAME = '"+user.getUsername()+"'";
        }
        try {
            statement.executeUpdate(paUpdate);
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Finds a row in the USERS table matching username, returns User object with data from that row.
    public User loadUser(String username) {
        String sqlQuery = "SELECT * FROM USERS WHERE USERNAME='"+username+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                // If PA_EMAIL is null, return a User object without an associated PayAcc object.
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
            String userCreate = "CREATE TABLE USERS (USERNAME VARCHAR(20), PASSWORD VARCHAR(20), FULLNAME VARCHAR(40), PA_EMAIL VARCHAR(20))";
            try {
                statement.executeUpdate(userCreate);
            } 
            catch (SQLException ex) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
