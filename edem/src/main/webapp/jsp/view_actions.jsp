<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="actions.title_page" var="title_page" />
<fmt:message key="actions.impact" var="impact" />
<fmt:message key="actions.count_comments" var="count_comments" />
<fmt:message key="actions.watch" var="watch" />
<fmt:message key="actions.update" var="update" />
<fmt:message key="actions.delete" var="delete" />


<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>${title_page}</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/foundation.css">
<link rel="stylesheet" type="text/css" href="css/foundation-icons.css">
<link rel="stylesheet" type="text/css" href="css/app.css">
</head>
<body>
	<header>
		<jsp:include page="fragment/header.jsp" />
	</header>
	<div class="row">
		<nav role="navigation" class="large-12 small-6 medium-8 columns">
			<c:choose>
				<c:when test="${sessionScope.user.role eq 'USER'}">
					<jsp:include page="user/fragment_user/breadcrumbs.jsp" />
				</c:when>
				<c:otherwise>
					<jsp:include page="admin/fragment_admin/breadcrumbs.jsp" />
				</c:otherwise>
			</c:choose>
		</nav>
	</div>
	<section class="row">
		<div id="mainContent" class="large-10 columns" style="min-height: 600px;">
			<c:forEach items="${actions}" var="action">
				<div class="article thumbnail">
					<figure style="text-align: center">
						<img src="./.${action.logo}" alt="${action.title}" width="650" height="425"/>
					</figure>
					<div class="data">
						<h3>${action.title}</h3>
						<ul class="vertical large-horizontal menu">
							<li><i class="fi-eye"></i>${impact} -${action.co2} CO2</li>
							<li><i class="fi-comments"></i>${count_comments} ${action.comments}</li>
						</ul>
						<hr>
						<div class="desc">
							<p>${action.desc}</p>
							<c:choose>
								<c:when test="${sessionScope.user.role eq 'USER'}">
									<form name="login-form" class="login-form" action="watch_action" method="post">
										<input type="hidden" name="command" value="watch_action" /> 
										<input type="hidden" name="id_action" value="${action.id}" /> 
										<input type="submit" name="submit" value="${watch}" class="button" />
									</form>
								</c:when>
								<c:otherwise>
									<form name="login-form" class="login-form" action="update_action" method="post">
										<input type="hidden" name="command" value="update_action" /> 
										<input type="hidden" name="id_action" value="${action.id}" /> 
										<input type="submit" name="submit" value="${update}" class="button" />
									</form>
									<form name="login-form" class="login-form" action="delete_action" method="post">
										<input type="hidden" name="command" value="delete_action" /> 
										<input type="hidden" name="id_action" value="${action.id}" /> 
										<input type="submit" name="submit" value="${delete}" class="button" />
									</form>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</div>
			</c:forEach>

			<tags:pagination pagination="${pagination }"/>
		</div>
		
		<c:if test="${sessionScope.user.role eq 'USER'}">
			<jsp:include page="user/fragment_user/breadcrumbs_account.jsp" />
		</c:if>
		
	</section>
	<footer class="footer">
		<jsp:include page="fragment/footer.jsp" />
	</footer>
	<script src="./js/jquery.js"></script>
	<script src="./js/what-input.js"></script>
	<script src="./js/foundation.js"></script>
	<script src="./js/app.js"></script>
</body>
</html>


