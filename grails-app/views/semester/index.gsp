<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'semester.label', default: 'Semester')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <content tag="navbar">
            <ul>
                <li><g:link class="list" controller="home" action="index">Home</g:link></li>
                <li>Semesters</li>
            </ul>
        </content>

        <g:link class="button-primary button button-create with-icon" controller="grade" action="create"><g:icon name="document"/>add grade</g:link>

        <h1>My semesters</h1>

        <g:render template="semesters" model="[semesterList: semesterList, semesterCount: semesterCount]"/>

        <g:render template="create"/>
    </body>
</html>