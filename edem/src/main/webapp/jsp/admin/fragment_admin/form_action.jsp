<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="text" />

<fmt:message key="form_action.add_new_action" var="add_new_action" />
<fmt:message key="form_action.title_action" var="title_action" />
<fmt:message key="form_action.desc" var="desc" />
<fmt:message key="form_action.content" var="content" />
<fmt:message key="form_action.co2" var="co2" />
<fmt:message key="form_action.chose_picture" var="chose_picture" />
<fmt:message key="form_action.create" var="create" />

<div class="callout">
	<h3 class="text-center">${add_new_action}</h3>
	<hr />
	<form action="add_action" method="post" data-abide novalidate class="contact-form" enctype="multipart/form-data">
		<input type="hidden" name="command" value="add_action" /> 
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<label> 
					<input type="text" required name="title" value="" placeholder="${title_action}"> 
				</label>
			</div>
		</div>
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<label> 
					<textarea name="desc" required style="height: 90px;" placeholder="${desc}"></textarea> 
				</label>
			</div>
		</div>
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<label> 
					<textarea name="content" required style="height: 120px;" placeholder="${content}"></textarea> 
				</label>
			</div>
		</div>
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<label> 
					<input type="text" required name="count_co2" placeholder="${co2}"> 
				</label>
			</div>
		</div>
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<input type="file" name="file" id="file" style="visibility: hidden;" /> 
				<label class="button float-center" for="file" style="background: gray;">${chose_picture}</label>
				<span class="text-center" style="color: blue;">${result}</span>
				<span class="text-center" style="color: red;">${result_error}</span>
			</div>
			<input type="hidden" value="d:\programming\JAVA WEB DEVELOPMENT\workspace\10_JavaST_2020_Final_project\edem\src\main\webapp\img\action\"
				name="destination" /> 
		</div>
		<div class="row  columns">
			<button class="button float-center" type="submit" value="Submit" style="background: green;">${create}</button>
		</div>
	</form>
</div>