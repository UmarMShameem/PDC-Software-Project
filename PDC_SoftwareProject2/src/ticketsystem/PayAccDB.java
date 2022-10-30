package ticketsystem;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PayAccDB extends DBManager {
    public PayAccDB() {
        super();
        createTable();
    }

    // Return true if an entry in the PAY_ACCOUNTS table contains the input email.
    public boolean containsPayAcc(String email) {
        String sqlQuery = "SELECT * FROM PAY_ACCOUNTS WHERE PA_EMAIL='"+email+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(PayAccDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Delete entry from the PAY_ACCOUNTS table where the email matches the email of the input PayAcc object.
    public void deletePayAcc(PayAcc payAccount) {
        String payAccDelete = "DELETE FROM PAY_ACCOUNTS WHERE PA_EMAIL = '"+payAccount.getEmail()+"'";
        try {
            statement.executeUpdate(payAccDelete);
        } 
        catch (SQLException ex) {
            Logger.getLogger(PayAccDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Query the PAY_ACCOUNTS table for an entry matching the input email, return new PayAcc object.
    public PayAcc loadPayAcc(String email) {
        String sqlQuery = "SELECT * FROM PAY_ACCOUNTS WHERE PA_EMAIL='"+email+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return new PayAcc(rs.getString("PA_EMAIL"), rs.getString("PA_PASSWORD"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(PayAccDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Insert input PayAcc object fields into the PAY_ACCOUNTS table.
    public void savePayAcc(PayAcc account) {
        String payAccInsert = "INSERT INTO PAY_ACCOUNTS VALUES ('"+account.getEmail()+"', '"+account.getPassword()+"')";
        try {
            statement.executeUpdate(payAccInsert);
        } 
        catch (SQLException ex) {
            Logger.getLogger(PayAccDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void createTable() {
        if (!this.containsTable("PAY_ACCOUNTS")) {
            String payAccCreate = "CREATE TABLE PAY_ACCOUNTS(PA_EMAIL VARCHAR(40), PA_PASSWORD VARCHAR(20))";
            try {
                statement.executeUpdate(payAccCreate);
            } 
            catch (SQLException ex) {
                Logger.getLogger(PayAccDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
