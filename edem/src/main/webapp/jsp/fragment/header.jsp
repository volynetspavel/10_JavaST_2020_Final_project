<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="header.header_page" var="header_page" />
<fmt:message key="header.log_out" var="log_out" />
<fmt:message key="locbutton.en" var="en_button" />
<fmt:message key="locbutton.ru" var="ru_button" />

<div class="callout primary" style="margin: 0;">
	<div class="row">
		<div class="large-6 medium-6 columns">
			<h1>${header_page}</h1>
		</div>

		<div class="large-6 medium-6 columns">
		<div class="input-group" style="margin-top: 1em;">
			<ul class="breadcrumbs">
				<li>
					<form action="language" method="post">
						<input type="hidden" name="local" value="ru_RU" />
						<input type="hidden" name="command" value="language" /> 
						<input type="hidden" name="current_path" value="/${pageContext.request.requestURL}" /> 
						<input type="submit" name="submit" value="${ru_button}" class="button" style="background: rgb(186, 180, 175);"/>
					</form>
				</li>
				<li>
					<form action="language" method="post">
						<input type="hidden" name="local" value="en_EN" /> 
						<input type="hidden" name="command" value="language" /> 
						<input type="hidden" name="current_path" value="/${pageContext.request.requestURL}" /> 
						<input type="submit" name="submit" value="${en_button}" class="button" style="background: rgb(186, 180, 175);"/>
					</form>
				</li>
				<li>
					<form action="sign_out" method="post">
					<input type="hidden" name="command" value="sign_out" />
					
						<div class="input-group-button">
							<input class="button" value="${log_out}" type="submit">
						</div>
					
					</form>
				</li>
			</ul>
			</div>
		</div>
	</div>
</div>