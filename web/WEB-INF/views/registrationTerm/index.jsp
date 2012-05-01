<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="AdditionalScripts">		
		<script type="text/javascript">
		$(function () {
			$('.set-current').click(function () {
				var $this = $(this),
					setCurrentUrl = $this.data('set-current-url');

				$('#set-current-confirm').dialog({
					autoOpen: true,
					modal: true,
					title: 'Confirmation',
					resizable: false,
					buttons: {
						'Yes, change current': function () {
							$.ajax({
								type: 'post',
								url: setCurrentUrl,
								success: function (data) {
									if (data !== true) {
										UTIL.alert('An error occurred.  Please try again later.');
										return;
									}

									$('tr').removeClass('current-term-row');
									$this.closest('tr').addClass('current-term-row');
									UTIL.alert('You have successfully changed to current registration!');
								},
								error: function () {
									UTIL.alert('An error occurred.  Please try again later.');
								}
							});
							$(this).dialog('close');
						},

						'Cancel': function () {
							$(this).dialog('close');
						}
					},
					close: function () {
						$(this).dialog('destroy');
					}
				});
			});
		});
		</script>
	</template:put>
	<template:put name="MainContent">
        <h2>Registration Terms</h2>
	<h3><a href="<c:url value="/registrationTerm/create.do" />">Create New Registration Term</a></h3>
	<table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>StartDate</th>
                <th>EndDate</th>
                <th>Calendar Items</th>
				<th>Skating Sessions</th>
                <th>Edit</th>
                <th>Delete</th>
				<th>Current</th>
            </tr>	
            <c:forEach items="${registrations}" var="term">
                <tr <c:if test="${term.current}">class="current-term-row"</c:if>>
                    <td>${term.id}</td>
                    <td>${term.termName}</td>
                    <td>${term.startDate}</td>
                    <td>${term.endDate}</td>
                    <td>${term.calendarItems.size()} - <a href="<c:url value="/calendarItem/index.do" />?id=${term.id}">View</a></td>
					<td>${term.sessions.size()} - <a href="<c:url value="/skatingSession/index.do" />?id=${term.id}">View</a></td>
                    <td><a href="<c:url value="/registrationTerm/edit.do" />?id=${term.id}">Edit</a></td>
                    <td><a href="<c:url value="/registrationTerm/deleteConfirm.do" />?id=${term.id}">Delete</a></td>
					<td>
						<span class="current-term"><strong>Current Term</strong></span>
						<a href="#" class="set-current" 
							data-set-current-url="<c:url value="/registrationTerm/setCurrent.do" />?id=${term.id}">Set as Current</a>
				</td>
                </tr>	
            </c:forEach>
	</table>
	<div id="set-current-confirm" class="dialog">Are you sure you wish to change the current registration term?  This has system wide changes.</div>
    </template:put>
</template:insert>