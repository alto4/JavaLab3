package webd4201.altons;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

/**
 * StudentDA.java
 * Handles all CRUD interactions with student records stored in a PostgreSQL database using various prepared statements
 * @author Scott Alton
 * @version 1.0
 * @since 1.0 (2021/2/7)
 */
public class StudentDA {

    /**
     * Student instance that will be used to interact with the database
     */
    static Student aStudent;

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
     * the student's program code
     */
    static String programCode;
    /**
     * the student's program description
     */
    static String programDescription;
    /**
     * the student's registration year
     */
    static int year;
    /**
     * a vector containing mark instances representing course grades
     */
    static Vector<Mark> mark = new Vector<Mark>();

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

    // CRUD Functions - handle student records in database using prepared statements
    /**
     * create - Attempts to create a new student record in the database after checking if student's id is already present in student records
     *
     * @param aStudent
     *              A student object to be added to the database
     * @return created A boolean representing success status of the attempt to create a new student record
     * @throws DuplicateException
     *              Thrown in event that a student with the provided id already exists in the database records
     */
    public static boolean create(Student aStudent) throws DuplicateException
    {
        boolean created = false;

        // Update all properties from student object provided
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        enrolDate = new Date();
        lastAccess = new Date();
        type = aStudent.getType();
        enabled = aStudent.isEnabled();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();

        // Check if student is already registered in system before processing registration
        try
        {
            retrieve(id);
            throw (new DuplicateException("Error creating new Student record. A student with the id " + id +" is already registered in the database."));
        }
        // If NotFoundException occurs, the id provided is unique and proceed to add student to database
        catch(NotFoundException e)
        {
            try
            {
                // Prepare insert statement to create new user/student record
                PreparedStatement psInsert = aConnection.prepareStatement("INSERT INTO Users (id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);INSERT INTO Students (id, program_code, program_description, year) VALUES (?, ?, ?, ?)");

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
                psInsert.setString(11, programCode);
                psInsert.setString(12, programDescription);
                psInsert.setInt(13, year);

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
     * Attempts to retrieve a student based on their unique student id number
     *
     * @param id
     *         The id of the student attempting to be retrieved from the database
     * @return aStudent Object containing details of the specified student
     * @throws NotFoundException
     *          thrown if student id provided is not present in the database
     */
    public static Student retrieve(long id) throws NotFoundException
    {
        // Attempt to execute the SQL select statement to retrieve an individual student's details
        try
        {
            // Prepare retrieve statement to get a single student user record
            PreparedStatement psRetrieve = aConnection.prepareStatement("SELECT Users.id, password, first_name, last_name, email_address, last_access, enrol_date, enabled, type, program_code, program_description, year  FROM Students, Users  WHERE Users.id = Students.id AND Users.id = (?)");

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
                type = 's';
                programCode = rs.getString("program_code");
                programDescription = rs.getString("program_description");
                year = Integer.parseInt(rs.getString("year"));
                mark = new Vector<Mark>();

                // create Student instance based on result set from database
                try
                {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year, mark);
                }
                catch (InvalidUserDataException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                // Throw exception if no student was found with provided id
                throw (new NotFoundException("Student with id of " + id + " not found in the database."));
            }
            rs.close();
        }
        catch (SQLException e)
        {
            // Print SQL exception that could potentially arise when prepared statement is executed
            System.out.println(e);
        }

        return aStudent;
    }

    /**
     * Update - updates an existing record in the database
     *
     * @param aStudent
     *          Student object containing updated details to be reflected in the database
     * @return records An int representing the number of records updated by the query
     * @throws NotFoundException
     *          Thrown if an attempt is made to update a student record that does not exist in the database
     * @throws SQLException
     *          Thrown if any SQL errors arise in attempting to execute prepared statement on database records
     * @throws ParseException
     *      *   Thrown if an error occurs trying to parse date string values into Date objects
     */
    public static int update(Student aStudent) throws NotFoundException, SQLException, ParseException {
        int records = 0;

        // Get updated student values from argument object
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = new Date();
        enrolDate = DF.parse(aStudent.getEnrolDate());
        enabled = aStudent.isEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();
        mark = aStudent.getMark();

        // Prepare update statement to update the users table record with the corresponding ID
        PreparedStatement psUpdate = aConnection.prepareStatement("UPDATE Users SET password = (?), first_name = (?), last_name = (?), email_address = (?), last_access = (?), enrol_date = (?), enabled = (?), type = (?) WHERE id = (?);UPDATE Students SET program_code = (?), program_description = (?), year = (?) WHERE id = (?)");

        // Set values for insert prepared statement that will go into users table
        psUpdate.setString(1, User.encryptPassword(password));
        psUpdate.setString(2, firstName);
        psUpdate.setString(3, lastName);
        psUpdate.setString(4, emailAddress);
        psUpdate.setDate(5, new java.sql.Date(lastAccess.getTime()));
        psUpdate.setDate(6, new java.sql.Date(enrolDate.getTime()));
        psUpdate.setBoolean(7, enabled);
        psUpdate.setString(8, String.valueOf(type));
        psUpdate.setLong(9, id);

        // Set values for insert prepared statement that will go into students table
        psUpdate.setString(10, programCode);
        psUpdate.setString(11, programDescription);
        psUpdate.setInt(12, year);
        psUpdate.setLong(13, id);

        // Check if user exists in the database, and if so, update their record accordingly
        try
        {
            Student.retrieve(id);
            // If corresponding student record is found, execute the SQL update statement
            records = psUpdate.executeUpdate();
        }
        catch(NotFoundException e)
        {
            // If no student with provided id is found in records, throw corresponding error
            throw new NotFoundException("Student not found. No student with the id " + id
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
     * delete - Remove student record from the database
     *
     * @param aStudent
     *          Student object to be deleted from the database
     * @return records An int representing the number of records deleted by the query
     * @throws NotFoundException
     *          Thrown if an attempt is made to delete a student that is not present in the database
     * @throws SQLException
     *          Thrown if any SQL errors arise in attempting to execute prepared statement on database records
     */
    public static int delete(Student aStudent) throws NotFoundException, SQLException {
        int records = 0;
        // Get the student id
        id = aStudent.getId();

        // Prepare retrieve statement to get a single student user record
        PreparedStatement psDelete = aConnection.prepareStatement("DELETE FROM Students WHERE id = (?); DELETE FROM Users WHERE id = (?)");

        // Set values for insert prepared statement
        psDelete.setLong(1, id);
        psDelete.setLong(2, id);

        // Check if student exists in records based on id
        try
        {
            Student.retrieve(id);

            // If student is found, execute the SQL update statement
            records = psDelete.executeUpdate();
        }
        catch(NotFoundException e)
        {
            throw new NotFoundException("No student with the id  " + id + " exists, and therefore they cannot be deleted. Please check that you are trying to delete a student with a valid student id.");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }

        return records;
    }

    /**
     * Verifies that password and login match database records before returning a Student object containing a registered
     * students account details
     * *
     * @param id
     *          The unique student ID associated with an account
     * @param password
     *          The password provided by the user, to be encrypted and compared to database record
     * @throws NotFoundException
     *          Thrown if attempt is made to update a student record using an id that is not present in the database

     * @return aStudent A Student object containing details of an authenticated user's registration details, or if
     *         authentication fails returns null to represent failed attempt to login
     */
    public static Student authenticate(long id, String password) throws NotFoundException {
        // Check if user exists in the database, and if so, update their record accordingly
        try
        {
            // Attempt to retrieve student record using provided login id
            Student aStudent = Student.retrieve(id);

            // Compare login credentials provided against database records
            if((User.encryptPassword(password)).equals(aStudent.getPassword())) {
                System.out.println("USER AUTHENTICATED SUCCESSFULLY");
            } else {
                return null;
            }
        }
        catch(NotFoundException e)
        {
            // If no student with provided id is found in records, throw corresponding error
            throw new NotFoundException("Student not found. No student with the id " + id
                    + " could be found in the database so no update can be performed on the record.");
        }

        return aStudent;
    }
}

