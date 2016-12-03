<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login</title>
</head>

<body>

<h1>NCEC - File Analyser</h1>

<div id="login-box">

    <h2>Enter your Login and Password</h2>

    <c:url var="loginUrl" value="/j_spring_security_check" />
    <form name='f' action="${loginUrl}" method="post">
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='j_username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='j_password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit" /></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </form>
</div>

</body>
</html>