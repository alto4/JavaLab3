package webd4201.altons;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * Faculty.java
 * This is the faculty class that represents a faculty member at the college.
 * @version 2.0
 * @since 2.0 (2021/2/16)
 */
public class Faculty extends User implements CollegeInterface {

    // Class constants
    /**
     * the default school code
     */
    public final static String DEFAULT_SCHOOL_CODE = "SET";
    /**
     * the default school description
     */
    public final static String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
    /**
     * the default office number
     */
    public final static String DEFAULT_OFFICE = "H-140";
    /**
     * the default phone extension
     */
    public final static int DEFAULT_PHONE_EXTENSION = 1234;

    // Instance attributes
    /**
     * the school code
     */
    private String schoolCode;
    /**
     * the school description
     */
    private String schoolDescription;
    /**
     * the faculty's office
     */
    private String office;
    /**
     * the faculty's phone extension
     */
    private int extension;

    // Static database connection and retrieval methods
    /**
     * starts a new database connection using the provided connection string
     *
     * @param c
     *          A connection object that establishes a connection with the database
     */
    public static void initialize(Connection c)
    {
        FacultyDA.initialize(c);
    }

    /**
     * Retrieves a faculty record from the database
     *
     * @param id
     *          the id of the faculty record being retrieved from the database
     * @return Faculty object containing all values retrieve from the database
     * @throws NotFoundException
     *          Thrown if an attempt is made to retrieve a faculty record that does not exist
     */
    public static Faculty retrieve(long id) throws NotFoundException
    {
        return FacultyDA.retrieve(id);
    }

    /**
     * Terminates the database connection
     */
    public static void terminate()
    {
        FacultyDA.terminate();
    }

    // CRUD database access methods
    /**
     * Creates a new faculty record in the database
     *
     * @throws DuplicateException
     *          Thrown if an attempt is made to create a new faculty record using an id that already exists in the database
     */
    public void create() throws DuplicateException
    {
        FacultyDA.create(this);
    }

    /**
     * Removes a faculty record from the database
     *
     * @throws NotFoundException
     *          Thrown if an attempt is made to remove a faculty record using an id that is not present in the database
     * @throws SQLException
     *          Thrown if SQL errors occur upon calling prepared statement in Data Access class
     */
    public void delete() throws NotFoundException, SQLException
    {
        FacultyDA.delete(this);
    }

    /**
     * Updates an existing faculty record in the database
     *
     * @throws NotFoundException
     *          Thrown if attempt is made to update a faculty record using an id that is not present in the database
     * @throws SQLException
     *          Thrown if SQL errors occur upon calling prepared statement in Data Access class
     * @throws ParseException
     *          Thrown if an error occurs trying to parse date string values into Date objects
     */
    public void update() throws NotFoundException, SQLException, ParseException {
        FacultyDA.update(this);
    }

    // Constructor
    /**
     * Parameterized constructor - creates a new faculty instance
     *
     * @param id
     *          The faculty's id number
     * @param password
     *          The faculty's password
     * @param firstName
     *          The faculty's first name
     * @param lastName
     *          The faculty's last name
     * @param emailAddress
     *          The faculty's email address
     * @param lastAccess
     *          The date the faculty last accessed the system
     * @param enrolDate
     *          The date the faculty member was enrolled in the system
     * @param enabled
     *          The enabled status of the faculty
     * @param type
     *          The faculty's user type
     * @param code
     *          The faculty's code
     * @param description
     *          The faculty's description
     * @param office
     *          The faculty's office location
     * @param extension
     *          The faculty's phone extension
     * @throws InvalidUserDataException
     *          This exception is thrown if any of the value passed into the constructor are deemed invalid
     */
    public Faculty(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String code, String description, String office, int extension) throws InvalidUserDataException {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        setSchoolCode(code);
        setSchoolDescription(description);
        setOffice(office);
        setExtension(extension);
    }

    /**
     * Default constructor - creates a new faculty instance
     *
     * @throws InvalidUserDataException
     *          This exception is thrown if any of the value passed into the constructor are deemed invalid
     */
    public Faculty() throws InvalidUserDataException {
        super();
        setSchoolCode(DEFAULT_SCHOOL_CODE);
        setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
        setOffice(DEFAULT_OFFICE);
        setExtension(DEFAULT_PHONE_EXTENSION);
    }

    // Accessors and Mutators
    /**
     * Returns the school code
     *
     * @return schoolCode The code assigned to the faculty's school
     */
    public String getSchoolCode() {
        return schoolCode;
    }

    /**
     * Sets the school code
     *
     * @param schoolCode
     *                  The faculty's school code
     */
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    /**
     * Returns the school description
     *
     * @return school description A description of the faculty's school
     */
    public String getSchoolDescription() {
        return schoolDescription;
    }

    /**
     * Sets the school description
     *
     * @param schoolDescription
     *                  A description of the faculty's school
     */
    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    /**
     * Returns the faculty's office location
     *
     * @return office The faculty's office location
     */
    public String getOffice() {
        return office;
    }

    /**
     * Sets the faculty's office location
     *
     * @param office
     *              The faculty's office location
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Returns the faculty's phone extension
     *
     * @return extension The phone extension to contact the faculty member
     */
    public int getExtension() {
        return extension;
    }

    /**
     * Sets the faculty member's phone extension
     *
     * @param extension
     *             The faculty's phone extension
     */
    public void setExtension(int extension) {
        this.extension = extension;
    }

    // Class methods
    /**
     * Returns the faculty member's type as a proper-case string
     *
     * @return String A formatted string describing the faculty's role at the college
     */
    public String getTypeForDisplay() {
        return "Faculty";
    }

    /**
     * Returns a summary of the faculty member's details
     *
     * @return facultyString A summary of the faculty member's details
     */
    public String toString() {
        String facultyString = super.toString().replace("User", getTypeForDisplay());
        facultyString += "\n\t" + getSchoolDescription() + "(" + getSchoolCode() + ")\n\t" +
                "Office: " + getOffice() + "\n\t" +
                PHONE_NUMBER + " x" + getExtension();

        return facultyString;
    }

}
