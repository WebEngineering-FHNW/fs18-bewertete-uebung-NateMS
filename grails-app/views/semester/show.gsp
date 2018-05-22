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
        <div id="show-semester" class="content scaffold-show" role="main">

            <h1>${semester.description}</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:render template="courses" model="[semester: semester]" />

            <g:form resource="${this.semester}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.semester}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
