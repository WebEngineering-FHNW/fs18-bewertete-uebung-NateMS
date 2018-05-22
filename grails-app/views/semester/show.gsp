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

        <div id="show-semester" class="content scaffold-show" role="main">

            <h1>${semester.description}<g:link class="icon-only" action="edit" resource="${semester}"><g:icon name="edit"/></g:link></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:render template="courses" model="[semester: semester]" />

        </div>
    </body>
</html>
