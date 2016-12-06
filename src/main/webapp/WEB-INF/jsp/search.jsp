<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Search Page</title>
</head>

<body>

<h1>NCEC File Analyser</h1>

<form method="get" action="search-reqs">
    <h2>Requierment Search:</h2>
    <a href="/home"> Home</a> <br>
    <table>
        <td>Parameter search</td>
        <tr>
            <td>
                <select name = "parameter">
                    <option value = "title">Title</option>
                    <option value = "text">Text</option>
                    <option value = "comment">Comment</option>
                    <option value = "done">Done</option>
                    <option value = "time">Time</option>
                    <option value = "date">Date</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><b>Contains</b></td>
            <td><input type = "text" name = "contains"/></td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "Find Requierment"/></td>
        </tr>
    </table>
</form>

</body>
</html>