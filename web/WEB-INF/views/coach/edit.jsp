<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <c:choose>
            <c:when test="${model.id == 0}">
                <h2>Create Coach</h2>
            </c:when>
            <c:otherwise>
                <h2>Edit Coach</h2>
            </c:otherwise>
        </c:choose>
		<div class="edit-area">
			<form:form action="commit.do" modelAttribute="coach">
				<fieldset>
					<div>
						<label>ID:</label>
						${coach.id}
						<form:hidden path="id" />
					</div>
					<div>
						<label>Name:</label>
						<form:input path="name" />
					</div>
					<div>
						<label>Primary Info:</label>
						<form:textarea path="primaryInfo" cols="50" rows="15" />
					</div>
					<div>
						<label>Secondary Info:</label>
						<form:textarea path="secondaryInfo" cols="50" rows="15" />
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