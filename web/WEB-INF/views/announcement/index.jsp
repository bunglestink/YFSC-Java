<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Announcements</h2>
		<h3><a href="<c:url value="create.do" />">Create a New Announcement</a></h3>
		<table>
			<tr>
				<th>ID</th>
				<th>Date</th>
				<th>Title</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>	
			<c:forEach items="${announcements}" var="announcement">
				<tr>
					<td>${announcement.id}</td>
					<td>${announcement.announcementDateString}</td>
					<td>${announcement.title}</td>
					<td><a href="<c:url value="edit.do" />?id=${announcement.id}">Edit</a></td>
					<td><a href="<c:url value="deleteConfirm.do" />?id=${announcement.id}">Delete</a></td>
				</tr>	
			</c:forEach>
		</table>
    </template:put>
</template:insert>