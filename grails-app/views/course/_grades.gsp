<g:if test="${grades.size()}">
    <table class="table-list grades-table">
        <thead>
        <tr>
            <th>weight</th>
            <th>grade</th>
            <th>
        </tr>
        </thead>
        <tbody>
        <g:each var="grade" in="${grades}">
            <tr>
                <td>${grade.weight}</td>
                <td>${grade.grade}</td>
                <td>
                    <g:form class="delete-form" resource="${grade}" method="DELETE">
                        <g:submit/>
                    </g:form>
                </td>
            </tr>
        </g:each>
        <g:if test="${preAverage}">
            <tr>
                <td><b>Average</b></td>
                <td><b>${preAverage}</b></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</g:if>
<g:else>
    <h4>no grades submitted</h4>
</g:else>