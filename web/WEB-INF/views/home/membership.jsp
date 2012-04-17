<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<div class="formatted-content">
			<div>
				<h2>Join Online Now!</h2>
				<p>The easiest way to join YFSC is by online electronic registraion.  If you already have an account, sign in and register!  If not, please take a minute and create your account today:</p>
				<div class="join-now">
					<%-- TODO: if (Request.IsAuthenticated) { --%>
						<p><a href="<c:url value="/membership.index.do" />">Join Online in Member Area</a></p> 
					<%-- } else { --%>
						<p><a href="<c:url value="/account/logOn.do" />">Login</a></p> 
						<p>or</p>
						<p><a href="<c:url value="/account/create.do" />">Create an Account</a></p>
					<%-- } --%>
				</div>
			</div>
			<hr />
			<div>
				<h2>Join By Mail</h2>
				<div>
					Click the links for the forms you need.<br />
					Print the forms, fill them out, and mail them with a check to:<br />
					<div class="padded-address">
						Mary D'Agostino,<br />
						19 Douglas Drive,<br />
						Hamden, CT 06518<br />
					</div>
				</div>
				<ol>
					<li>
						<a href="<c:url value="/Content/files/attach/1112_YFSC_Spring_Renewal.pdf" />">Spring Registration Form for Current Members</a><br />
						Use this form to subscribe to Spring term skating sessions.<br />
					</li>
					<li>
						<a href="<c:url value="/Content/files/attach/1112_YFSC_Mbrship_FormSpring.pdf" />">Combined Membership and Registration Form for New Members</a><br />
						Use this form if you were NOT a YFSC member in the fall, to join the YFSC and/or USFSA and to register for spring term sessions.
					</li>
					<li>
						<a href="<c:url value="/Content/files/attach/Emergency_Form.pdf" />">Emergency Information</a><br />
						Required for new skating members.  This information will be kept in the rink.<br />
					</li>
					<li>
						Test Application Forms<br />
						No test session has been scheduled yet.<br />
					</li>
				</ol>
			</div>
		</div>
    </template:put>
</template:insert>