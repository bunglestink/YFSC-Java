<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <c:choose>
            <c:when test="${skatingSession.id == 0}">
                <h2>Create Skating Session</h2>
            </c:when>
            <c:otherwise>
                <h2>Edit Skating Session</h2>
            </c:otherwise>
        </c:choose>
		<div class="edit-area">
			<form:form action="commit.do" modelAttribute="skatingSession">
				<fieldset>
					<div>
						<label>ID:</label>
						${skatingSession.id}
						<form:hidden path="id" />
						<form:hidden path="registrationTermId" />
					</div>
					<div>
						<label>Name:</label>
						<form:input path="name" />
					</div>
					<div>
						<label>Start Date:</label>
						<form:input path="startDateString" cssClass="date" />
					</div>
					<div>
						<label>Weeks Duration;</label>
						<form:input path="weeksDuration" cssClass="integer" />
					</div>
					<div>
						<label>Day Of Week</label>
						<form:input path="dayOfWeek" />
					</div>
					<div>
						<label>Start Time:</label>
						<form:input path="startTime" />
					</div>
					<div>
						<label>End Time:</label>
						<form:input path="endTime" />
					</div>
					<div>
						<label>Cost:</label>
						<form:input path="totalCost" cssClass="currency" />
					</div>
					
					<div>
						<label>Description:</label>
						<form:textarea path="description" cols="50" rows="5" />
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