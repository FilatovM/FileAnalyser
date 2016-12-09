<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>

<body>

<h1>NCEC File Analyser</h1>

<form method="get" action="load-file">
    <h2>Load file:</h2>
    <a href="/home"> Home</a> <br>
    <table>
        <tr>
            <td><b>File path:</b></td>
            <td>
                <label>
                    <input type="text" name="path"/>
                </label>
            </td>
        </tr>
        <tr>
            <td><b>File-DB mapping</b></td>
        </tr>
        <tr>
            <td><b>Enter field titles (keep empty to not map)</b></td>
        </tr>
        <tr>
            <th>File field</th>
            <th>DB field</th>
        </tr>
        <tr>
            <td><input type = "text" name = "id" /></td>
            <td><b>ID</b></td>
        </tr>
        <tr>
            <td><input type = "text" name = "title" /></td>
            <td><b>Title</b></td>
        </tr>
        <tr>
            <td><input type = "text" name = "text" /></td>
            <td><b>Text</b></td>
        </tr>
        <tr>
            <td><input type = "text" name = "comment" /></td>
            <td><b>Comment</b></td>
        </tr>
        <tr>
            <td><input type = "text" name = "done" /></td>
            <td><b>Done</b></td>
        </tr>
        <tr>
            <td><input type = "text" name = "time" /></td>
            <td><b>Time</b></td>
        </tr>
        <tr>
            <td><input type = "text" name = "date" /></td>
            <td><b>Date</b></td>
        </tr>

        <tr>
            <td colspan = "2"><input type = "submit" value = "Load file"/></td>
        </tr>
    </table>
</form>


</body>
</html>