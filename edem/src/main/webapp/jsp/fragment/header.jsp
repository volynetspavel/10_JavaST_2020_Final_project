<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="header.header_page" var="header_page" />
<fmt:message key="header.log_out" var="log_out" />

<div class="callout primary" style="margin: 0;">
	<div class="row">
		<div class="large-6 medium-6 columns">
			<h1>${header_page}</h1>
		</div>

		<div class="large-6 medium-6 columns">
			<form action="sign_out" method="post">
				<input type="hidden" name="command" value="sign_out" />
				<div class="input-group" style="margin-top: 1em;">
					<div class="input-group-button">
						<input class="button" value="Log out" type="submit">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>