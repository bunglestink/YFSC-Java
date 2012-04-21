<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="AdditionalScripts">
		<script type="text/javascript">
		$(function () {
			$('#print-invoice').click(function () {
				window.print();
				return false;
			});
		});
		</script>
	</template:put>
	<template:put name="MainContent">
		<h2>Registration Term Invoice</h2>
		<div class="invoice-options">
			<a id="print-invoice" href="#print">Print Invoice</a> | <a href="invoice.do?id=${invoice.id}&format=csv">Download as csv file</a>
		</div>
		<hr />
		<br />
		<div class="formatted-content invoice-header">
			<div>
				<label>Invoice ID: </label><span>${invoice.id}</span>
			</div>
			<div>
				<label>Invoice Date: </label><span>${invoice.invoiceDateString}</span>
			</div>
			<div>
				<label>Total Cost</label><span>$${invoice.totalCost}</span>
			</div>
			<div>
				<label>Amount Paid: </label><span>$${invoice.amountPaid}</span>
			</div>
			<div>
				<label>Outstanding Balance: </label><span class="<c:if test="${!invoice.paidInFull}">not-paid-in-full</c:if>">$${invoice.outstandingBalance}</span>
			</div>
		</div>
			<div>
			<fieldset>
				<legend>Invoice Items</legend>
				<br />
				<table>
					<thead>
						<tr>
							<th>Description</th>
							<th>Unit Cost</th>
							<th>Quantity</th>
							<th>Total Cost</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invoice.invoiceItems}" var="item">
						<tr>
							<td>${item.description}</td>
							<td class="currency">$${item.unitCost}</td>
							<td>${item.quantity}</td>
							<td class="currency">$${item.totalCost}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</fieldset>
		</div>
	</template:put>
</template:insert>