<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="personal.title_page" var="title_page" />
<fmt:message key="personal.personal_info" var="personal_info" />
<fmt:message key="personal.name" var="name" />
<fmt:message key="personal.surname" var="surname" />
<fmt:message key="personal.email" var="email" />
<fmt:message key="personal.history" var="history" />


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
		
			<h3>${personal_info}</h3>
			<div class="article thumbnail">
			<figure style="text-align: center">
				<img src="./.${account_avatar}" alt="${account_name}" width="320" height="179" >
				</figure>
				<div class="data">
					<hr>
					<div class="content">
						<h5 class="name">${name}: ${account_name}</h5>
						<hr>
						<h5 class="name">${surname}: ${account_surname}</h5>
						<hr>
						<h5 class="name">${email}: ${account_email}</h5>
						<hr>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="fragment_user/breadcrumbs_account.jsp" />
	</section>
	<footer class="footer">
		<jsp:include page="../fragment/footer.jsp" />
	</footer>
	<script src="././js/jquery.js"></script>
	<script src="././js/what-input.js"></script>
	<script src="././js/foundation.js"></script>
	<script src="././js/app.js"></script>
</body>
</html>