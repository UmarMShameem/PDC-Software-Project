package ticketsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingAppController implements ActionListener  {
    public BookingAppModel model;
    public BookingAppView view;
    
    public BookingAppController(BookingAppModel model, BookingAppView view) {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Create Account":
                this.model.createAccount();
                break;
            case "":
                break;
        }
    }
    
}
