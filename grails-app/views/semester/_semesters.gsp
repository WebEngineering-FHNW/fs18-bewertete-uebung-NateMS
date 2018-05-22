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