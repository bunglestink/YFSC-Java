<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <c:choose>
            <c:when test="${model.id == 0}">
                <h2>Create Registration Term</h2>
            </c:when>
            <c:otherwise>
                <h2>Edit Registration Term</h2>
            </c:otherwise>
        </c:choose>
	<div class="edit-area">
            <form:form action="commit.do" modelAttribute="model">
            <fieldset>
                <div>
                    <label>ID:</label>
                    <form:label path="id" />
                    <form:hidden path="id" />
                </div>
                <div>
                    <label>Name:</label>
                    <form:input path="termName" />
                </div>
                <div>
                    <label>Start Date:</label>
                    <form:input path="startDateString" cssClass="date" />
                </div>
                <div>
                    <label>End Date:</label>
                    <form:input path="endDateString" cssClass="date" />
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