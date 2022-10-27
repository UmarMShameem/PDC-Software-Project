package ticketsystem;

public class Output {
    public static final int ADD_PAY_ACCOUNT = 0;
    public static final int ADD_PAY_ACCOUNT_SUCCESS = 1;
    public static final int BACK_TO_HOME = 2;
    public static final int CHANGE_PASSWORD_SUCCESS = 3;
    public static final int CREATE_ACCOUNT = 4; 
    public static final int CREATE_ACCOUNT_SUCCESS = 5;
    public static final int INVALID_LOGIN_CREDENTIALS = 6;
    public static final int INVALID_NAME = 7;
    public static final int INVALID_NAME_LENGTH = 8;
    public static final int INVALID_NEW_PASSWORD = 9;
    public static final int INVALID_PA_EMAIL = 10;
    public static final int INVALID_PA_PASSWORD = 11;
    public static final int INVALID_USERNAME = 12;
    public static final int LOG_IN_SUCCESS = 13;
    public static final int LOG_OUT = 14;
    public static final int NEW_PASSWORD_MISMATCH = 15;
    public static final int PA_IN_USE = 16;
    public static final int REMOVE_PAY_ACCOUNT = 17;
    public static final int USERNAME_EXISTS = 18;
    public static final int VIEW_ACCOUNT_SETTINGS = 19;
    public static final int WRONG_CURRENT_PASSWORD = 20;
    
    // BookingAppModel will take an Output object with an action and 
    // output strings to notify the View.
    String outputString1;
    String outputString2;
    String outputString3;
    int action;
}
