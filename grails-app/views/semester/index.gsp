<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'semester.label', default: 'Semester')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:link class="button-primary button create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
        <div id="list-semester" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${semesterList}" />

            <div class="pagination">
                <g:paginate total="${semesterCount ?: 0}" />
            </div>
        </div>
    </body>
</html>