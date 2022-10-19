package ticketsystem;

import java.util.HashMap;

public class Menu {
    HashMap<Integer, Meal> meals;
    HashMap<Integer, Drink> drinks;
    
    // Constructor populates both HashMaps with meal and drink object data.
    public Menu() {
        meals = new HashMap<Integer, Meal>();
        meals.put(1, new Meal("Beef pie", "Beef pie filled with cheese sauce and gravy.", 10.5));
        meals.put(2, new Meal("BBQ Chicken Pizza", "Chicken pizza topped with mozarella cheese, olives, red onion, BBQ sauce and capsicum.", 12.0));
        meals.put(3, new Meal("Fish Burger", "Fish fillet with cheese, a hash brown, tartare sauce, and French Fries as a side.", 11.0));
        meals.put(4, new Meal("No meal", "", 0.0));
        
        drinks = new HashMap<Integer, Drink>();
        drinks.put(1, new Drink("Water", "500ml bottled water.", 2.0));
        drinks.put(2, new Drink("Apple Juice", "500ml Keri Apple Juice.", 2.5));
        drinks.put(3, new Drink("Tea", "English Breakfast Tea, choice of milk or no milk.", 2.5));
        drinks.put(4, new Drink("Coffee", "Choice of latte or cappucino.", 4.5));
        drinks.put(5, new Drink("No drink", "", 0.0));
    }
}
