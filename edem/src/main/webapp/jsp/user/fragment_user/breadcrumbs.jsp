<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="breadcrumbs.about" var="about" />
<fmt:message key="breadcrumbs.view_actions" var="view_actions" />
<fmt:message key="breadcrumbs.view_animals" var="view_animals" />

<ul class="breadcrumbs">
	<li>
		<form action="view_actions" method="post">
			<input type="hidden" name="command" value="view_actions" /> 
			<input type="submit" name="submit" value="${view_actions }" class="button" />
		</form>
	</li>
	<li>
		<form action="view_animals" method="post">
			<input type="hidden" name="command" value="view_animals" /> 
			<input type="submit" name="submit" value="${view_animals }" class="button" />
		</form>
	</li>
	<li>
		<form action="about" method="post">
			<input type="hidden" name="command" value="about" /> 
			<input type="submit" name="submit" value="${about }" class="button" />
		</form>
	</li>
</ul>