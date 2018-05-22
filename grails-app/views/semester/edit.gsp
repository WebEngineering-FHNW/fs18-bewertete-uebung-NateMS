<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'semester.label', default: 'Semester')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
    <content tag="navbar">
        <ul>
            <li><g:link class="list" controller="home" action="index">Home</g:link></li>
            <li><g:link class="list" action="index">Semesters</g:link></li>
            <li>${semester.description}</li>
        </ul>
    </content>

    <g:link class="button-primary button button-create with-icon" controller="grade" action="create"><g:icon name="document"/>add grade</g:link>

    <g:form action="update" method="POST" class="edit-form">
        <input type="text" name="description" placeholder="description" value="${semester.description}">
        <g:submitButton name="save" class="save" value="save" />
    </g:form>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:render template="courses" model="[semester: semester]" />
</body>
</html>
