<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="Grade" />
    <title>Gradr - New Grade</title>
</head>
<body>

<content tag="navbar">
    <ul>
        <li><g:link class="list" controller="home" action="index">Home</g:link></li>
        <li>New Grade</li>
    </ul>
</content>

<h1>New Grade</h1>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:hasErrors bean="${this.grade}">
    <ul class="errors" role="alert">
        <g:eachError bean="${this.grade}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>
<section class="create-section">
    <g:form action="save" method="POST" class="create-form">
        <input type="text" name="grade" placeholder="grade" value="${grade.grade}">
        <input type="text" name="weight" placeholder="weight" value="${grade.weight}">
        <g:submitButton name="create" class="save" value="save" />
    </g:form>
</section>
</body>
</html>