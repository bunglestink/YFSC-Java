<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="AdditionalScripts">
		<script type="text/javascript">
		$(function () {
			var printInvoice = $('#print-invoice'),
				deletePayment = $('.delete-payment'),
				addPayment = $('#add-payment')
				outstandingBalance = new Number($('.outstanding-balance').data('balance')),
				amountPaid = new Number($('.amount-paid').data('amount'));
			
			function addPaymentTotal(payment) {
				outstandingBalance -= payment;
				amountPaid += payment;
				
				$('.outstanding-balance').html('$' + outstandingBalance.toFixed(2));
				$('.amount-paid').html('$' + amountPaid.toFixed(2));
				
				if (outstandingBalance > 0) {
					$('.outstanding-balance').addClass('not-paid-in-full');
				}
				else { 
					$('.outstanding-balance').removeClass('not-paid-in-full');
				}
			}
			
			function deleteHandler() {
				var $this = $(this),
					paymentAmount = $this.data('amount');
				
				$('#delete-confirm').dialog({
					modal: true,
					autoOpen: true,
					title: 'Confirmation',
					resizable: false,
					buttons: {
						Delete: function () {
							$(this).dialog('close');
							$.ajax({
								url: 'deletePayment.do?id=' + $this.data('payment-id'),
								type: 'POST',
								success: function(data) {
									if (data === true) {
										$this.closest('tr').remove();
										addPaymentTotal(-paymentAmount);
										UTIL.alert('Payment successfully deleted.');
										return;
									}
									UTIL.alert('Unable to delete payment.  Please try again later.');
								},
								error: function () {
									UTIL.alert('Unable to delete payment.  Please try again later.');
								}
							});
						},
						Cancel: function () {
							$(this).dialog('close');
						}
					}
				});
				
				return false;
			}
			
			
			
			
			function addHandler() {
				var $this = $(this),
					addDialog = $('#add-dialog');
				
				addDialog.find('input').val(null);
				addDialog.dialog({
					modal: true,
					autoOpen: true,
					title: 'Add Payment',
					width: 400,
					resizable: false,
					buttons: {
						Submit: function () {
							var payment = {
								amount: parseInt(addDialog.find('#amount').val(), 10),
								type: addDialog.find('#type').val(),
								description: addDialog.find('#type').val(),
								invoice: {id: $this.data('invoice-id')}
							};
							
							if (!payment.amount) {
								UTIL.alert('Amount must be a numeric value!');
								return;
							}
							
							$.ajax({
								url: 'addPayment.do',
								type: 'POST',
								data: JSON.stringify(payment),
								contentType: 'application/json',
								success: function(data) {
									addDialog.dialog('close');
									
									var paymentId = parseInt(data, 10);
									if (paymentId) {
										var paymentTable = $('#payment-table'),
											date = new Date(),
											newRow = $('<tr>')
														.append($('<td>').html((date.getMonth()+1).toString() + '/' + date.getDate().toString() + '/' + date.getFullYear().toString()))
														.append($('<td>').html(payment.type))
														.append($('<td>').html(payment.description))
														.append($('<td>').html('$' + payment.amount).addClass('currency'))
														.append($('<td>')
															.append($('<a>')
																.attr('href', '#')
																.data('payment-id', paymentId)
																.data('amount', payment.amount.toFixed(2))
																.html('Delete').click(deleteHandler)))
														.appendTo(paymentTable.find('tbody'));
										
										addPaymentTotal(payment.amount);
										UTIL.alert('Payment successfully added.');
										return;
									}
									UTIL.alert('Unable to add payment.  Please try again later.');
								},
								error: function () {
									addDialog.dialog('close');
									UTIL.alert('Unable to add payment.  Please try again later.');
								}
							});
						},
						Cancel: function () {
							$(this).dialog('close');
						}
					}
				});
				
				return false;
			}
			
			
			deletePayment.click(deleteHandler);
			addPayment.click(addHandler);
			printInvoice.click(function () {
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
		<div>
			<fieldset>
				<legend>Payments</legend>
				<br />
				<a id="add-payment" class="print-hide" data-invoice-id="${invoice.id}" href="#">Add a Payment</a>
				<br />
				<br />
				<table id="payment-table">
					<thead>
						<tr>
							<th>Date Received</th>
							<th>Type</th>
							<th>Notes</th>
							<th>Amount</th>
							<th class="print-hide">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invoice.invoicePayments}" var="payment">
						<tr>
							<td>${payment.dateReceivedString}</td>
							<td>${payment.type}</td>
							<td>${payment.description}</td>
							<td class="currency">$${payment.amount}</td>
							<td class="print-hide"><a href="#" class="delete-payment" data-payment-id="${payment.id}" data-amount="${payment.amount}">Delete</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</fieldset>
		</div>
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
				<label>Amount Paid: </label>
				<span data-amount="${invoice.amountPaid}" class="amount-paid">$${invoice.amountPaid}</span>
			</div>
			<div>
				<label>Outstanding Balance: </label>
				<span data-balance="${invoice.outstandingBalance}" 
					  class="outstanding-balance <c:if test="${!invoice.paidInFull}">not-paid-in-full</c:if>">$${invoice.outstandingBalance}</span>
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
		<div id="delete-confirm" class="dialog">Are you sure you wish to delete this payment?  This action cannot be reversed.</div>
		<div id="add-dialog" class="dialog">
			<div>
				<label>Amount: </label><input type="text" id="amount" />
			</div>			
			<div>
				<label>Type: </label><input type="text" id="type" />
			</div>			
			<div>
				<label>Notes: </label><input type="text" id="description" />
			</div>			
		</div>
	</template:put>
</template:insert>