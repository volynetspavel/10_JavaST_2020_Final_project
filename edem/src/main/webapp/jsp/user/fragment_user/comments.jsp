<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<c:forEach var="comment" items="${list_comments }">
	<div class="media-object comment-item" data-id-comment="${comment.id}">
		<div class="media-object-section">
			<img src="./.${comment.account.avatar}" 
			alt="${comment.account.name }" class="avatar" />
		</div>
		<div class="media-object-section">
			<h5 class="name">${comment.account.name }</h5>
			<p>${comment.content }</p>
			<p class="meta">
				<small><fmt:formatDate value="${comment.created }" type="time" timeStyle="medium" /></small>
			</p>
		</div>
	</div>
</c:forEach>