<% String title = "Home";

    String message = (String)session.getAttribute("message");

     if (message == null) {
        message = "";
     } else {
        session.removeAttribute("message");
     }
%>

<%@ include file="./header.jsp" %>

    <h4 class="text-success"><%= message %></h4>

	<h2>Welcome to DC Grades</h2>

	<p class="lead">Manage your academic records in one location.</p>
	<a class="btn btn-info btn-lg px-5" href="./login.jsp">Log In</a>

    <div class="w-100 row mx-auto">
        <div class="mx-auto col-12 col-md-7 my-5">
             <img src="https://durhamcollege.ca/wp-content/uploads/whitby-campus-banner.jpg" alt="Durham College's Oshawa campus." class="shadow-lg rounded w-100 img-fluid" />
        </div>
    </div>

<%@ include file="./footer.jsp" %>