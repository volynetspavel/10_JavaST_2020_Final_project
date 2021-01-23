<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="new_comment.new_comment" var="new_comment" />
<fmt:message key="new_comment.submit" var="submit" />

<div id="new-comment-container" class="media-object comment-item new-comment">
	<div class="media-object-section">
		<img class="avatar" src="img/no_avatar.png" alt="anonym" /><br/>
		<a href="javascript:gpLogout();" class="logout">Logout</a>
	</div>
	<div class="media-object-section" style="width: 100%;">
		<textarea id="comment-content" name="comment-content" placeholder="${new_comment}"></textarea>
		<span class="form-error">Comment is required. </span>
		<a href="javascript:submitComment();" class="float-right button" style="margin:7px 0 0;">${submit}</a>
	</div>
</div>

<div class="reveal" id="sigin-form" data-reveal>
	<h4>You should login before leaving messages</h4>
    <div class="row">
    	<div class="g-signin2 small-centered columns" style="width:200px;" data-onsuccess="onSignIn"></div>
    </div>
    <button class="close-button" data-close aria-label="Close modal" type="button">
        <span aria-hidden="true">&times;</span>
    </button>
</div>