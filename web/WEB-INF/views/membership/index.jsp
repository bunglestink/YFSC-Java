<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
	<template:put name="AdditionalScripts">
		<script type="text/javascript">
		$(function () {
			$('#backup-action').click(function () {
				
				$('#backup-confirm').dialog({
					autoOpen: true,
					modal: true,
					resizable: false,
					title: 'Confirmation',
					buttons: {
						'Yes, perform backup now': function () {
							window.location = $('#backup-action').attr('href');
							$(this).dialog('close');
						},
						'Cancel': function() {
							$(this).dialog('close');
						}
					},
					close: function () {
						$(this).dialog('destroy');
					}
				});
				
				return false;
			});
		});
		</script>
	</template:put>
    <template:put name="MainContent">
		<h2>Membership</h2>
		<div class="formatted-content">
			<div>
				<a href="<c:url value="/membership/register.do" />">Signup for current Session now!</a>
			</div>
		</div>
			<div>
			<fieldset>
				<legend>Registration History</legend>
				<br />
				<c:if test="${user.registrations.isEmpty()}">
					<p>You have no past registrations.</p>
				</c:if>
				<c:if test="${!user.registrations.isEmpty()}">
					<table>
						<thead>
							<tr>
								<th>Registration Term</th>
								<th>Registration Date</th>
								<th>Number of Skaters</th>
								<th>Total Cost</th>
								<th>Amount Paid</th>
								<th>View Invoice</th>
								<th>Download</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${user.registrations}" var="registration">
								<tr>
									<td>${registration.registrationTerm.termName}</td>
									<td>${registration.invoice.invoiceDateString}</td>
									<td>${registration.skaters.size()}</td>
									<td class="currency">$${registration.invoice.totalCost}</td>
									<td class="currency">$${registration.invoice.amountPaid}</td>
									<td><a href="<c:url value="invoice.do?id=${registration.invoice.id}" />">View</a></td>
									<td><a href="<c:url value="invoice.do?id=${registration.invoice.id}&format=csv" />">csv file</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</fieldset>
		</div>
		<div>
			<sec:authorize access="hasRole('Admin')">
				<fieldset>
					<legend>Admin</legend>
					<ul>
						<li><a href="<c:url value="/announcement/index.do" />">Announcements</a></li>
						<li><a href="<c:url value="/coach/index.do" />">Coach</a></li>
						<li><a href="<c:url value="/invoice/index.do" />">Invoices</a></li>
						<li><a href="<c:url value="/registrationTerm/index.do" />">Registration Terms</a></li>
					</ul>
				</fieldset>
				<fieldset>
					<legend>Maintenance</legend>
					<ul>
						<li><a id="backup-action" href="<c:url value="/adminServices/backup.do" />">Backup System</a></li>
					</ul>
				</fieldset>
				<div id="backup-confirm" class="dialog">This action will create a backup of the entire system.  This can be used for archival purposes, and can be restored by an admin.  Are you sure you wish to backup the system?</div>
			</sec:authorize>
		</div>
	</template:put>
</template:insert>