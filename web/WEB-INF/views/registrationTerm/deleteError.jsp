<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Delete Error</h2>
		<p>This Registration Term cannot be deleted because it has calendar items.</p>
		<div>
			<a href="<c:url value="/registrationTerm/index.do" />">Back</a>
		</div>
		<hr />
    </template:put>
</template:insert>