<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
	<h2>Delete Registration Term Confirmation</h2>
	<p>Are you sure you wish to delete the following registration term?</p>
	<div class="edit-area">
            <form:form action="delete.do" modelAttribute="term">
		<fieldset>
			<div>
                            <label>ID:</label>
                            ${term.id}
                            <form:hidden path="id" />
			</div>
			<div>
                            <label>Name:</label>
                            ${term.termName}
			</div>
			<div>
                            <label>Start Date:</label>
                            ${term.startDateString}
			</div>
			<div>
                            <label>End Date:</label>
                            ${term.endDateString}
			</div>
			<div>
                            <label></label>
                            <a href="<c:url value="/registrationTerm/index.do" />">Cancel</a> or 
                            <input type="Submit" value="Delete" />
			</div>
		</fieldset>
            </form:form>
	</div>
    </template:put>
</template:insert>