package ticketsystem;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuDB extends DBManager {
    public MenuDB() {
        super();
        createTable();
    }
    
    @Override
    public void createTable() {
        try {
            if (!this.containsTable("MEALS")) {
                String mealCreate = "CREATE TABLE MEALS(MEAL_NAME VARCHAR(20), MEAL_DESC VARCHAR(50), PRICE DOUBLE)";
                statement.addBatch(mealCreate);
            }
            if (!this.containsTable("DRINKS")) {
                String drinkCreate = "CREATE TABLE DRINKS(DRINK_NAME VARCHAR(20), DRINK_DESC VARCHAR(50), PRICE DOUBLE)";
                statement.addBatch(drinkCreate);
            }
            statement.executeBatch();
        } 
        catch (SQLException ex) {
            Logger.getLogger(MenuDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
