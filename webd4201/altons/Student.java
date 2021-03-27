package webd4201.altons;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;

/**
 * Student.java
 * This is a student class that represents an individual student.
 * @version 1.0
 * @since 1.0 (2021/1/13)
 */
public class Student extends User {
    // Class constants
    /**
     * the default program code for a student instance
     */
    public final static String DEFAULT_PROGRAM_CODE = "UNDC";
    /**
     * the default program description for a student instance
     */
    public final static String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
    /**
     * the default registration year for a student instance
     */
    public final static int DEFAULT_YEAR = 1;

    // Instance attributes
    /**
     * the student's program code
     */
    private String programCode;
    /**
     * the student's program description
     */
    private String programDescription;
    /**
     * the student's registration year
     */
    private int year;
    /**
     * a vector containing mark instances representing course grades
     */
    private Vector<Mark> mark = new Vector<Mark>();

    // Static database connection and retrieval methods
    /**
     * starts a new database connection using the provided connection string
     *
     * @param c
     *          A connection object that establishes a connection with the database
     */
    public static void initialize(Connection c)
    {
        StudentDA.initialize(c);
    }

    /**
     * Retrieves a student record from the database
     *
     * @param id
     *          the id of the student record being retrieved from the database
     * @return Student object containing all values retrieve from the database
     * @throws NotFoundException
     *          Thrown if an attempt is made to retrieve a student record that does not exist
     */
    public static Student retrieve(long id) throws NotFoundException
    {
        return StudentDA.retrieve(id);
    }

    /**
     * Terminates the database connection
     */
    public static void terminate()
    {
        StudentDA.terminate();
    }

    // CRUD database access methods

    /**
     * Creates a new student record in the database
     *
     * @throws DuplicateException
     *          Thrown if an attempt is made to create a new student record using an id that already exists in the database
     */
    public void create() throws DuplicateException
    {
        StudentDA.create(this);
    }

    /**
     * Removes a student record from the database
     *
     * @throws NotFoundException
     *          Thrown if an attempt is made to remove a student record using an id that is not present in the database
     * @throws SQLException
     *          Thrown if SQL errors occur upon calling prepared statement in Data Access class
     */
    public void delete() throws NotFoundException, SQLException
    {
        StudentDA.delete(this);
    }

    /**
     * Updates an existing student record in the database
     *
     * @throws NotFoundException
     *          Thrown if attempt is made to update a student record using an id that is not present in the database
     * @throws SQLException
     *          Thrown if SQL errors occur upon calling prepared statement in Data Access class
     * @throws ParseException
     *          Thrown if an error occurs trying to parse date string values into Date objects
     */
    public void update() throws NotFoundException, SQLException, ParseException {
        StudentDA.update(this);
    }

    /**
     * Verifies that password and login match database records before allowing a student access to their account
     * @param id
     *          The unique student ID associated with an account
     * @param password
     *          The password provided by the user
     * @throws NotFoundException
     *          Thrown if attempt is made to update a student record using an id that is not present in the database

     * @return Student Object containing all details in database related to the authenticated student, or otherwise
     *          returns null if the credentials do not pass authentication
     */
    public static Student authenticate(long id, String password) throws NotFoundException {
        // Check if user exists in the database, and if so, update their record accordingly
        try
        {
            return StudentDA.authenticate(id, password);
        }
        catch (NotFoundException e)
        {
            // If no student with provided id is found in records, throw corresponding error
            throw new NotFoundException("Student not found. No student with the id " + id
                    + " could be found in the database so no update can be performed on the record.");
        }
    }
    /**
     * Parameterized constructor - creates a new student instance
     * @param id
     *          the student's id number
     * @param password
     *          the student's account password
     * @param firstName
     *          the student's first name
     * @param lastName
     *          the student's last name
     * @param emailAddress
     *          the student's email address
     * @param lastAccess
     *          the last time the student accessed their account
     * @param enrolDate
     *          the date the student was registered in the system
     * @param enabled
     *          the active/inactive status of the student's account
     * @param type
     *          the account type
     * @param programCode
     *          the student's program code
     * @param programDescription
     *          the student's program description
     * @param year
     *          the year the student is registered for
     * @param mark
     *          the grade the student has earned
     * @throws InvalidUserDataException
     *          exception is thrown if any values passed into constructor are deemed invalid
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year, Vector<Mark> mark) throws InvalidUserDataException {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        setProgramCode(programCode);
        setProgramDescription(programDescription);
        setYear(year);
        setMark(mark);
    }

    /**
     * Overloaded constructor - creates a new student instance without any marks being declared
     *
     * @param id
     *          the student's id number
     * @param password
     *          the student's account password
     * @param firstName
     *          the student's first name
     * @param lastName
     *          the student's last name
     * @param emailAddress
     *          the student's email address
     * @param lastAccess
     *          the last time the student accessed their account
     * @param enrolDate
     *          the date the student was registered in the system
     * @param enabled
     *          the active/inactive status of the student's account
     * @param type
     *          the account type
     * @param programCode
     *          the student's program code
     * @param programDescription
     *          the student's program description
     * @param year
     *          the year the student is registered for
     * @throws InvalidUserDataException
     *          exception is thrown if any values passed into constructor are deemed invalid
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year) throws InvalidUserDataException {
        this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year, new Vector<>());
    }

    /**
     * Default constructor - creates a new student instance using default values for all parameters
     *
     * @throws InvalidUserDataException
     *          exception is thrown if any values passed into constructor are deemed invalid
     */
    public Student() throws InvalidUserDataException {
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR);
    }

    //Accessors and Mutators
    /**
     * Returns student's program code
     *
     * @return programCode The code of the program the student is enrolled in
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets the student's program code
     *
     * @param programCode
     *                  the student's program code
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * Returns the student's program description
     *
     * @return programDescription A brief description of the student's program
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * Sets the student's program description
     *
     * @param programDescription
     *                  the student's program description
     */
    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * Return the year the student is registered for
     *
     * @return year The year the student is currently enrolled in
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year the student is registered for
     *
     * @param year
     *              the year the student is registered for
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns a vector containing the student's marks in various courses
     *
     * @return  mark A vector of Mark instances
     */
    public Vector<Mark> getMark() {
        return mark;
    }

    /**
     * Sets the student's marks earned
     *
     * @param mark
     *          a vector containing the student's marks
     */
    public void setMark(Vector<Mark> mark) {
        this.mark = mark;
    }

    // Class methods
    /**
     * Returns the suffix for inputted number
     *
     * @param year
     *          the registration year that suffix is to be generated for
     * @return String that contains the year of enrollment provided, as well as the corresponding suffix
     */
    public String getSuffix(int year) {
        switch(year) {
            case 1:
                return year + "st";
            case 2:
                return year + "nd";
            case 3:
                return year + "rd";
            default:
                return year + "th";
        }
    }

    /**
     * Returns a string summarizing the student's registration details
     *
     * @return description An overview of the student's registration details
     */
    public String toString() {
        String description = getTypeForDisplay() + " Info for:\n\t" +
                getFirstName() + " " + getLastName() + "(" + getId() + ")\n\t" +
                "Currently in " + getSuffix(getYear()) + " year of \"" + getProgramDescription() + "\" (" + getProgramCode() + ")\n\t" +
                "Enrolled: " + getEnrolDate() + "\n\t" +
                "No marks on record.";

        return description;
    }

    /**
     * Returns the student's user type as a formatted string
     *
     * @return String A formatted string describing the student's role at the college
     */
    public String getTypeForDisplay() {
        return "Student";
    }
}
