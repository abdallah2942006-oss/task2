<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");

    String fullName = request.getParameter("fullName");
    String password = request.getParameter("password");
    String age = request.getParameter("age");
    String radioAddress = request.getParameter("radioAddress");
    String selectAddress = request.getParameter("selectAddress");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form Result</title>
</head>
<body>
    <h2>Received Information</h2>
    <p>Full Name: <%= fullName %></p>
    <p>Password: <%= password %></p>
    <p>Age: <%= age %></p>
    <p>Radio Address: <%= radioAddress %></p>
    <p>Select Address: <%= selectAddress %></p>
    <a href="form.jsp">Back to Form</a>
</body>
</html>
