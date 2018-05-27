<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>

        <content tag="navbar">
            <ul>
                <li><g:link class="list" controller="home" action="index">Home</g:link></li>
                <li><g:link class="list" controller="semester" action="index">Semesters</g:link></li>
                <li><g:link class="list" controller="semester" action="show" params="[id:course.semester.id]">${course.semester.description}</g:link></li>
                <li>${course.name}</li>
            </ul>
        </content>

        <g:link class="button-primary button button-create with-icon" controller="grade" action="create" params="[courId: course.id]"><g:icon name="document"/>add grade</g:link>

        <h1>${course.name}<g:link class="icon-only" action="edit" resource="${course}"><g:icon name="edit"/></g:link></h1>

        <h3 class="avg"><g:avg pre="avg" val="${course.average()}"/></h3>

        <h3>Exams</h3>
        <g:render template="grades" model="[grades: normalGrades, course: course, preAverave: preAverage]" />

        <h3>Finals</h3>
        <g:render template="grades" model="[grades: finalGrades, course: course, preAverage: false]" />
    </body>
</html>
