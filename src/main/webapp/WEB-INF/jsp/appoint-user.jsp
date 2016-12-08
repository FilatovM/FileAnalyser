<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Appoint User Page</title>
</head>

<body>

<h1>NCEC File Analyser</h1>

<form method="get" action="add-role">
    <h2>Grant access to user:</h2>
    <a href="/home"> Home</a> <br>
    <table>
        <tr>
            <td><b>Username</b></td>
            <td>
                <label>
                    <input type="text" name="username"/>
                </label>
            </td>
        </tr>
        <td>User role</td>
        <tr>
            <td>
                <label>
                    <select name="role">
                        <option value="ROLE_MODER">Moderator</option>
                        <option value="ROLE_ADMIN">Administrator</option>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "Submit"/></td>
        </tr>
    </table>
</form>

</body>
</html>