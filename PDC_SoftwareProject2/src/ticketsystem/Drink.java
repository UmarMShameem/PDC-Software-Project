package ticketsystem;

import java.text.DecimalFormat;

public class Drink {
    private String name;
    private String desc;
    private double price;
    
    public Drink(String name, String desc, double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }
    
    @Override // Print the drink name, description and price.
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        String out = name+"\n"
                + "\n"
                + ""+desc+"\n"
                + "Price: $"+df.format(price)+"\n"
                + "--";
        
        return out;
    }
    
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrice() {
        return price;
    }
}
