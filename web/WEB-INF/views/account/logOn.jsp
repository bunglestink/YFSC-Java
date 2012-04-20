<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Log In</h2>
		<div class="edit-area">
			<form name="f" action="<c:url value='/j_spring_security_check' />" method="POST">
				<!--<input name="returnUrl" type="hidden" value="${returnUrl}" />-->
				<fieldset>
					<c:if test="${error == true}">
						<div>
							Invalid username or password entered.
						</div>
					</c:if>
					<div>
						<label>Username</label>
						<input name="j_username" type="text" />
					</div>
					<div>
						<label>Password</label>
						<input name="j_password" type="password" />
					</div>
					<div>
						<label></label>
						<input type="Submit" value="Log In" />
					</div>
				</fieldset>
			</form>
			<p>Don't have an account? <a href="<c:url value="/account/create.do" />">Create one in seconds!</a></p>
		</div>
    </template:put>
</template:insert>