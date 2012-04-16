<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
	<h2>Delete Announcement Confirmation</h2>
	<p>Are you sure you wish to delete the following announcement?</p>
	<div class="edit-area">
		<form:form action="delete.do" modelAttribute="announcement">
		<fieldset>
			<div>
				<label>ID:</label>
				${announcement.id}
				<form:hidden path="id" />
			</div>
			<div>
				<label>Date:</label>
				${announcement.announcementDateString}
			</div>
			<div>
				<label>Title:</label>
				${announcement.title}
			</div>
			<div>
				<label>Body:</label>
				${announcement.body}
			</div>
			<div>
				<label></label>
				<a href="<c:url value="index.do" />">Cancel</a> or 
				<input type="Submit" value="Delete" />
			</div>
		</fieldset>
		</form:form>
	</div>
    </template:put>
</template:insert>