<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<div class="edit-area">
			<form:form action="create.do">
			<fieldset>
				<div>
					<%--= Html.ValidationSummary() --%>
				</div>
				<div>
					<label>Username</label>
					<form:input path="j_username" />
				</div>
				<div>
					<label>Password</label>
					<form:password path="j_password" />
				</div>
				<div>
					<label>Password</label>
					<form:password path="j_passwordConfirm" />
				</div>
				<div>
					<label>Email Address</label>
					<form:input path="email" />
				</div>
				<div>
					<label></label>
					<input type="Submit" value="Create Account" />
				</div>
			</fieldset>
			</form:form>
		</div>
    </template:put>
</template:insert>