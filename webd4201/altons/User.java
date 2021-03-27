package webd4201.altons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

/**
 * User.java
 * This is a user class that defines all base operations and default values for the child Faculty and Student classes.
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
public class User implements CollegeInterface {
    // Class constants
    /**
     * Default user id
     */
    protected final static long DEFAULT_ID = 100123456L;
    /**
     * Default user password
     */
    protected final static String DEFAULT_PASSWORD = "password";
    /**
     * Minimum user password length
     */
    protected final static byte MINIMUM_PASSWORD_LENGTH = 8;
    /**
     * Maximum user password length
     */
    protected final byte MAXIMUM_PASSWORD_LENGTH = 40;
    /**
     * Default user first name
     */
    protected final static String DEFAULT_FIRST_NAME = "John";
    /**
     * Default user last name
     */
    protected final static String DEFAULT_LAST_NAME = "Doe";
    /**
     * Default user email address
     */
    protected final static String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.com";
    /**
     * Default user enabled status
     */
    protected final static boolean DEFAULT_ENABLED_STATUS = true;
    /**
     * Default user type
     */
    protected final static char DEFAULT_TYPE = 's';
    /**
     * Default user id length
     */
    protected final static byte ID_NUMBER_LENGTH = 9;
    /**
     * Default date format for dates
     */
    protected final static DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);

    // Instance attributes
    /**
     * The user's id
     */
    private long id;
    /**
     * The user's password
     */
    private String password;
    /**
     * The user's first name
     */
    private String firstName;
    /**
     * The user's last name
     */
    private String lastName;
    /**
     * The user's email address
     */
    private String emailAddress;
    /**
     * The date the user last accessed the system
     */
    private Date lastAccess;
    /**
     * The date of the user was registered in the system
     */
    private Date enrolDate;
    /**
     * The status of the user
     */
    private boolean enabled;
    /**
     * The user type
     */
    private char type;

    // Constructors
    /**
     * Parameterized constructor - creates a new User instance
     *
     * @param id
     *          the user's id
     * @param password
     *          the user's password
     * @param firstName
     *          the user's first name
     * @param lastName
     *          the user's last name
     * @param emailAddress
     *          the user's email address
     * @param lastAccess
     *          the date the user last logged into the system
     * @param enrolDate
     *          the date the user enrolled in the system
     * @param enabled
     *          the status of the user as active/inactive
     * @param type
     *          the user's type
     * @throws InvalidUserDataException
     *          exception arises if any of the data values being set are determined to be invalid
     */
    public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) throws InvalidUserDataException {
        try {
            setId(id);
            setPassword(password);
            setFirstName(firstName);
            setLastName(lastName);
            setEmailAddress(emailAddress);
            setLastAccess(lastAccess);
            setEnrolDate(enrolDate);
            setEnabled(enabled);
            setType(type);
        } catch (Exception e) {
            throw new InvalidUserDataException(e.getMessage());
        }
    }

    /**
     * Default constructor - creates a new User instance using default values for each parameter
     *
     * @throws InvalidUserDataException
     *          exception arises if any of the data values being set are determined to be invalid
     */
    public User() throws InvalidUserDataException {
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE);
    }

    // Mutators and Accessors
    /**
     * Returns the user's id
     *
     * @return id The user's id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the user's id
     * @param id
     *          the user's new id
     *
     * @throws InvalidIdException
     *          thrown if id provided is outside of defined range
     */
    public void setId(long id) throws InvalidIdException {
        // Verify that id being set is within acceptable range
        if (verifyId(id))
        {
            this.id = id;
        }
        else
        {   // Throw corresponding exception if id is not within range
            throw new InvalidIdException("The id provided must be " + ID_NUMBER_LENGTH + " digits in length.");
        }
    }

    /**
     * Returns the user's password
     *
     * @return password The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     * @param password
     *          the user's new password
     * @throws InvalidPasswordException
     *          exception thrown if password provided is too few or too many characters in length
     */
    public void setPassword(String password) throws InvalidPasswordException {
        // Verify that password being set is acceptable length
        if ((password.length() >= MINIMUM_PASSWORD_LENGTH) && (password.length() <= MAXIMUM_PASSWORD_LENGTH))
        {
            this.password = password;
        }
        else
        {   // Throw exception if password length is outside of valid range
            throw new InvalidPasswordException("The password provided must be at least " + MINIMUM_PASSWORD_LENGTH+ " and no greater than " + MAXIMUM_PASSWORD_LENGTH + " characters in length.");
        }
    }

    /**
     * Returns the user's first name
     *
     * @return firstName The user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name
     * @param firstName
     *          the user's first name
     * @throws InvalidNameException
     *          exception thrown if the name value provided is numeric or an empty string
     */
    public void setFirstName(String firstName) throws InvalidNameException {
        // Flag for checking if name provided is numeric
        boolean notNumeric = false;

        // Verify that user's first name is not numeric before proceeding
        try
        {
            Double.parseDouble(firstName);
        }
        catch (NumberFormatException ex)
        {
            notNumeric = true;
        }

        // Verify that a name has been entered and is not numeric
        if (firstName.length() > 0 && notNumeric)
        {
            this.firstName = firstName;
        }
        else
        {
            // Throw corresponding exception if name is numeric or left blank
            throw new InvalidNameException("The first name cannot be blank or numeric.");
        }
    }

    /**
     * Returns the user's last name
     *
     * @return lastName The user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name
     *
     * @param lastName
     *          the user's last name
     *
     * @throws InvalidNameException
     *          exception thrown if the name value provided is numeric or an empty string
     */
    public void setLastName(String lastName) throws InvalidNameException {
        // Flag for checking if name provided is numeric
        boolean notNumeric = false;

        // Verify that user's last name is not numeric before proceeding
        try
        {
            Double.parseDouble(lastName);
        }
        catch (NumberFormatException ex)
        {
            notNumeric = true;
        }

        // Verify that a name has been entered and is not numeric
        if (lastName.length() > 0 && notNumeric)
        {
            this.lastName = lastName;
        }
        else
        {
            throw new InvalidNameException("The last name cannot be blank or numeric.");
        }
    }

    /**
     * Returns the user's email address
     *
     * @return email address The user's email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the user's email address
     *
     * @param emailAddress
     *          the user's new email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the date the user last accessed the system
     *
     * @return formatted date of when the user last accessed the system
     */
    public String getLastAccess() {
        return DF.format(lastAccess);
    }

    /**
     * Sets the date the user last accessed the system
     *
     * @param lastAccess
     *          formatted date the user last accessed the system
     */
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Returns the date the user was enrolled in the system
     *
     * @return formatted date of when the user was enrolled in the system
     */
    public String getEnrolDate() {
        return DF.format(enrolDate);
    }

    /**
     * Sets the date the user was enrolled in the system
     *
     * @param enrolDate
     *          the date the user was enrolled in the system
     */
    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * Returns the user's enabled status
     *
     * @return boolean The user's enabled status
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the user's enabled status
     *
     * @param enabled
     *          the enabled status of the user
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns the user's account type
     *
     * @return type Character representing the user's account type
     */
    public char getType() {
        return type;
    }

    /**
     * Sets the user's account type
     *
     * @param type
     *          the user's account type
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * Returns the user's account type as a proper-case string
     *
     * @return string The user's account type as a proper-case string
     */
    public String getTypeForDisplay() {
        return "User";
    }

    // Class methods
    /**
     * Builds and returns a formatted string summarizing user details
     *
     * @return description A formatted string describing the user's details
     */
    public String toString() {
        String description = "User Info for: " + getId() + "\n" +
                "\tName: " + getFirstName() + " " + getLastName() + "(" + getEmailAddress() + ")\n" +
                "\tCreated on: " + getEnrolDate() + "\n" +
                "\tLast access: " + getLastAccess();
        return description;
    }

    /**
     * Prints user summary info by calling the overloaded toString function
     */
    public void dump() {
        System.out.println(toString());
    }

    /**
     * Validates the id provided for the user based on acceptable range constants
     *
     * @param id
     *              the id of the user being verified
     * @return boolean Representing pass status of the validation
     */
    static public boolean verifyId(long id) {
        if(Long.toString(id).length() != ID_NUMBER_LENGTH) {
            return false;
        }
        return true;
    }

    /**
     * Encrypts a user password for secure storage in the database
     *
     * @param toHash
     *              A string representation of the password to be encrypted
     * @return String hashed password suitable for database storage
     */
    public static String encryptPassword(String toHash)
    {
        // Create new MessageDigest instance to handle encryption using SHA1 algorithm
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA1");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        // Get bytes of password string using sha1 digestion
        md.update(toHash.getBytes());
        byte[] bytesOfHashedString = md.digest();

        // Create empty string for encrypted password, and build string from array contents
        String hexPassword = "";
        StringBuilder tempString = new StringBuilder();

        for (int i = 0; i < bytesOfHashedString.length; i++)
        {
            tempString.append(String.format("%02x", bytesOfHashedString[i]));
        }

        // Convert to string and return the hashed value of the input
        hexPassword = tempString.toString();

        return hexPassword;
    }
}
