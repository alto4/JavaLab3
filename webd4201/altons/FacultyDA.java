package webd4201.altons;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * FacultyDA.java
 * Handles all CRUD interactions with faculty records stored in a PostgreSQL database using various prepared statements
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/2/16)
 */
public class FacultyDA {

    /**
     * Faculty instance that will be used to interact with the database
     */
    static Faculty aFaculty;

    // Database connection variables
    /**
     * Connection object for connecting to database
     */
    static Connection aConnection;
    /**
     * Statement object for interacting with the database
     */
    static Statement aStatement;

    // Instance attributes
    /**
     * The user's id
     */
    static long id;
    /**
     * The user's password
     */
    static String password;
    /**
     * The user's first name
     */
    static String firstName;
    /**
     * The user's last name
     */
    static String lastName;
    /**
     * The user's email address
     */
    static String emailAddress;
    /**
     * The date the user last accessed the system
     */
    static Date lastAccess;
    /**
     * The date of the user was registered in the system
     */
    static Date enrolDate;
    /**
     * The status of the user
     */
    static boolean enabled;
    /**
     * The user type
     */
    static char type;
    /**
     * the school code
     */
    static String schoolCode;
    /**
     * the school description
     */
    static String schoolDescription;
    /**
     * the faculty's office
     */
    static String office;
    /**
     * the faculty's phone extension
     */
    static int extension;

