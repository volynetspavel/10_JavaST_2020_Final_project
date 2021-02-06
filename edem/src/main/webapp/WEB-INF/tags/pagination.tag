<%@ tag pageEncoding="UTF-8" 	trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" 	  		uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="pagination" required="true" type="com.volynets.edem.tag.Pagination" %>

<c:if test="${pagination != null}">
<ul class="pagination text-center" role="navigation" aria-label="Pagination">
	<li class="pagination-previous${pagination.previous ? '' : ' disabled' }">
		<c:choose>
			<c:when test="${pagination.previous}">
				<form name="login-form" class="login-form" action="${pagination.previousUrl}" method="post">
					<input type="hidden" name="command" value="${pagination.command }" /> 
					<input type="submit" name="submit" value="Previous" class="button" aria-label="Previous page" />
				</form> 
			</c:when>
			<c:otherwise>
				<form name="login-form" class="login-form" action="">
					<input type="submit" name="submit" value="Previous" class="button" style="background-color: grey" />
				</form> 
			</c:otherwise>
		</c:choose>
	</li>
	
	<c:forEach var="page" items="${pagination.pages }">
		<c:choose>
			<c:when test="${page.current }"><li class="current">${page.caption }</li></c:when>
			<c:when test="${page.ellipsis }"><li class="ellipsis"></li></c:when>
			<c:otherwise>
				<li>
					<form name="login-form" class="login-form" action="${page.url}" method="post">
						<input type="hidden" name="command" value="${pagination.command }" /> 
						<input type="submit" name="submit" value="${page.caption}" class="button" />
					</form> 
				</li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
    <li class="pagination-next${pagination.next ? '' : ' disabled' }">
    	<c:choose>
			<c:when test="${pagination.next}">
				<form name="login-form" class="login-form" action="${pagination.nextUrl}" method="post">
					<input type="hidden" name="command" value="${pagination.command }" /> 
					<input type="submit" name="submit" value="Next" class="button" aria-label="Next page"/>
				</form> 
			</c:when>
			<c:otherwise>
				<form name="login-form" class="login-form" action="">
					<input type="submit" name="submit" value="Next" class="button" style="background-color: grey" />
				</form> 
			</c:otherwise>
		</c:choose>
    </li>
</ul>
</c:if>