<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="AdditionalScripts">
		<script type="text/javascript">
				
		$(function () {
			$('form').submit(function () {
				var password = $('#password').val(),
					passwordConfirm = $('#passwordConfirm').val();
					
				if (password !== passwordConfirm) {
					UTIL.alert('Password fields must match.');
					return false;
				}
				
				return true;
			});
		});
		
		</script>
	</template:put>
	<template:put name="MainContent">
		<div class="edit-area">
			<form:form action="createSubmit.do" modelAttribute="user">
			<fieldset>
				<c:if test="${errors != null}">
					<div class="validation-summary-errors">
						<ul>
							<c:forEach items="${errors}" var="error">
								<li>${error}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<div>
					<label>Username</label>
					<form:input path="username" />
				</div>
				<div>
					<label>Password</label>
					<form:password path="password" id="password" />
				</div>
				<div>
					<label>Password</label>
					<input type="password" id="passwordConfirm" />
				</div>
				<div>
					<label>Email Address</label>
					<form:input path="email" />
				</div>
				<div>
					<label></label>
					<input type="Submit" value="Create Account" id="formSubmit" />
				</div>
			</fieldset>
			</form:form>
		</div>
    </template:put>
</template:insert>