package ticketsystem;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuDB extends DBManager {
    public MenuDB() {
        super();
        createTable();
    }
    
    public void addMealItems() {
        String mealInsert = "INSERT INTO MEALS VALUES ('Beef Pie', 'Pie filled with beef mince, cheese sauce and gravy.', 10.5), "
                + "('BBQ Chicken Pizza', 'Pizza topped with chicken, mozarella cheese, olives, red onion, BBQ sauce and capsicum.', 12.0), "
                + "('Fish Burger', 'Fish fillet with cheese, a hash brown, tartare sauce, and French Fries as a side.', 11.0)";
        try {
            statement.executeUpdate(mealInsert);
        } 
        catch (SQLException ex) {
            Logger.getLogger(MenuDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addDrinkItems() {
        String drinkInsert = "INSERT INTO DRINKS VALUES ('Water', '500ml bottled water.', 2.0), "
                + "('Apple Juice', '500ml Keri apple juice.', 3.0), "
                + "('Tea', 'Hot cup of English Breakfast Tea.', 3.0), "
                + "('Coffee', 'Hot cup of Espresso coffee, made with freshly ground coffee beans.', 4.5)";
        try {
            statement.executeUpdate(drinkInsert);
        } 
        catch (SQLException ex) {
            Logger.getLogger(MenuDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Meal getMeal(String name) {
        String sqlQuery = "SELECT * FROM MEALS WHERE MEAL_NAME='"+name+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return new Meal(name, rs.getString("MEAL_DESC"), rs.getDouble("PRICE"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(MenuDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Drink getDrink(String name) {
        String sqlQuery = "SELECT * FROM DRINKS WHERE DRINK_NAME='"+name+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return new Drink(name, rs.getString("DRINK_DESC"), rs.getDouble("PRICE"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(MenuDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void createTable() {
        try {
            if (!this.containsTable("MEALS")) {
                String mealCreate = "CREATE TABLE MEALS(MEAL_NAME VARCHAR(20), MEAL_DESC VARCHAR(100), PRICE DOUBLE)";
                statement.executeUpdate(mealCreate);
                addMealItems();
            }
            if (!this.containsTable("DRINKS")) {
                String drinkCreate = "CREATE TABLE DRINKS(DRINK_NAME VARCHAR(20), DRINK_DESC VARCHAR(100), PRICE DOUBLE)";
                statement.executeUpdate(drinkCreate);
                addDrinkItems();
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(MenuDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
