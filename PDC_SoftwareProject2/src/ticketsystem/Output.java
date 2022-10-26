package ticketsystem;

public class Output {
    public static final int BACK_TO_HOME = 0;
    public static final int CREATE_ACCOUNT = 1; 
    public static final int CREATE_ACCOUNT_SUCCESS = 2;
    public static final int INVALID_LOGIN_CREDENTIALS = 3;
    public static final int INVALID_NAME = 4;
    public static final int INVALID_NAME_LENGTH = 5;
    public static final int INVALID_NEW_PASSWORD = 6;
    public static final int INVALID_USERNAME = 7;
    public static final int LOG_IN_SUCCESS = 8;
    public static final int LOG_OUT = 9;
    public static final int NEW_PASSWORD_MISMATCH = 10;
    public static final int USERNAME_EXISTS = 11;
    public static final int VIEW_ACCOUNT_SETTINGS = 12;
    
    // BookingAppModel will take an Output object with an action and 
    // output strings to notify the View.
    String outputString1;
    String outputString2;
    String outputString3;
    int action;
}
