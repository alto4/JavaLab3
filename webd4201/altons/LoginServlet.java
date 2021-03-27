package webd4201.altons;

import com.sun.media.sound.InvalidDataException;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * LoginServlet.java - attempts to authorize a user's login credentials, and upon success creates a new session
 * @author Scott Alton (modified from source code provided by Darren Puffer)
 * @version 1.0 (11 March 2021)
 * @since 1.0
 */
public class LoginServlet extends HttpServlet {

    /**
     * Creates a login request to the server that will attempt to authenticate a user and log them into their account
     *
     * @param request
     *           The request being sent to the servlet
     * @param response
     *           The response provided by the servlet
     * @throws ServletException
     *           General exception thrown by the servlet if it encounters difficulties
     * @throws IOException
     *           Thrown if an error occurs while processing requests input and output
     *
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            // Connect to database
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            HttpSession session = request.getSession(true);

            String login = new String();
            String password = new String();

            try
            {
                // Get data provided by user from form fields
                login = request.getParameter( "Login" );
                password = request.getParameter("Password");

                // Attempt to authenticate the login credentials using static Student method
                if(Student.authenticate(Long.parseLong(login), password) != null)
                {
                    // Once authenticated, retrieve their details from the database and set to the current session
                    Student aStudent = Student.retrieve(Long.parseLong(login));
                    session.setAttribute("student", aStudent);

                    // Empty out potential error messages from session storage
                    session.setAttribute("errors", "");

                    // Redirect the user to the dashboard
                    response.sendRedirect("./dashboard.jsp");
                }
                else
                {
                    // Add an error message to the session and send back to login page
                    session.setAttribute("errors", "Invalid login credentials. Please try again");
                    response.sendRedirect("./login.jsp");
                }
            }
            catch( NotFoundException nfe)
            {
                session.setAttribute("errors", "Invalid login credentials. Please try again");
                response.sendRedirect("./login.jsp");
            }
        }
        catch (Exception e)
        {
            HttpSession session = request.getSession(true);
            System.out.println(e);
            session.setAttribute("errors","Invalid login credentials. Please try again");
            response.sendRedirect("./login.jsp");
        }
    }
}