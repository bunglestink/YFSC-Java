<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <c:choose>
            <c:when test="${calendarItem.id == 0}">
                <h2>Create Calendar Item</h2>
            </c:when>
            <c:otherwise>
                <h2>Edit Calendar Item</h2>
            </c:otherwise>
        </c:choose>
		<div class="edit-area">
			<form:form action="commit.do" modelAttribute="calendarItem">
				<fieldset>
					<div>
						<label>ID:</label>
						${calendarItem.id}
						<form:hidden path="id" />
						<form:hidden path="registrationTermId" />
					</div>
					<div>
						<label>Days:</label>
						<form:input path="days" />
					</div>
					<div>
						<label>Saturday Notes:</label>
						<form:textarea path="saturdayNotes" cols="50" rows="10" />
					</div>
					<div>
						<label>Sunday Notes:</label>
						<form:textarea path="sundayNotes" cols="50" rows="10" />
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