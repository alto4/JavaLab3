package webd4201.altons;

/**
 * NotFoundException.java
 * Exception for when an attempt is made to select a user not registered in the database
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/24)
 */
@SuppressWarnings("serial")
public class NotFoundException extends Exception {

    /**
     * Parameterized constructor - creates a new NotFoundException instance
     * @param message
     *              The exception message for display
     */
    public NotFoundException(String message)
    {
        super(message);
    }

    /**
     * Default constructor - creates a new NotFoundException
     */
    public NotFoundException() { }
}
