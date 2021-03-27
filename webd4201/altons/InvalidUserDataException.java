package webd4201.altons;

/**
 * InvalidUserDataException.java
 * Exception for when an attempt is made to create a new user with invalid data passed as
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
@SuppressWarnings("serial")
public class InvalidUserDataException extends Exception {

   /**
    * Parameterized constructor - creates a new InvalidUserDataException instance
    * @param message
    *              The exception message to be displayed
    */
    public InvalidUserDataException(String message)
    {
        super(message);
        System.out.println("An error occurred upon attempting to create a new user:");
    }

   /**
    * Default constructor - creates a new InvalidUserDataException instance
    */
    public InvalidUserDataException() { }
}
