<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="animals.title_page" var="title_page" />
<fmt:message key="animals.amount_co2" var="amount_co2" />
<fmt:message key="animals.update" var="update" />
<fmt:message key="animals.delete" var="delete" />
<fmt:message key="animals.add" var="add" />

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
			<c:forEach items="${animals}" var="animal">
				<div class="article thumbnail">
					<figure style="text-align: center">
						<img src="./.${animal.logo}" alt="${animal.name}" width="650" height="425"/>
					</figure>
					<div class="data">
						<h3>${animal.name}</h3>
						<ul class="vertical large-horizontal menu">
							<li><i class="fi-eye"></i>${amount_co2} ${animal.co2}</li>
						</ul>
						<hr>
						<div class="desc">
							<p>${animal.desc}</p>
							<hr>
							<p>${animal.content}</p>
							<c:if test="${sessionScope.user.role eq 'USER'}">
								<form name="login-form" class="login-form" action="view_animals" method="post">
									<input type="hidden" name="command" value="take_animal" /> 
									<input type="hidden" name="id_animal" value="${animal.id}" /> 
									<input type="submit" name="submit" value="${add}" class="button" /></br>
									<span class="header" style="color: green;">${animal_chose}</span>
									<span class="header" style="color: red;">${animal_chose_error}</span>
								</form>
							</c:if>
							<c:if test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
								<form name="login-form" class="login-form" action="view_animals" method="post">
									<input type="hidden" name="command" value="update_animal" /> 
									<input type="hidden" name="id_animal" value="${animal.id}" />
									<input type="submit" name="submit" value="${update}" class="button" />
								</form>
								<form name="login-form" class="login-form" action="view_animals" method="post">
									<input type="hidden" name="command" value="delete_animal" /> 
									<input type="hidden" name="id_animal" value="${animal.id}" />
									<input type="submit" name="submit" value="${delete}" class="button" />
								</form>
							</c:if>
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


