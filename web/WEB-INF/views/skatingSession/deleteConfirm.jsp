<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
	<h2>Delete Skating Session Confirmation</h2>
	<p>Are you sure you wish to delete the following skating session?</p>
	<div class="edit-area">
		<form:form action="delete.do" modelAttribute="skatingSession">
		<fieldset>
			<div>
				<label>ID:</label>
				${skatingSession.id}
				<form:hidden path="id" />
			</div>
			<div>
				<label>Start Date:</label>
				${skatingSession.startDate}
			</div>
			<div>
				<label>Weeks Duration</label>
				${skatingSession.weeksDuration}
			</div>
			<div>
				<label>Day of Week:</label>
				${skatingSession.dayOfWeek}
			</div>
			<div>
				<label>Start Time:</label>
				${skatingSession.startTime}
			</div>
			<div>
				<label>End Time:</label>
				${skatingSession.endTime}
			</div>
			<div>
				<label>Total Cost:</label>
				${skatingSession.totalCost}
			</div>
			<div>
				<label>Description:</label>
				${skatingSession.description}
			</div>
			
			<div>
				<label></label>
				<a href="<c:url value="index.do" />?id=${skatingSession.registrationTerm.id}">Cancel</a> or 
				<input type="Submit" value="Delete" />
			</div>
		</fieldset>
		</form:form>
	</div>
    </template:put>
</template:insert>