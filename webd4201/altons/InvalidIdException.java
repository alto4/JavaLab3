package webd4201.altons;

/**
 * InvalidIdException.java
 * Exception for when an attempt to set the id is made outside the range of valid numbers.
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
@SuppressWarnings("serial")
public class InvalidIdException extends Exception {

    /**
     * Parameterized constructor - creates a new InvalidIdException instance
     * @param message
     *              The exception message to be displayed
     */
     public InvalidIdException(String message)
   {
       super(message);
   }

    /**
     * Default constructor - creates a new InvalidIdException instance
     */
     public InvalidIdException() { }
}
