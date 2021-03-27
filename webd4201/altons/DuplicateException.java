package webd4201.altons;

/**
 * DuplicateException.java
 * Exception for when an attempt is made to create a user that is already present in the database
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/24)
 */
@SuppressWarnings("serial")
public class DuplicateException extends Exception {

    /**
     * Parameterized constructor - creates a new DuplicateException instance
     * @param message
     *              The exception message for display
     */
    public DuplicateException(String message)
    {
        super(message);
    }

    /**
     * Default constructor - creates a new DuplicateException
     */
    public DuplicateException() { }
}
