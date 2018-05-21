<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'semester.label', default: 'Semester')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <nav>
            <ul>
                <li><g:link class="list" controller="home" action="index">Home</g:link></li>
                <li>Semesters</li>
            </ul>
        </nav>
        <g:link class="button-primary button button-create with-icon" action="create"><g:icon name="plus" /><g:message code="default.new.label" args="[entityName]" /></g:link>
        <div id="list-semester" class="content scaffold-list" role="main">
            <h1>My semesters</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:if test="${semesterCount}">
                <table>
                    <tbody>
                        <g:each var="semester" in="${semesterList}">
                            <tr>
                                <td><g:link class="" action="show" params="[id:semester.id]">${semester.description}</g:link></td>
                                <td>${semester.courses.size()} Course<g:if test="${semester.courses.size() != 1}">s</g:if></td>
                                <td><g:if test="${semester.courses.size()}">5</g:if><g:else>N/A</g:else></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:if>
        </div>
    </body>
</html>