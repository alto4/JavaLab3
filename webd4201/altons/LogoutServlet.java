package webd4201.altons;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * LogoutServlet.java - destroys user's authorized session and creates a message updating user that logout was successful.
 * @author Scott Alton (modified from source code provided by Darren Puffer)
 * @version 1.0 (11 March 2021)
 * @since 1.0
 */
public class LogoutServlet extends HttpServlet
{
    /**
     * Creates a logout request to the server that will destroy student records from active session
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
       // End the session
       HttpSession session = request.getSession();
       session.removeAttribute("student");

       // Generate a message alerting user of successful logout and redirect them back to index
       session.setAttribute("message", "Successfully logged out.");

       response.sendRedirect("./index.jsp");
   }
}


