package webd4201.altons;

import java.sql.*;

/**
 * DatabaseConnect - establishes database connection for managing student and faculty data.
 * @author Scott Alton (modified from source code provided by Darren Puffer)
 * @version 1.0 (26 January 2021)
 * @since 1.0
 */
public class DatabaseConnect
{
    /**
     * Database location
     */
    static String url = "jdbc:postgresql://127.0.0.1:5432/webd4201_db";
    /**
     * Connection object to open port to db
     */
    static Connection aConnection;
    /**
     * Database user
     */
    static String user = "webd4201_admin";
    /**
     * Database user password
     */
    static String password = "webd4201_password";

    /**
     * initialize - Establishes the connection with PostgreSQL database
     *
     * @return Connection to the webd4201_db database
     */
    public static Connection initialize()
    {
        try
        {
            // Load the JDBC Driver for PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Create new database connection instance
            aConnection = DriverManager.getConnection(url, user, password);
        }
        // Catch exception in event that .jar file is not located
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        // Catch all other exceptions that could occur connecting to the database
        catch (SQLException e)
        {
            System.out.println(e);
        }

        return aConnection;
    }

    /**
     * terminate - Closes the database connection
     */
    public static void terminate()
    {
        try
        {
            aConnection.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}
