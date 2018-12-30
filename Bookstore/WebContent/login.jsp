<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: парык
  Date: 28.12.2018
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please login</title>
</head>
<body>
<form action="" method="post">

    Please enter your username
    <input type="text" name="un"/><br>

    <c:out value="${messages}" /><br>

    <input type="submit" value="submit">

</form>
</body>
</html>
