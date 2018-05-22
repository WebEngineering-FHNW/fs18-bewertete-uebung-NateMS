<g:if test="${semester.courses?.size()}">
    <h3>average: <g:avg val="${semester.average()}"/></h3>
    <table>
        <thead>
        <tr>
            <th>course</th>
            <th>nr. of grades</th>
            <th>avg</th>
            <th>action</th>
        </tr>
        </thead>
        <tbody>
        <g:each var="course" in="${semester.courses}">
            <tr>
                <td><g:link class="" controller="course" action="show" params="[id:course.id]">${course.name}</g:link></td>
                <td>${course.grades?.size()}</td>
                <td><g:avg val="${course.average()}"/></td>
                <td><button class="button button-delete"><g:icon name="edit"/></button><button class="button button-delete"><g:icon name="trash"/></button></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>