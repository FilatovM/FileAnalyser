<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Add User Page</title>
</head>

<body>

<h1>NCEC File Analyser</h1>

<form method="get" action="load-user">
    <h2>Add user:</h2>
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
        <tr>
            <td><b>Password</b></td>
            <td>
                <label>
                    <input type="text" name="password"/>
                </label>
            </td>
        </tr>
        <td>User role</td>
        <tr>
            <td>
                <label>
                    <select name="role_idx">
                        <option value=1>User</option>
                        <option value=2>Moderator</option>
                        <option value=3>Administrator</option>
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