    // Class constants
    /**
     * the default date format for storage in database
     */
    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Date format used to return lastAccess and enrolDate in User class accessor methods
     */
    protected final static DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);

    /**
     * initialize - establishes the database connection
     * @param c
     *          A connection string that will be used to connect to the database
     */
    public static void initialize(Connection c)
    {
        try
        {
            aConnection = c;
            aStatement = aConnection.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    /**
     * terminate - Closes the database connection
     */
    public static void terminate()
    {
        try
        { 	// close the statement
            aStatement.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    // CRUD Functions - handle faculty records in database using prepared statements
    /**
     * create - Attempts to create a new faculty record in the database after checking if faculty's unique id is already present in records
     *
     * @param aFaculty
     *              A faculty object to be added to the database
     * @return created A boolean representing success status of the attempt to create a new faculty record
     * @throws DuplicateException
     *              Thrown in event that a faculty member with the provided id already exists in the database records
     */
    public static boolean create(Faculty aFaculty) throws DuplicateException
    {
        boolean created = false;

        // Update all properties from student object provided
        id = aFaculty.getId();
        password = aFaculty.getPassword();
        firstName = aFaculty.getFirstName();
        lastName = aFaculty.getLastName();
        emailAddress = aFaculty.getEmailAddress();
        enrolDate = new Date();
        lastAccess = new Date();
        type = aFaculty.getType();
        enabled = aFaculty.isEnabled();
        schoolCode = aFaculty.getSchoolCode();
        schoolDescription = aFaculty.getSchoolDescription();
        office = aFaculty.getOffice();
        extension = aFaculty.getExtension();

        // Check if faculty is already registered in system before processing registration
        try
        {
            retrieve(id);
            throw (new DuplicateException("Error creating new Faculty record. A faculty with the id " + id +" is already registered in the database."));
        }
        // If NotFoundException occurs, the id provided is unique and proceed to add faculty member to database
        catch(NotFoundException e)
        {
            try
            {
                // Prepare insert statement to create new user/student record
                PreparedStatement psInsert = aConnection.prepareStatement("INSERT INTO Users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);INSERT INTO Faculty (id, school_code, school_description, office, extension) VALUES (?, ?, ?, ?, ?)");

                // Set values for insert prepared statement
                psInsert.setLong(1, id);
                psInsert.setString(2, User.encryptPassword(password));
                psInsert.setString(3, firstName);
                psInsert.setString(4, lastName);
                psInsert.setString(5, emailAddress);
                psInsert.setDate(6, java.sql.Date.valueOf(SQL_DF.format(lastAccess)));
                psInsert.setDate(7, java.sql.Date.valueOf(SQL_DF.format(enrolDate)));
                psInsert.setBoolean(8, enabled);
                psInsert.setString(9, String.valueOf(type));
                psInsert.setLong(10, id);
                psInsert.setString(11, schoolCode);
                psInsert.setString(12, schoolDescription);
                psInsert.setString(13, office);
                psInsert.setInt(14, extension);

                // execute the SQL update statement
                created = psInsert.execute();
            }
            catch (SQLException ee)
            {
                System.out.println(ee);
            }
        }
        return created;
    }

    /**
     * Attempts to retrieve a faculty member based on their unique id number
     *
     * @param id
     *         The id of the faculty member attempting to be retrieved from the database
     * @return aFaculty Object containing details of the specified faculty member
     * @throws NotFoundException
     *          thrown if faculty id provided is not present in the database
     */
    public static Faculty retrieve(long id) throws NotFoundException
    {
        // Attempt to execute the SQL select statement to retrieve an individual faculty member's details
        try
        {
            // Prepare retrieve statement to get a single faculty user record
            PreparedStatement psRetrieve = aConnection.prepareStatement("SELECT Users.id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type, school_code, school_description, office, extension  FROM Faculty, Users  WHERE Users.id = Faculty.id AND Users.id = (?)");

            // Set id value to query for prepared statement
            psRetrieve.setLong(1, id);

            // Retrieve result set from running prepared statement
            ResultSet rs = psRetrieve.executeQuery();

            boolean queryStatus = rs.next();

            // Continue retrieving records for each result row (should only be 1 row due to unique id)
            if (queryStatus)
            {
                // Extract the data from result set
                id = rs.getLong("id");
                password = rs.getString("password");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                emailAddress = rs.getString("email_address");
                lastAccess = rs.getDate("last_access");
                enrolDate = rs.getDate(("enrol_date"));
                enabled = rs.getBoolean("enabled");
                type = 'f';
                schoolCode = rs.getString("school_code");
                schoolDescription = rs.getString("school_description");
                office = rs.getString("office");
                extension = Integer.parseInt(rs.getString("extension"));

                // create Faculty instance based on result set from database
                try
                {
                    aFaculty = new Faculty(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, schoolCode, schoolDescription, office, extension);
                }
                catch (InvalidUserDataException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                // Throw exception if no faculty member was found with provided id
                throw (new NotFoundException("Faculty member with id of " + id + " not found in the database."));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            // Print SQL exception that could potentially arise when prepared statement is executed
            System.out.println(e);
        }

        return aFaculty;
    }

    /**
     * Update - updates an existing record in the database
     *
     * @param aFaculty
     *          Faculty object containing updated details to be reflected in the database
     * @return records An int representing the number of records updated by the query
     * @throws NotFoundException
     *          Thrown if an attempt is made to update a faculty record that does not exist in the database
     * @throws SQLException
     *          Thrown if any SQL errors arise in attempting to execute prepared statement on database records
     * @throws ParseException
     *      *   Thrown if an error occurs trying to parse date string values into Date objects
     */
    public static int update(Faculty aFaculty) throws NotFoundException, SQLException, ParseException {
        int records = 0;

        // Get updated faculty values from argument object
        id = aFaculty.getId();
        password = aFaculty.getPassword();
        firstName = aFaculty.getFirstName();
        lastName = aFaculty.getLastName();
        emailAddress = aFaculty.getEmailAddress();
        lastAccess = new Date();
        enrolDate = DF.parse(aFaculty.getEnrolDate());
        enabled = aFaculty.isEnabled();
        type = aFaculty.getType();
        schoolCode = aFaculty.getSchoolCode();
        schoolDescription = aFaculty.getSchoolDescription();
        office = aFaculty.getOffice();
        extension = aFaculty.getExtension();

        // Prepare update statement to update the users table record with the corresponding ID
        PreparedStatement psUpdate = aConnection.prepareStatement("UPDATE Users SET password = (?), first_name = (?), last_name = (?), email_address = (?), last_access = (?), enrol_date = (?), enabled = (?), type = (?) WHERE id = (?);UPDATE Faculty SET school_code = (?), school_description = (?), office = (?), extension = (?) WHERE id = (?)");

        // Set values for insert prepared statement that will go into users table
        psUpdate.setString(1, password);
        psUpdate.setString(2, firstName);
        psUpdate.setString(3, lastName);
        psUpdate.setString(4, emailAddress);
        psUpdate.setDate(5, new java.sql.Date(lastAccess.getTime()));
        psUpdate.setDate(6, new java.sql.Date(enrolDate.getTime()));
        psUpdate.setBoolean(7, enabled);
        psUpdate.setString(8, String.valueOf(type));
        psUpdate.setLong(9, id);

        // Set values for insert prepared statement that will go into faculty table
        psUpdate.setString(10, schoolCode);
        psUpdate.setString(11, schoolDescription);
        psUpdate.setString(12, office);
        psUpdate.setInt(13, extension);
        psUpdate.setLong(14, id);

        // Check if user exists in the database, and if so, update their record accordingly
        try
        {
            Faculty.retrieve(id);
            // If corresponding faculty record is found, execute the SQL update statement
            records = psUpdate.executeUpdate();
        }
        catch(NotFoundException e)
        {
            // If no faculty member with provided id is found in records, throw corresponding error
            throw new NotFoundException("Faculty member not found. No faculty with the id " + id
                    + " could be found in the database so no update can be performed on the record.");
        }
        catch (SQLException e)
        {
            // Print SQL exception that could potentially arise when prepared statement is executed
            System.out.println(e);
        }
        return records;
    }

    /**
     * delete - Remove faculty member record from the database
     *
     * @param aFaculty
     *          Faculty object to be deleted from the database
     * @return records An int representing the number of records deleted by the query
     * @throws NotFoundException
     *          Thrown if an attempt is made to delete a faculty member that is not present in the database
     * @throws SQLException
     *          Thrown if any SQL errors arise in attempting to execute prepared statement on database records
     */
    public static int delete(Faculty aFaculty) throws NotFoundException, SQLException {
        int records = 0;
        // Get the faculty id
        id = aFaculty.getId();

        // Prepare retrieve statement to get a single faculty user record
        PreparedStatement psDelete = aConnection.prepareStatement("DELETE FROM Faculty WHERE id = (?); DELETE FROM Users WHERE id = (?)");

        // Set values for insert prepared statement
        psDelete.setLong(1, id);
        psDelete.setLong(2, id);

        // Check if faculty exists in records based on id
        try
        {
            Faculty.retrieve(id);

            // If faculty is found, execute the SQL update statement
            records = psDelete.executeUpdate();
        }
        catch(NotFoundException e)
        {
            throw new NotFoundException("No faculty member with the id  " + id + " exists, and therefore they cannot be deleted. Please check that you are trying to delete a faculty member with a valid id.");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }

        return records;
    }
}
