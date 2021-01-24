<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
					<img src="./.${animal.logo}" alt="${animal.name}" />
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
								<form name="login-form" class="login-form" action="take_animal" method="post">
									<input type="hidden" name="command" value="take_animal" /> 
									<input type="hidden" name="id_animal" value="${animal.id}" /> 
									<input type="submit" name="submit" value="${add}" class="button" />
									<span class="header" style="color: green;">${animal_chose}</span>
									<span class="header" style="color: red;">${animal_chose_error}</span>
								</form>
							</c:if>
							<c:if test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
								<form name="login-form" class="login-form" action="update_animal" method="post">
									<input type="hidden" name="command" value="update_animal" /> 
									<input type="hidden" name="id_animal" value="${animal.id}" />
									<input type="submit" name="submit" value="${update}" class="button" />
								</form>
								<form name="login-form" class="login-form" action="delete_animal" method="post">
									<input type="hidden" name="command" value="delete_animal" /> 
									<input type="hidden" name="id_animal" value="${animal.id}" />
									<input type="submit" name="submit" value="${delete}" class="button" />
								</form>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>

			<ul class="pagination text-center" role="navigation" aria-label="Pagination">
				<li class="pagination-previous"><a href="#" aria-label="Previous">Previous</a></li>
				<li><a href="#" aria-label="Page 1">1</a></li>
				<li class="ellipsis"></li>
				<li><a href="#" aria-label="Page 13">13</a></li>
				<li><a href="#" aria-label="Page 14">14</a></li>
				<li class="current">15</li>
				<li><a href="#" aria-label="Page 16">16</a></li>
				<li><a href="#" aria-label="Page 17">17</a></li>
				<li class="ellipsis"></li>
				<li><a href="#" aria-label="Page 43">43</a></li>
				<li class="pagination-next"><a href="#" aria-label="Next page">Next</a></li>
			</ul>
		</div>
	</section>
	<footer class="footer">
		<jsp:include page="fragment/footer.jsp" />
	</footer>
</body>
</html>


