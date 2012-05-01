<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Skating Sessions for ${registrationTerm.termName}</h2>
		<h5><a href="<c:url value="/registrationTerm/index.do" />">Back to registration terms</a></h5>
		<h3><a href="<c:url value="create.do" />?id=${registrationTerm.id}">Create a New Skating Session</a></h3>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Start Date</th>
				<th>Weeks Duration</th>
				<th>Day Of Week</th>
				<th>Start Time</th>
				<th>End Time</th>
				<th>Cost</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>	
			<c:forEach items="${skatingSessions}" var="skatingSession">
				<tr>
					<td>${skatingSession.id}</td>
					<td>${skatingSession.name}</td>
					<td>${skatingSession.startDate}</td>
					<td>${skatingSession.weeksDuration}</td>
					<td>${skatingSession.dayOfWeek}</td>
					<td>${skatingSession.startTime}</td>
					<td>${skatingSession.endTime}</td>
					<td>${skatingSession.totalCost}</td>
					<td><a href="<c:url value="edit.do" />?id=${skatingSession.id}">Edit</a></td>
					<td><a href="<c:url value="deleteConfirm.do" />?id=${skatingSession.id}">Delete</a></td>
				</tr>	
			</c:forEach>
		</table>
    </template:put>
</template:insert>