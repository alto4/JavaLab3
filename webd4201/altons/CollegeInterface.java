package webd4201.altons;

/**
 * CollegeInterface.java
 * This is the college interface class and contains const declarations of the college's name and phone number.
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
public interface CollegeInterface {
    // Attributes
    /**
     * the college's name
     */
    public static final String COLLEGE_NAME = "Durham College";
    /**
     * the default phone number for the college
     */
    public static final String PHONE_NUMBER = "(905)-721-2000";

    /**
     * Returns user type
     *
     * @return String the user's type
     */
    public abstract String getTypeForDisplay();
}
