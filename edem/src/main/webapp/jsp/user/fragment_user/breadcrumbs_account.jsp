<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" />

<fmt:message key="breadcrumbs_account.personal" var="personal" />
<fmt:message key="breadcrumbs_account.history" var="history" />

<div class="columns large-2 show-for-large right" data-sticky-container>
	<div class="sticky categories show-for-large" data-sticky data-anchor="mainContent">
		<table>
			<tbody>
				<tr>
					<td>
						<form action="account" method="post">
							<input type="hidden" name="command" value="account" /> 
							<input type="submit" name="submit" value="${personal }" class="button" style="background: transparent; color: black"/>
						</form>
					</td>
				</tr>
				<tr>
					<td>						
						<form action="history" method="post">
							<input type="hidden" name="command" value="history" /> 
							<input type="submit" name="submit" value="${history }" class="button" style="background: transparent; color: black"/>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>