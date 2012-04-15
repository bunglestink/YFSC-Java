<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="content">
        <h1>${name}</h1>
        <h2>${name}</h2>
        <h2>${name}</h2>
    </template:put>
</template:insert>