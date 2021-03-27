<% String title = "Login";%>

<%@ include file="./header.jsp" %>

<%@ page import = "demo.*" %>
<%
    String errorMessage = (String)session.getAttribute("errors");
    String login = (String)session.getAttribute("login");

     if (errorMessage == null) {
        errorMessage = "";
     } else {
        session.removeAttribute("errors");
     }

	if(login == null)
		login = "";
%>
	<h2>Login</h2>
	<p class="lead mx-auto">
	    Enter your login information below.
	</p>
	<p>
	    If you are not a student, please return to the
	   <a class="text-success font-weight-bold" href="./index.jsp">D/C Grades</a> home page.
	</p>

	<form class="bg-dark row rounded text-white p-3 col-lg-4 col-sm-6 col-xs-12 mx-auto my-3" name="Input" method="post" action="./Login" >
        <div class="text-danger h6 mx-auto col-12 justify-content-center"><%= errorMessage %></div>

        <div class="row w-100 p-2">
			<div class="col-4 text-right">Login Id:</div>
			<div class="col-4"><input type="text" name="Login" value="<%= login %>" /></div>
        </div>
        <div class="row w-100 p-2">
            <div class="col-4 text-right">Password:</div>
        	<div class="col-4"><input type="password" name="Password" value="" /></div>
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