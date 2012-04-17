<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <h2>${coach.name}</h2>
		<hr />
		<div id="coach-primary-info">
			${coach.primaryInfo}
		</div>
		<div id="coach-secondary-info">
			${coach.secondaryInfo}
		</div>
    </template:put>
</template:insert>