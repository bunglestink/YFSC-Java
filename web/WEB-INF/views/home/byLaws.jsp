<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
        <h2>Bylaws</h2>
		<div class="formatted-content">
			<div>
				<label><a href="<c:url value="/Content/files/bylaws/bylaws-amended.pdf" />">Bylaws</a></label>: as ammended June 22, 2008
			</div>
			<div>
				<label><a href="<c:url value="/Content/files/bylaws/Exemption_YFSC.pdf" />">Tax Exemption Certificate</a></label>: May 2007
			</div>
			<div>
				<label>Annual Review Form</label>:
				<ul>
					<li><a href="<c:url value="/Content/files/bylaws/2010_Form_990-EZ.pdf" />">"Fiscal Year 2009-10"</a></li>
					<li><a href="<c:url value="/Content/files/bylaws/2009_Form_990-EZ.pdf" />">"Fiscal Year 2008-09"</a></li>
					<li><a href="<c:url value="/Content/files/bylaws/2008_Form_990-EZ.pdf" />">"Fiscal Year 2007-08"</a></li>
				</ul>
			</div>
		</div>
    </template:put>
</template:insert>