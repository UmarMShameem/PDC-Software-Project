package ticketsystem;

// Main class for the booking system.
public class BookingSystemMain {
    public static void main(String[] args) {
        BookingAppModel model = new BookingAppModel();
        BookingAppView view = new BookingAppView();
        view.setVisible(true);
        BookingAppController controller = new BookingAppController(model, view);
        model.addObserver(view);
    }
}
