package ticketsystem;

public class Output {
    public static final int ADD_PAY_ACCOUNT = 0;
    public static final int BACK_TO_HOME = 1;
    public static final int CHANGE_PASSWORD_SUCCESS = 2;
    public static final int CREATE_ACCOUNT = 3; 
    public static final int CREATE_ACCOUNT_SUCCESS = 4;
    public static final int INVALID_LOGIN_CREDENTIALS = 5;
    public static final int INVALID_NAME = 6;
    public static final int INVALID_NAME_LENGTH = 7;
    public static final int INVALID_NEW_PASSWORD = 8;
    public static final int INVALID_PA_EMAIL = 9;
    public static final int INVALID_PA_PASSWORD = 10;
    public static final int INVALID_USERNAME = 11;
    public static final int LOG_IN_SUCCESS = 12;
    public static final int LOG_OUT = 13;
    public static final int NEW_PASSWORD_MISMATCH = 14;
    public static final int PA_IN_USE = 15;
    public static final int USERNAME_EXISTS = 16;
    public static final int VIEW_ACCOUNT_SETTINGS = 17;
    public static final int WRONG_CURRENT_PASSWORD = 18;
    
    // BookingAppModel will take an Output object with an action and 
    // output strings to notify the View.
    String outputString1;
    String outputString2;
    String outputString3;
    int action;
}
