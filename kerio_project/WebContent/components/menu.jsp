<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<c:choose>
			<c:when test="${fn:length(allQueries) == 0}">
				<p class="alert-warning">No queries found.</p>
			</c:when>

			<c:otherwise>
				<c:forEach items="${requestScope.allQueries}" var="query">
					<li><a href="query-datepicker?id=${query.id}">${query.name}</a></li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
</div>