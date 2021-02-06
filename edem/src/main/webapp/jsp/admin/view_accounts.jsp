<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="accounts.title_page" var="title_page" />
<fmt:message key="accounts.email_field" var="email_field" />
<fmt:message key="accounts.delete" var="delete" />

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
			<jsp:include page="fragment_admin/breadcrumbs.jsp" />
		</nav>
	</div>
	<section class="row">
		<div id="mainContent" class="large-10 columns" style="min-height: 600px;">
			<c:forEach items="${accounts}" var="account">
				<c:if test="${account.user.role eq 'USER'}">
					<div class="article thumbnail">
						<figure style="text-align: center">
							<img src="./.${account.avatar}" alt="${account.name}" width="400" height="350" />
						</figure>
						<div class="data">
							<h3>${account.surname}</h3>
							<h3>${account.name}</h3>
							<ul class="vertical large-horizontal menu">
								<li><i class="fi-eye"></i>${email_field} ${account.user.email}</li>
							</ul>
							<hr>
							<form name="login-form" class="login-form" action="delete_account" method="post">
								<input type="hidden" name="command" value="delete_account" /> 
								<input type="hidden" name="id_account" value="${account.user.id}" /> 
								<input type="submit" name="submit" value="${delete}" class="button" />
							</form>
						</div>
					</div>
				</c:if>
			</c:forEach>

		<tags:pagination pagination="${pagination }"/>
		</div>
	</section>
	<footer class="footer">
		<jsp:include page="../fragment/footer.jsp" />
	</footer>
</body>
</html>


