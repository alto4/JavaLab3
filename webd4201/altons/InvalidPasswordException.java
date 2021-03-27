package webd4201.altons;

/**
 * InvalidPasswordException.java
 * Exception for when an attempt is made to set a user's password that is too short or too long.
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {


    /**
     * Parameterized constructor - creates a new InvalidPasswordException instance
     * @param message
     *              The exception message for display
     */
    public InvalidPasswordException(String message)
    {
        super(message);
    }

    /**
     * Default constructor - creates a new InvalidPasswordException
     */
    public InvalidPasswordException() { }
}
