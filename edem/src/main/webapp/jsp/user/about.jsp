<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="about.title" var="title_page" />
<fmt:message key="about.content" var="content" />

<div class="callout">
	<h3>${title_page}</h3>
	<p>${content}</p>
</div>