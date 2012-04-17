<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <h2>YFSC Coaches</h2>
		<p>These professional skaters teach group lessons for the club. Click any link for more information.</p>
		<ul>
			<c:forEach items="${coaches}" var="coach">
				<li><a href="<c:url value="/home/clubCoaches.do?id=${coach.id}" />">${coach.name}</a></li>
			</c:forEach>
		</ul>
		<hr />
    </template:put>
</template:insert>