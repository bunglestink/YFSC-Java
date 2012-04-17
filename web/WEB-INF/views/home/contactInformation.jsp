<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<h2>Contact Information</h2>
		<div class="formatted-content">
			<div>
				<label>President:</label><br />
				Michael Fischer<br />
				(203) 288-9599<br />
				<a href="mailto:Michael.Fischer@yale.edu">Michael.Fischer@yale.edu</a>
			</div>
			<div>
				<label>Membership Chair</label><br />
				Mary Lee<br />
				(203) 777-8517<br />
				<a href="mailto:Mary.Lee@yale.edu">Mary.Lee@yale.edu</a>
			</div>
			<div>
				<label>Coach Committee:</label><br />
				Bobbi Nesheim<br />
				<a href="mailto:bk.titles@comcast.net">bk.titles@comcast.net</a>
			</div>
			<div>
				<label>Test Chair:</label><br />
				Bonnie Collier<br />
				(203) 795-5088<br />
				<a href="mailto:bonnie.collier@yale.edu">bonnie.collier@yale.edu</a>
			</div>
			<div>
				<label>Club Contact Information</label><br />
				Call (203) 432-1233 to leave a message.<br />
				Your call will be returned.<br />
			</div>
		</div>
    </template:put>
</template:insert>