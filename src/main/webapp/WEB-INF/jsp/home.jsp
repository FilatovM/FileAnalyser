<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login Page</title>
</head>

<body>

<h1>NCEC File Analyser</h1>

<div id="home_menu">
    <h2>Menu:</h2>
    <a href="<c:url value="/logout"/>">Log out</a> <br>
    <a href="/review"> Review</a> <br>
    <a href="/search"> Search</a> <br>
    <sec:authorize access="hasRole('ROLE_MODER')">
    <a href="/load"> Load</a> <br>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/add-user"> Add User</a> <br>
    <a href="/appoint-user"> Appoint User</a> <br>
    </sec:authorize>
</div>

</body>
</html>