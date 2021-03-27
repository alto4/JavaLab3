<% String title = "Dashboard";%>

<%@ include file="./header.jsp" %>

<%@ page import = "webd4201.altons.*" %>
<%
    Student aStudent = (Student)session.getAttribute("student");
    String name;
    String welcomeMessage;

    if(aStudent != null) {
        name = aStudent.getFirstName() + " " + aStudent.getLastName();
        welcomeMessage = "You are currently registered in the " + aStudent.getSuffix(aStudent.getYear()) + " year of "+ aStudent.getProgramDescription() + ".";
    } else {
        name = "Anonymous";
        welcomeMessage = "<span class=\"text-danger\">You aren't supposed to be here</span>";
    }
%>

	<h2>Student Dashboard</h2>
	<h4 class="text-success my-3">Welcome, <%= name %></h4>
	<p class="lead font-weight-bold"><%= welcomeMessage %></p>
	<p class="lead w-75 mx-auto">This site is <span class="text-danger">currently under construction</span>, and you can look forward to a number of exciting features coming soon.</p>
	<a class="btn btn-info btn-lg px-3" href="./index.jsp"> Return Home</a>

<%@ include file="./footer.jsp" %>
