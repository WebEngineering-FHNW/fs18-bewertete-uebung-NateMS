<g:if test="${courses?.size()}">
    <h3 class="avg"><g:avg pre="avg" val="${semester.average()}"/></h3>
    <table class="table-list">
        <thead>
        <tr>
            <th>course</th>
            <th>nr. of grades</th>
            <th>avg</th>
            <th>
        </tr>
        </thead>
        <tbody>
        <g:each var="course" in="${courses}">
            <tr>
                <td><g:link class="" controller="course" action="show" params="[id:course.id]">${course.name}</g:link></td>
                <td>${course.grades?.size()}</td>
                <td><g:avg pre="avg" val="${course.average()}"/></td>
                <td>
                    <g:form class="delete-form" resource="${course}" method="DELETE">
                        <g:submit/>
                    </g:form>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <h4>there are no courses in this semester</h4>
</g:else>