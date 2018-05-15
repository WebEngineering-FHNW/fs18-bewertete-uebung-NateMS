<html>
<head>
    <meta name="layout" content="main" />
    <title>Login</title>
</head>
<body>
<div class="body">
    <h1>Login</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:form controller="user" action="authenticate" method="post" >
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td class="name">
                        <label for="email">Login:</label>
                    </td>
                    <td>
                        <input type="email" id="email" name="email" />
                    </td>
                </tr>
                <tr class="prop">
                    <td class="name">
                        <label for="password">Password:</label>
                    </td>
                    <td>
                        <input type="password" id="password" name="password" />
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button">
                <input class="save" type="submit" value="Login" />
            </span>
        </div>
    </g:form>
</div>
</body>
</html>