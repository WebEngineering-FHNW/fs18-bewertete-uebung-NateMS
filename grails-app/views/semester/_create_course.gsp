<section class="create-section">
    <g:form controller="course" action="save" method="POST" class="create-form">
        <p class="form-title">add a new course to this semester</p>
        <input type="text" name="name" placeholder="name">
        <g:hiddenField name="semester" value="${semester}" />
        <g:submitButton name="create" class="save" value="save" />
    </g:form>
</section>