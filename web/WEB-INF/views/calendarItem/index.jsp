<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Calendar Items for ${registrationTerm.termName}</h2>
		<h5><a href="<c:url value="/registrationTerm/index.do" />">Back to registration terms</a></h5>
		<h3><a href="<c:url value="create.do" />?id=${registrationTerm.id}">Create a New Calendar Item</a></h3>
		<table>
			<tr>
				<th>ID</th>
				<th>Days</th>
				<th>Saturday Notes</th>
				<th>Sunday Notes</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>	
			<c:forEach items="${calendarItems}" var="calendarItem">
				<tr>
					<td>${calendarItem.id}</td>
					<td>${calendarItem.days}</td>
					<td>${calendarItem.saturdayNotes}</td>
					<td>${calendarItem.sundayNotes}</td>
					<td><a href="<c:url value="edit.do" />?id=${calendarItem.id}">Edit</a></td>
					<td><a href="<c:url value="deleteConfirm.do" />?id=${calendarItem.id}">Delete</a></td>
				</tr>	
			</c:forEach>
		</table>
    </template:put>
</template:insert>