<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search result page</title>
</head>
<body>
<h1>NCEC File Analyser</h1>

<div id="search-result-page">
    <h2>Search result:</h2>
    <a href="/home"> Home</a> <br>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Text</th>
            <th>Comment</th>
            <th>Done</th>
            <th>Time</th>
            <th>Date</th>
        </tr>
        <c:forEach var="req" items = "${reqs}">
            <tr>
                <td>${req.id}</td>
                <td>${req.text}</td>
                <td>${req.title}</td>
                <td>${req.comment}</td>
                <td>${req.done}</td>
                <td>${req.time}</td>
                <td>${req.date}</td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
