<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="sign_in.autorization" var="autorization" />
<fmt:message key="sign_in.input_data" var="input_data" />
<fmt:message key="sign_in.registration" var="registration" />
<fmt:message key="sign_in.sign_in" var="sign_in" />
<fmt:message key="locbutton.en" var="en_button" />
<fmt:message key="locbutton.ru" var="ru_button" />

<html>
<head>
<title>${sign_in}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="./css/sign_in/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="clr"></div>
	<div id="wrapper">
		<div class="user-icon"></div>
		<div class="pass-icon"></div>

		<form name="login-form" class="login-form" action="sign_in" method="post">
			<input type="hidden" name="command" value="sign_in" />
			<div class="header">
				<h1>${autorization}</h1>
				<span>${input_data}</span>
			</div>

			<div class="content">
				<input name="email" type="text" class="input username" value="" onfocus="this.value=''" /> 
				<input name="password" type="password" class="input password" value="" onfocus="this.value=''" />
			</div>
			<span class="header" style="color: red;">${errorInputData}</span>
			<div class="footer">
				<input type="submit" name="submit" value="${sign_in}" class="button" />
			</div>
		</form>
		<div class="login-form">
			<div class="footer">
				<form action="registration" method="post">
					<input type="hidden" name="command" value="registration" />
					<input type="submit" name="submit" value="${registration}" class="register" />
				</form>
			</div>
		</div>
		<div class="login-form">
			<div  class="footer" style="background: #80ff80;">
				<form action="language" method="post">
					<input type="hidden" name="local" value="ru_RU" />
					<input type="hidden" name="command" value="language" />
					<input type="hidden" name="current_path" value="/${pageContext.request.requestURL}"/>
					<input type="submit" name="submit" value="${ru_button}" class="register" />
				</form>
				<form action="language" method="post">
					<input type="hidden" name="local" value="en_EN" />
					<input type="hidden" name="command" value="language" />
					<input type="hidden" name="current_path" value="/${pageContext.request.requestURL}"/>
					<input type="submit" name="submit" value="${en_button}" class="register" />
				</form>
			</div>
		</div>
	</div>
	<div class="gradient"></div>
</body>
</html>