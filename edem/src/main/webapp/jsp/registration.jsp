<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text" />

<fmt:message key="registration.title_page" var="title_page" />
<fmt:message key="registration.your_surname" var="your_surname" />
<fmt:message key="registration.your_name" var="your_name" />
<fmt:message key="registration.your_avatar" var="your_avatar" />
<fmt:message key="registration.your_email" var="your_email" />
<fmt:message key="registration.password" var="password" />
<fmt:message key="registration.confirm_password" var="confirm_password" />
<fmt:message key="registration.create_account" var="create_account" />

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${title_page}</title>
    
    <link rel="stylesheet" href="./font/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="./css/style.css" type="text/css" >
</head>
<body>
    <div class="main">
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form" enctype="multipart/form-data">
                    	<input type="hidden" name="command" value="registration">
                    	
                        <h2 class="form-title">${title_page}</h2>
						<div class="form-group">
                            <input type="text" class="form-input" name="surname" value="" placeholder="${your_surname}"/>
                            <span style="color:red;">${errorSurname}</span>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" name="name" value="" placeholder="${your_name}"/>
                            <span style="color:red;">${errorName}</span>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-input" name="email" value="" placeholder="${your_email}"/>
                            <span style="color:red;">${errorEmail}</span>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" name="password" value="" placeholder="${password}"/>
                            <span style="color:red;">${errorPassword}</span>
                        	<span style="color:red;">${errorPassAndConfirmedPassMessage}</span>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" name="confirmed_password" value="" placeholder="${confirm_password}"/>
                            <span style="color:red;">${errorPassword}</span>
                        	<span style="color:red;">${errorPassAndConfirmedPassMessage}</span>
                        </div>
                        <div class="form-group">
							<div class="common-row">
							    <input type="file" name="file" id="file" style="visibility:hidden;"  /> <br/>
							    <label class="form-input" for="file" style="background: rgb(186, 218, 188);">
									${your_avatar}
								</label>
							</div>
                            <input type="hidden" value="d:\programming\JAVA WEB DEVELOPMENT\workspace\10_JavaST_2020_Final_project\edem\src\main\webapp\img\avatar\" name="destination"/>
                            <span style="color:red;">${errorAvatar}</span>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="${create_account}"/>
                        </div>
                    </form>
                </div>
            </div>
        </section>

    </div>
</body>
</html>