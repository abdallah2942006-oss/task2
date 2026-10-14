<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%!
    int id = 101;
    String name = "Abdullah";

    String concatenate(int id, String name) {
        return id + " " + name;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Task</title>
</head>
<body>
    <%
        String result = concatenate(id, name);
    %>

    <h2><%= result %></h2>
</body>
</html>
