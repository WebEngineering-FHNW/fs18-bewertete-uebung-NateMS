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

        <div id="show-course" class="content scaffold-show" role="main">
            <h1>${course.name}<g:link class="icon-only" action="edit" resource="${course}"><g:icon name="edit"/></g:link></h1>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:if test="${course.grades.size()}">
                <h3 class="avg"><g:avg pre="avg" val="${course.average()}"/></h3>
                <table class="table-list">
                    <thead>
                    <tr>
                        <th>grade</th>
                        <th>weight</th>
                        <th>isFinal</th>
                        <th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each var="grade" in="${course.grades}">
                        <tr>
                            <td>${grade.grade}</td>
                            <td>${grade.weight}</td>
                            <td>${grade.isFinal}</td>
                            <td>
                                <g:form class="delete-form" resource="${grade}" method="DELETE">
                                    <g:submit/>
                                </g:form>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:if>
        </div>
    </body>
</html>
