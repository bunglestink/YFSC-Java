<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="AdditionalScripts">
		<script type="text/javascript">
			$(function () {
				var invoiceFilter = $('#invoice-filter');
				
				invoiceFilter.keyup(function () {
					var filterText = invoiceFilter.val(),
						filterRegex = new RegExp('.*' + filterText + '.*', 'gi');
						
					$('tbody tr').each(function () {
						var $this = $(this),
							id = $this.find('.invoice-id').html(),
							family = $this.find('.invoice-family').html(),
							term = $this.find('.invoice-term').html();
							
						if (filterRegex.test(id) || filterRegex.test(family) || filterRegex.test(term)) {
							$this.show();
						}
						else {
							$this.hide();
						}
					});
				});
			});
		</script>
	</template:put>
	<template:put name="MainContent">
		<h2>Invoices</h2>
		Filter: <input id="invoice-filter" />
		<hr />
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Family Name</th>
					<th>Registration Term</th>
					<th>Amount</th>
					<th>Edit</th>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${invoices}" var="invoice">
					<tr>
						<td class="invoice-id">${invoice.id}</td>
						<td class="invoice-family">${invoice.registration.firstName} ${invoice.registration.lastName}</td>
						<td class="invoice-term">${invoice.registration.registrationTerm.termName}</td>
						<td>$${invoice.outstandingBalance}</td>
						<td><a href="<c:url value="view.do" />?id=${invoice.id}">Edit</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </template:put>
</template:insert>