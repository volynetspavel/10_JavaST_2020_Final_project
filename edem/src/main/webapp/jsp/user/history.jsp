<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="history.title_page" var="title_page" />
<fmt:message key="history.animal" var="animal" />
<fmt:message key="history.amount_co2" var="amount_co2" />
<fmt:message key="history.reduced_co2" var="reduced_co2" />
<fmt:message key="history.actions" var="actions" />


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
			<h3>${title_page}</h3>
			<div class="article thumbnail">
				<figure style="text-align: center">
					<h4>${animal}</h4>
					<img src="./.${animal_logo}" alt="${animal_name}" width="650" height="425">
				</figure>
				<div class="data">
					<figure style="text-align: center">
						<h4>${animal_name}</h4>
					</figure>
					<hr>
					<div class="content">
						<p>${description}</p>
						<p>${content}</p>
					</div>
					<br>
					<hr>
					<p style="color: green">${amount_co2}: ${animal_co2}</p>
					<p style="color: green">${reduced_co2}: ${total_co2}</p>
					<hr>
					<figure style="text-align: center">
						<h4>${actions}</h4>
					</figure>
					<div class="comments">
						<div id="comments-list-container" >
							<c:forEach var="action" items="${list_actions }">
								<div class="media-object comment-item" data-id-comment="${action.id}">
									<div class="media-object-section">
										<img src="./.${action.logo}" alt="${action.title }" class="avatar" />
									</div>
									<div class="media-object-section">
										<h5 class="name">${action.title }</h5>
										<p>${action.desc }</p>
									</div>
								</div>
							</c:forEach>							
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
	<script src="././js/jquery.js"></script>
	<script src="././js/what-input.js"></script>
	<script src="././js/foundation.js"></script>
	<script src="././js/app.js"></script>
</body>
</html>