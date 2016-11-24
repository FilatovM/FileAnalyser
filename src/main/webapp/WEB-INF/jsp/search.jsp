<html>
<head>
    <title>Search Page</title>
</head>

<body>

<h1>NCEC File Analyser</h1>

<form method="get" action="load-file">
    <h2>Requierment Search:</h2>
    <table>
        <td>Parameter search</td>
        <tr>
            <td>
                <select name = "possible-result">
                    <option value = "SUCCESS">Success</option>
                    <option value = "Title">Title</option>
                    <option value = "Text">Text</option>
                    <option value = "Comment">Comment</option>
                    <option value = "Done">Done</option>
                    <option value = "Time">Time</option>
                    <option value = "Date">Date</option>
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