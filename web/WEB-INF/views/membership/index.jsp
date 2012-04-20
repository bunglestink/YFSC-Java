<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Membership</h2>
		<div class="formatted-content">
			<div>
				<a href="<c:url value="/membership/register.do" />">Signup for current Session now!</a>
			</div>
		</div>
		<div>
			<sec:authorize access="hasRole('Admin')">
				<fieldset>
					<legend>Admin</legend>
					<ul>
						<li><a href="<c:url value="/announcement/index.do" />">Announcements</a></li>
						<li><a href="<c:url value="/coach/index.do" />">Coach</a></li>
						<li><a href="<c:url value="/registrationTerm/index.do" />">Registration Terms</a></li>
					</ul>
				</fieldset>
			</sec:authorize>
		</div>
	</template:put>
</template:insert>