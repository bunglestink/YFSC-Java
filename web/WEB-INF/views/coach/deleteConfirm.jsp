<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
	<h2>Delete Coach Confirmation</h2>
	<p>Are you sure you wish to delete the following coach?</p>
	<hr />
	<div class="edit-area">
		<form:form action="delete.do" modelAttribute="coach">
			<div>
				<label>ID:</label>
				${coach.id}
				<form:hidden path="id" />
			</div>
			<div>
				<label>Name:</label>
				${coach.name}
			</div>
			<div>
				<label>Primary Info:</label>
				${coach.primaryInfo}
			</div>
			<div>
				<label>SecondaryInfo:</label>
				${coach.secondaryInfo}
			</div>
			<div>
				<label></label>
				<a href="<c:url value="/coach/index.do" />">Cancel</a> or 
				<input type="Submit" value="Delete" />
			</div>
		</form:form>
	</div>
    </template:put>
</template:insert>