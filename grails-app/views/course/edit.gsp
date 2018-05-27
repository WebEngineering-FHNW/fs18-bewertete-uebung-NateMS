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
        <li><g:link class="list" controller="course" action="show" params="[id:course.id]">${course.name}</g:link></li>
        <li>edit ${course.name}</li>
    </ul>
</content>

<g:form action="update" method="PUT" class="edit-form">
    <input type="text" name="name" placeholder="name" value="${course.name}">
    <input type="hidden" name="id" value="${course.id}">
    <input type="hidden" name="semesterId" value="${course.semester.id}">
    <g:submitButton name="save" class="save" value="save" />
</g:form>

<h3 class="avg"><g:avg pre="avg" val="${course.average()}"/></h3>

<h3>Exams</h3>
<g:render template="grades" model="[grades: normalGrades, course: course, preAverave: preAverage]" />

<h3>Finals</h3>
<g:render template="grades" model="[grades: finalGrades, course: course, preAverage: false]" />
</body>
</html>
