package ticketsystem;

import java.util.ArrayList;

public class Output {
    public static final int ADD_PAY_ACCOUNT = 0;
    public static final int ADD_PAY_ACCOUNT_SUCCESS = 1;
    public static final int BACK_TO_HOME = 2;
    public static final int BOOKING_ERROR = 3;
    public static final int CHANGE_PASSWORD_SUCCESS = 4;
    public static final int CREATE_ACCOUNT = 5; 
    public static final int CREATE_ACCOUNT_SUCCESS = 6;
    public static final int CREATE_BOOKING = 7;
    public static final int CONFIRM_BOOKING_PROMPT = 8;
    public static final int CONFIRM_BOOKING_SUCCESS = 9;
    public static final int INVALID_LOGIN_CREDENTIALS = 10;
    public static final int INVALID_NAME = 11;
    public static final int INVALID_NAME_LENGTH = 12;
    public static final int INVALID_NEW_PASSWORD = 13;
    public static final int INVALID_PA_EMAIL = 14;
    public static final int INVALID_PA_PASSWORD = 15;
    public static final int INVALID_USERNAME = 16;
    public static final int LOG_IN_SUCCESS = 17;
    public static final int LOG_OUT = 18;
    public static final int NEW_PASSWORD_MISMATCH = 19;
    public static final int PA_IN_USE = 20;
    public static final int POPULATE_MEAL_DRINK_LIST = 21;
    public static final int PRINT_MEAL_INFO = 22;
    public static final int PRINT_DRINK_INFO = 23;
    public static final int REGISTER_MEMBER_SUCCESS = 24;
    public static final int REGISTER_MEMBER_ERROR = 25;
    public static final int REMOVE_PAY_ACCOUNT = 26;
    public static final int REMOVE_PAY_ACCOUNT_SUCCESS = 27;
    public static final int USERNAME_EXISTS = 28;
    public static final int VIEW_ACCOUNT_SETTINGS = 29;
    public static final int VIEW_MEMBERSHIP = 30;
    public static final int VIEW_MEMBERSHIP_OPTION = 31;
    public static final int WRONG_CURRENT_PASSWORD = 32;
    
    // BookingAppModel will take an Output object with an action and 
    // output strings to notify the View.
    String outputString1;
    String outputString2;
    String outputString3;
    ArrayList<Meal> mealList;
    ArrayList<Drink> drinkList;
    int action;
}
