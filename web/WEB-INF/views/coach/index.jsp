<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Coaches</h2>
		<h3><a href="<c:url value="create.do" />">Add a Coach</a></h3>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>	
			<c:forEach items="${coaches}" var="coach">
				<tr>
					<td>${coach.id}</td>
					<td>${coach.name}</td>
					<td><a href="<c:url value="edit.do" />?id=${coach.id}">Edit</a></td>
					<td><a href="<c:url value="deleteConfirm.do" />?id=${coach.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
    </template:put>
</template:insert>