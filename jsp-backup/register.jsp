<% String title = "Register";%>

<%@ include file="./header.jsp" %>

<%@ page import = "demo.*" %>
<%
    String errorMessage = (String)session.getAttribute("errors");
    String login = (String)session.getAttribute("login");
    String id = "";
    String firstName = "";
    String lastName = "";
    String email = "";
    String programCode = "testing stickiness of form";
    String programDescription = "";
    int year = 1;
    String password = "";
    String passwordConfirm = "1";


     if (errorMessage == null) {
        errorMessage = "";
     } else {
        session.removeAttribute("errors");
     }

	if(login == null)
		login = "";


%>
	<h2><%= title %></h2>
	<p class="lead mx-auto">
	    Please complete the form below to register to the  <a class="text-success font-weight-bold" href="./index.jsp">D/C Grades</a> platform.
	</p>

	<form class="bg-dark row rounded text-white p-3 col-lg-4 col-sm-8 col-xs-12 mx-auto my-3" name="Input" method="post" action="./Register" >
        <div class="text-danger h6 mx-auto col-12 justify-content-center"><%= errorMessage %></div>

        <div class="row w-100 p-2">
			<div class="col-4 text-right">Student ID:</div>
			<div class="col-4"><input type="text" name="Id" value="<%= id %>" /></div>
        </div>
        <div class="row w-100 p-2">
            <div class="col-4 text-right">First Name:</div>
        	<div class="col-4"><input type="text" name="FirstName" value="<%= firstName %>" /></div>
        </div>
        <div class="row w-100 p-2">
            <div class="col-4 text-right">Last Name:</div>
            <div class="col-4"><input type="text" name="LastName" value="<%= lastName %>" /></div>
        </div>
        <div class="row w-100 p-2">
        	<div class="col-4 text-right">Email:</div>
        	<div class="col-4"><input type="text" name="Email" value="<%= email %>" /></div>
        </div>
        <div class="row w-100 p-2">
            <div class="col-4 text-right">Program Code:</div>
           	<div class="col-4"><input type="text" name="ProgramCode" value="<%= programCode %>" /></div>
        </div>
        <div class="row w-100 p-2">
            <div class="col-4 text-right">Program Description:</div>
            <div class="col-4"><input type="text" name="ProgramDescription" value="<%= programDescription %>" /></div>
        </div>
        <div class="row w-100 p-2">
           	<div class="col-4 text-right">Year:</div>
           	<div class="col-4"><input type="number" name="Year" value="<%= year %>" /></div>
        </div>
        <div class="row w-100 p-2">
            <div class="col-4 text-right">Password:</div>
           	<div class="col-4"><input type="password" name="Password" value="<%= password %>" /></div>
        </div>
        <div class="row w-100 p-2">
           	<div class="col-4 text-right">Password:</div>
           	<div class="col-4"><input type="password" name="PasswordConfirm" value="<%= passwordConfirm %>" /></div>
        </div>

		<div class="row w-100 p-4 justify-content-center">
			<input class="btn btn-info px-4 mx-2" type="submit" value = "Log In" />
			<input class="btn btn-warning px-4 mx-2" type="reset" value = "Clear" />
		</div>
	</form>

    <p>
        If you do not have an account, please <a class="text-success font-weight-bold" href="register.jsp">register</a>.
    </p>
<%@ include file="./footer.jsp" %>