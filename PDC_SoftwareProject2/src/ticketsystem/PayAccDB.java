package ticketsystem;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayAccDB extends DBManager {
    public PayAccDB() {
        super();
        createTable();
    }

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
            String payAccCreate = "CREATE TABLE PAY_ACCOUNTS(PA_EMAIL VARCHAR(20), PA_PASSWORD VARCHAR(20))";
            try {
                statement.executeUpdate(payAccCreate);
            } 
            catch (SQLException ex) {
                Logger.getLogger(PayAccDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
