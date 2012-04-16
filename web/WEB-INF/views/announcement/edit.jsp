<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <c:choose>
            <c:when test="${announcement.id == 0}">
                <h2>Create Announcement</h2>
            </c:when>
            <c:otherwise>
                <h2>Edit Announcement</h2>
            </c:otherwise>
        </c:choose>
		<div class="edit-area">
			<form:form action="commit.do" modelAttribute="announcement">
				<fieldset>
					<div>
						<label>ID:</label>
						${announcement.id}
						<form:hidden path="id" />
					</div>
					<div>
						<label>Date:</label>
						<form:input path="announcementDateString" cssClass="date" />
					</div>
					<div>
						<label>Title:</label>
						<form:input path="title" />
					</div>
					<div>
						<label>Body:</label>
						<form:textarea path="body" rows="20" cols="50" />
					</div>
					<div>
						<label></label>
						<input type="Submit" />
					</div>
				</fieldset>
			</form:form>
		</div>
    </template:put>
</template:insert>