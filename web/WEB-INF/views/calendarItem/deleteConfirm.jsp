<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
	<h2>Delete Calendar Item Confirmation</h2>
	<p>Are you sure you wish to delete the following calendar item?</p>
	<div class="edit-area">
		<form:form action="delete.do" modelAttribute="calendarItem">
		<fieldset>
			<div>
				<label>ID:</label>
				${calendarItem.id}
				<form:hidden path="id" />
			</div>
			<div>
				<label>Days:</label>
				${calendarItem.days}
			</div>
			<div>
				<label>Saturday Notes:</label>
				${calendarItem.saturdayNotes}
			</div>
			<div>
				<label>Sunday Notes:</label>
				${calendarItem.sundayNotes}
			</div>
			<div>
				<label></label>
				<a href="<c:url value="index.do" />?id=${calendarItem.registrationTerm.id}">Cancel</a> or 
				<input type="Submit" value="Delete" />
			</div>
		</fieldset>
		</form:form>
	</div>
    </template:put>
</template:insert>