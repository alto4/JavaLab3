package webd4201.altons;

/**
 * InvalidNameException.java
 * Exception for when an attempt to set the first or last name is made that is numeric, less than the miniumum number of characters allowed,
 * or greater than the maximum number of characters allowed.
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
@SuppressWarnings("serial")
public class InvalidNameException extends Exception {

    /**
     * Parameterized constructor - creates a new InvalidNameException instance
     * @param message
     *              The exception message for display
     */
    public InvalidNameException(String message)
    {
        super(message);
    }

    /**
     * Default constructor - creates a new InvalidNameException instance
     */
    public InvalidNameException() { }
}
