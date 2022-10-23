package ticketsystem;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public final class Ticket {
    private int ticketNo;
    private double amountPaid;
    private Meal meal;
    private Drink drink;
    private LocalDate dateBooked;
    private LocalDate travelDate;
    private LocalTime departTime;
    private Destination destination;
    private String username;
    
    // Constructor used when loading an existing ticket from file.
    public Ticket(int ticketNo, double amountPaid, Meal meal, Drink drink, LocalDate dateBooked, LocalDate travelDate, LocalTime departTime, Destination destination, String username) {
        this.setTicketNo(ticketNo);
        this.setAmountPaid(amountPaid);
        this.setMeal(meal);
        this.setDrink(drink);
        this.setDateBooked(dateBooked);
        this.setTravelDate(travelDate);
        this.setDepartTime(departTime);
        this.setDestination(destination);
        this.setUser(username);
    }
    
    // Constructor used when booking a new ticket.
    // Generates a new unique ticket number and sets date booked to current date.
    public Ticket(double amountPaid, Meal meal, Drink drink, LocalDate travelDate, LocalTime departTime, Destination destination, String username) {
        this.setTicketNo(generateTicketNo());
        this.setAmountPaid(amountPaid);
        this.setMeal(meal);
        this.setDrink(drink);
        this.setDateBooked(LocalDate.now());
        this.setTravelDate(travelDate);
        this.setDepartTime(departTime);
        this.setDestination(destination);
        this.setUser(username);
    }
    
    // Generates unique 6-digit ticket number.
    // Method recursively calls itself if the generated number is already in use.
    public int generateTicketNo() {
        int newTicketNo = new Random().nextInt(100000, 999999);
        
//        if (new TicketIO().containsTicket(newTicketNo)) {
//            return generateTicketNo();
//        }
        // TODO: Check Tickets table if generated ticket number already exists.
        
        return newTicketNo;
    }
    
    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }
    
    @Override
    public String toString() { // Print the ticket.
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);
        String out = "============================\n"
                + "|| Ticket number: "+ticketNo+"\n"
                + "|| Amount paid: $"+df.format(amountPaid)+"\n"
                + "|| Meal: "+meal.getName()+"\n"
                + "|| Drink: "+drink.getName()+"\n"
                + "|| Date booked: "+dateBooked.getDayOfMonth()+"/"+dateBooked.getMonthValue()+"/"+dateBooked.getYear()+"\n"
                + "|| Travel date: "+travelDate.getDayOfMonth()+"/"+travelDate.getMonthValue()+"/"+travelDate.getYear()+"\n"
                + "|| Departure time: "+departTime+"\n"
                + "|| Destination: "+destination+"\n"
                + "============================";
        
        return out;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (o == null) {
            return false;
        }
        
        if (!(o instanceof Ticket)) {
            return false;
        }
        
        Ticket ticket = (Ticket) o;
        return (this.ticketNo == ticket.ticketNo);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 180 * hashCode + this.ticketNo;
        return hashCode;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public LocalTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departTime = departTime;
    }
}
