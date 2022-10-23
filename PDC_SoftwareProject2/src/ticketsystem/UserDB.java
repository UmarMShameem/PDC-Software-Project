package ticketsystem;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB extends DBManager {
    private Statement statement;
    
    public UserDB() {
        super();
        statement = getStatement();
        createTable();
    }

    @Override
    public final void createTable() {
        if (!this.containsTable("USERS")) {
            String userCreate = "CREATE TABLE USERS (USERNAME VARCHAR(20), PASSWORD VARCHAR(20), FULLNAME VARCHAR(20), PA_EMAIL VARCHAR(20), PA_PASSWORD VARCHAR(20))";
            try {
                statement.executeUpdate(userCreate);
            } 
            catch (SQLException ex) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
//    public static void main(String[] args) {
//        UserDB userDB = new UserDB();
//    }
}
