<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<FName></FName>
</head>
<body>
<link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
	<center>
		<h1>Users Citadel</h1>
        <h2>
        	<a href="new">Add New User</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="NNN">List All Users</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Password Encrypted BCrypt</th>
                <th>Surname</th>
                <th>Age</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${listUsers}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.FName}" /></td>
                    <td><c:out value="${user.SName}" /></td>
                    <td><c:out value="${user.age}" /></td>
                    <td>
                    	<a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${user.id}' />">Delete</a>
                    </td>
                    <td><c:out value="${user.role}" /></td>
                </tr>
            </c:forEach>
            <th>Role of <c:out value='${pageContext["request"].userPrincipal.principal.getUsername()}' />: </th>
            <td>
                <input type="text" name="role" size="45"
                       value="<c:out value='${pageContext["request"].userPrincipal.principal.getAuthorities()}' />"
                />
            </td>
        </table>
    </div>	
</body>
</html>
