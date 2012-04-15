<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <h2>Registration Terms</h2>
	<h3><a href="<c:url value="/registrationTerm/create.do" />">Create New Registration Term</a></h3>
	<table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>StartDate</th>
                <th>EndDate</th>
                <th>Calendar Items</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>	
            <c:forEach items="${registrations}" var="term">
                <tr>
                    <td>${term.ID}</td>
                    <td>${term.TermName}</td>
                    <td>${term.StartDate}</td>
                    <td>${term.EndDate}</td>
                    <td>
                            <%-- ${term.CalendarItems.Count} - ${Html.ActionLink("View", "Index", "CalendarItem", new { id = term.ID }, null)}--%>
                    </td>
                    <td><a href="<c:url value="/registrationTerm/edit.do" />">Edit</a></td>
                    <td><a href="<c:url value="/registrationTerm/deleteConfirm.do" />">Delete</a></td>
                </tr>	
            </c:forEach>
	</table>
    </template:put>
</template:insert>