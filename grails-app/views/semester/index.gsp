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

        <g:link class="button-primary button button-create with-icon" action="create"><g:icon name="plus" />add new semester</g:link>
        <div id="list-semester" class="content scaffold-list" role="main">
            <h1>My semesters</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:if test="${semesterCount}">
                <table class="table-list">
                    <tbody>
                        <g:each var="semester" in="${semesterList}">
                            <tr>
                                <td><g:link class="" action="show" params="[id:semester.id]">${semester.description}</g:link></td>
                                <td>${semester.courses.size()} Course<g:if test="${semester.courses.size() != 1}">s</g:if></td>
                                <td><g:avg pre="avg" val="${semester.average()}"/></td>
                                <td>
                                    <g:form class="delete-form" resource="${semester}" method="DELETE">
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