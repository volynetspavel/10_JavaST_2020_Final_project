<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="action.title_page" var="title_page" />
<fmt:message key="action.take" var="take" />
<fmt:message key="action.impact" var="impact" />
<fmt:message key="action.count_comments" var="count_comments" />

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
		<jsp:include page="../fragment/header.jsp" />
	</header>
	<div class="row">
		<nav role="navigation" class="large-12 small-6 medium-8 columns">
			<jsp:include page="fragment_user/breadcrumbs.jsp" />
		</nav>
	</div>
	<section class="row">
		<div id="mainContent" class="large-10 columns" style="min-height: 600px;">
			<div class="article thumbnail">
				<img src="./.${logo}" alt="${title}" width="650" height="425"/>
				<div class="data">
					<%-- ----------------------------------------- action content ----------------------------------------- --%>
					<h3>${title}</h3>
					<ul class="vertical large-horizontal menu">
						<li><i class="fi-eye"></i>${impact} -${co2} CO2</li>
						<li><i class="fi-comments"></i>${count_comments} ${comments}</li>
					</ul>
					<hr />
					
					<div class="content">${content }</div>
					<form name="login-form" class="login-form" action="take_action" method="post">
						<input type="hidden" name="command" value="take_action" />  
						<input type="hidden" name="title_action" value="${title}" /> 
						<input type="submit" name="submit" value="${take}" class="button" />
					</form>		
					<span class="header" style="color:green;">${actionComplete}</span>
					<span class="header" style="color:blue;">${errorAction}</span>
					<%-- ----------------------------------------- Comments section ----------------------------------------- --%>
					
					<div class="comments">
						<jsp:include page="fragment_user/new_comment.jsp" />
						<div id="comments-list-container" data-comments-count="${comments}" data-id-article="${id }">
							<jsp:include page="fragment_user/comments.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="fragment_user/breadcrumbs_account.jsp" />
	</section>
	<footer class="footer">
		<jsp:include page="../fragment/footer.jsp" />
	</footer>
</body>
</html>