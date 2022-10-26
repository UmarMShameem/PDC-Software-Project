package ticketsystem;

public class Output {
    public static final int CREATE_ACCOUNT = 0; 
    public static final int CREATE_ACCOUNT_SUCCESS = 1;
    public static final int INVALID_LOGIN_CREDENTIALS = 2;
    public static final int INVALID_NAME = 3;
    public static final int INVALID_NAME_LENGTH = 4;
    public static final int INVALID_NEW_PASSWORD = 5;
    public static final int INVALID_USERNAME = 6;
    public static final int LOG_IN_SUCCESS = 7;
    public static final int LOG_OUT = 8;
    public static final int NEW_PASSWORD_MISMATCH = 9;
    public static final int USERNAME_EXISTS = 10;
    
    // BookingAppModel will take an Output object with an action and 
    // output string to notify the View.
    String outputString;
    int action;
}
