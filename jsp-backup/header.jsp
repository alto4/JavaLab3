<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="webd4201.altons.*" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%= title %> | D/C Grades</title>
       <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
       <link rel="stylesheet" href="./css/style.css" />
    </head>
    <body class="bg-light">
        <div class="navbar navbar-expand-lg navbar-dark bg-dark">
          <a class="navbar-brand" href="./index.jsp"><span class="text-success">D/C </span>Grades</a>
          <button class="navbar-toggler" type="button">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
            <% if (session.getAttribute("student") == null) { %>
                <li class="nav-item mx-3"><a class="text-white" href="login.jsp">Login</a></li>
                <li class="nav-item mx-3"><a class="text-white" href="register.jsp">Register</a></li>
            <% } else { %>
                <li class="nav-item mx-3"><a class="text-white" href="dashboard.jsp">Dashboard</a></li>
                <li class="nav-item mx-3"><a class="text-white" href="update.jsp">Update</a></li>
                <li class="nav-item mx-3"><a class="text-white" href="./Logout">Logout</a></li>
            <% } %>
            </ul>
          </div>
        </div>
    <!-- end of header.jsp -->


    <div class="my-5">
        <div class="container p-4 text-center">