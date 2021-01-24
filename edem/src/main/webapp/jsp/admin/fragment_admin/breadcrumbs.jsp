<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="breadcrumbs.view_accounts" var="view_accounts" />
<fmt:message key="breadcrumbs.view_actions" var="view_actions" />
<fmt:message key="breadcrumbs.view_animals" var="view_animals" />
<fmt:message key="breadcrumbs.add_action" var="add_action" />
<fmt:message key="breadcrumbs.add_animal" var="add_animal" />

<ul class="breadcrumbs">
	<li>
		<form action="view_accounts" method="post">
			<input type="hidden" name="command" value="view_accounts" /> 
			<input type="submit" name="submit" value="${view_accounts }" class="button" />
		</form>
	</li>
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
		<form action="visit_add_action" method="post">
			<input type="hidden" name="command" value="visit_add_action" /> 
			<input type="submit" name="submit" value="${add_action }" class="button" />
		</form>
	</li>
	<li>
		<form action="add_animal" method="post">
			<input type="hidden" name="command" value="add_animal" /> 
			<input type="submit" name="submit" value="${add_animal }" class="button" />
		</form>
	</li>
</ul>