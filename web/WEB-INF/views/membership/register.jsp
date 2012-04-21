<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
	<template:put name="RightBar">&nbsp;</template:put>
	
    <template:put name="MainContent">
		<script src="<c:url value="/js/knockout-2.0.0.js" />" type="text/javascript"></script> 
		<script src="<c:url value="/js/knockout.mapping.js" />" type="text/javascript"></script> 
		<script type="text/javascript" src="<c:url value="/js/Util/fix-json-date.js" />"></script>
		<script type="text/javascript" src="<c:url value="/js/Membership/Register.js" />"></script>
		<script type="text/javascript">


			ko.bindingHandlers.button = {
				init: function (element) {
					$(element).button();
				}
			};

			$(function () {

				// get settings, create and bind view model
				var settings = $('#registration-area').data(),
					viewModel;


				MODEL.ViewModelFactory.Create(
					settings['current-sessions-url'],
					settings['registration-base-url'],
					settings['get-registration-cost-url'],
					function (vm) {
						viewModel = vm;

						// apply bindings, then show first form
						ko.applyBindings(viewModel);
						$('#registration-loading').hide();
						$('#registration-family').slideDown();

						// set datepickers
						$('.datefield').datepicker();

						// register handlers for each nav link
						$('#registration-navigation ul a, .navlink').each(function () {
							var $this = $(this);
							var section = $this.attr('href').substring(1);
							var properCaseSection = section.substring(0, 1).toUpperCase() + section.substring(1);

							// onclick, hide all sections, then slide down selected
							$this.click(function () {
								// hide all 
								$('#registration-content > fieldset > div').hide();
								$('#registration-' + section).slideDown();
								$('#registration-content > fieldset > legend').html(properCaseSection);
							});
						});


						// hide dialog for later use...
						$('#session-selector-dialog').dialog({
							modal: true,
							autoOpen: false,
							minWidth: 500,
							buttons: {
								'Add Session': function () {
									var session = new MODEL.SkaterSession(viewModel.selectedSession(), viewModel.selectedSkater);

									// delete any previous records of the session before inserting
									var deletedSession = viewModel.selectedSkater.sessions.remove(function (item) { return item.id == session.id; });
									viewModel.selectedSkater.sessions.push(session);

									$(this).dialog('close');

									// display error message if returns array (has length duck typing)
									if (deletedSession.length) {
										$('#error-dialog')
											.html(viewModel.selectedSkater.firstName() + ' ' + viewModel.selectedSkater.lastName() + ' already in ' + session.name)
											.dialog('open');
									}

									return true;
								},
								Cancel: function () {
									$(this).dialog('close');
									return false;
								}
							}
						});

						// setup error dialog
						$('#error-dialog').dialog({
							modal: true,
							autoOpen: false,
							buttons: {
								Ok: function () {
									$(this).dialog('close');
									return true;
								}
							}
						});


						// setup submit confirmation dialog
						$('#confirm-submit-dialog').dialog({
							modal: true,
							autoOpen: false,
							minWidth: 500,
							buttons: {
								'Ok': function () {
									$(this).dialog('close');

									var result = viewModel.registration.toJsObject();
									$.ajax({
										url: settings['create-registration-url'], 
										data: JSON.stringify(result), 
										type: 'POST',
										contentType: 'application/json',
										success: function (result) {
											if (result === true) {
												UTIL.alert('success!');
												return;
											}

											// if not true, model errors: 
											var message = '<p>The following errors were encountered: </p></ul>';

											for (var i = 0; i < result.model.errors.length; i++) {
												message += '<li>' + result.model.errors[i] + '</li>';
											}
											UTIL.alert(message);
										},
										error: function (jqXhr, statusText) {
											UTIL.alert('An unknown error has occurred: <br /><br />' + statusText);
										}
									});

									return true;
								},

								'Cancel': function () {
									$(this).dialog('close');
									return false;
								}
							}
						});


						$('#btnSubmitRegistration').click(function () {
							$('#confirm-submit-dialog').dialog('open');
						});


						// set confirm message on unload
						window.onbeforeunload = function (e) {
							e = e || window.event;
							var message = 'Registration has not been submitted.';
							// For IE and Firefox prior to version 4
							if (e) {
								e.returnValue = message;
							}
							// For Safari
							return message;
						};
					});
			});
		</script>		
	</asp:Content>



	<asp:Content ContentPlaceHolderID="MainContent" runat="server">

		<h2>Registration</h2>
		<div id="registration-area"
			 data-current-sessions-url="<c:url value="/skatingSessionsService/current.do" />"
			data-registration-base-url="<c:url value="/annualRegistrationService/new.do" />"
			data-create-registration-url="<c:url value="/annualRegistrationService/create.do" />"
			data-get-registration-cost-url="<c:url value="/annualRegistrationService/getCost.do" />">
			<div id="registration-navigation">
				<ul>
					<li><a href="#family">Family</a> </li>
					<li>&gt; <a href="#skaters">Skaters</a> </li>
					<li>&gt; <a href="#sessions">Sessions</a> </li>
					<li>&gt; <a href="#confirmation" data-bind="click: refreshTotalCost">Confirmation</a> </li>
				</ul>
			</div>

			<div id="registration-content">
				<fieldset>
					<legend>Title</legend>

					<div id="registration-loading">
						<h3>Loading...</h3>
					</div>

					<div id="registration-family">
						<p>Adult or parent of child skater:</p>
						<div id="registration-family-left">
							<div>
								<label>First Name:</label>
								<input data-bind="value: registration.firstName" />
							</div>

							<div>
								<label>Middle Name:</label>
								<input data-bind="value: registration.middleName" />
							</div>

							<div>
								<label>Last Name:</label>
								<input data-bind="value: registration.lastName" />
							</div>

							<div>
								<label>Street:</label>
								<input data-bind="value: registration.street" />
							</div>

							<div>
								<label>City:</label>
								<input data-bind="value: registration.city" />
							</div>

							<div>
								<label>State:</label>
								<input data-bind="value: registration.state" />
							</div>

							<div>
								<label>Zip:</label>
								<input data-bind="value: registration.zip" />
							</div>

							<div>
								<label>Home Phone:</label>
								<input data-bind="value: registration.homePhone" />
							</div>

							<div>
								<label>Work Phone:</label>
								<input data-bind="value: registration.workPhone" />
							</div>

							<div>
								<label>Email:</label>
								<input data-bind="value: registration.email" />
							</div>
						</div>
						<div id="registration-family-right">
							<div>
								<label>Yale Affiliation:</label>
								<input type="checkbox" data-bind="checked: registration.yaleAffiliation" />
							</div>
							<div data-bind="visible: registration.yaleAffiliation">
								<div>
									<label>Affiliated Person:</label>
									<input  data-bind="value: registration.nameOfAffiliatedPerson" />
								</div>
								<div>
									<label>Yale Affiliation Type:</label>
									<input  data-bind="value: registration.yaleAffiliationType" />
								</div>
								<div>
									<label>Department:</label>
									<input  data-bind="value: registration.department" />
								</div>
								<div>
									<label>School:</label>
									<input  data-bind="value: registration.school" />
								</div>
								<div>
									<label>Year:</label>
									<input  data-bind="value: registration.year" />
								</div>
							</div>
						</div>
						<div>
							<br />
							<a class="navlink" href="#skaters">Next ></a>
						</div>
					</div>



					<div id="registration-skaters">
						<button data-bind='click: addSkater'>Add Skater</button>
						<button data-bind='click: copySkater'>Copy Skater From Family</button>
						<div id="registration-skaters-labels">
							<label>First Name</label>
							<label>Middle Name</label>
							<label>Last Name</label>
							<label class="registration-skaters-sex">Sex</label>
							<label class="registration-skaters-us-citizen">US Citizen?</label>
							<label>DOB</label>
							<label>New Registrant?</label>
							<label>Level</label>
						</div>
						<ul data-bind="template: {name: 'skaterTemplate', foreach: registration.skaters}">
						</ul>

						<script id="skaterTemplate" type="text/html">
							<li>
								<input data-bind="value: firstName" />
								<input data-bind="value: middleName" />
								<input data-bind="value: lastName" />
								<input data-bind="value: sex" class="registration-skaters-sex" />
								<input data-bind="checked: usCitizen" type="checkbox" class="registration-skaters-us-citizen" />
								<input data-bind="value: birthDate" class="datefield" />
								<input data-bind="checked: newRegistrant" type="checkbox" />
								<input data-bind="value: level" />
								<button data-bind="click: remove, button: true">Remove</button>
							</li>
						</script>

						<div>
							<br />
							<a class="navlink" href="#family">&lt; Back</a> | 
							<a class="navlink" href="#sessions">Next &gt;</a>
						</div>
					</div>

					<div id="registration-sessions">
						<h3 data-bind="visible: registration.skaters().length == 0">No skaters entered...</h3>
						<ul data-bind="template: {name: 'skaterSessionTemplate', foreach: registration.skaters}"></ul>
						<script id="skaterSessionTemplate" type="text/html">
							<li>
								<h3 data-bind="text: fullName"></h3>
								<button data-bind="click: $parent.addSession, button: true">Add Session</button>
								<p data-bind="visible: sessions().length == 0">No sessions...</p>
								<ul data-bind="template: {name: 'sessionTemplate', foreach: sessions}"></ul>
							</li>
						</script>
						<script id="sessionTemplate" type="text/html">
							<li>
								<span data-bind="text: name" class="registration-session-name"></span>
								 &#36<span data-bind="text: totalCost" class="registration-session-cost"></span>
								<button data-bind="click: remove, button: true">Remove</button>
							</li>
						</script>

						<div>
							<br />
							<a class="navlink" href="#skaters">&lt; Back</a> | 
							<a class="navlink" href="#confirmation">Next &gt;</a>
						</div>
					</div>

					<div id="registration-confirmation">
						<div id="submit-area">
							<label>Total Cost:</label>
							<label data-bind="text: totalCost"></label>
							<button id="btnSubmitRegistration">Submit Registration</button>
						</div>

						<h3>The <span data-bind="text: registration.lastName"></span> Family Registration</h3>
						<p>Please confirm that the below information is correct before proceeding:</p>

						<h4>Family Information</h4>
						<div id="confirmation-family-left">
							<div>
								<label>First Name:</label>
								<label data-bind="text: registration.firstName" />
							</div>

							<div>
								<label>Middle Name:</label>
								<label data-bind="text: registration.middleName" />
							</div>

							<div>
								<label>Last Name:</label>
								<label data-bind="text: registration.lastName" />
							</div>

							<div>
								<label>Street:</label>
								<label data-bind="text: registration.street" />
							</div>

							<div>
								<label>City:</label>
								<label data-bind="text: registration.city" />
							</div>

							<div>
								<label>State:</label>
								<label data-bind="text: registration.state" />
							</div>

							<div>
								<label>Zip:</label>
								<label data-bind="text: registration.zip" />
							</div>

							<div>
								<label>Home Phone:</label>
								<label data-bind="text: registration.homePhone" />
							</div>

							<div>
								<label>Work Phone:</label>
								<label data-bind="text: registration.workPhone" />
							</div>

							<div>
								<label>Email:</label>
								<label data-bind="text: registration.email" />
							</div>
						</div>
						<div id="confirmation-family-right">
							<div data-bind="visible: registration.yaleAffiliation">
								<br />
								<div>
									<label>Yale Affiliation:</label>
								</div>
								<div>
									<label>Affiliated Person:</label>
									<label  data-bind="text: registration.nameOfAffiliatedPerson" />
								</div>
								<div>
									<label>Yale Affiliation Type:</label>
									<label  data-bind="text: registration.yaleAffiliationType" />
								</div>
								<div>
									<label>Department:</label>
									<label  data-bind="text: registration.department" />
								</div>
								<div>
									<label>School:</label>
									<label  data-bind="text: registration.school" />
								</div>
								<div>
									<label>Year:</label>
									<label  data-bind="text: registration.year" />
								</div>
							</div>
						</div>

						<h4>Skaters</h4>
						<ul class="nolist" data-bind="template: {name: 'skaterConfirmTemplate', foreach: registration.skaters}">
						</ul>
						<script id="skaterConfirmTemplate" type="text/html">
							<li>
								<div>
									<label>Name:</label>
									<span data-bind="text: firstName" /> <span data-bind="text: middleName" /> <span data-bind="text: lastName" /> 
								</div>
								<div>
									<label>Sex:</label>
									<label data-bind="text: sex" />
								</div>
								<div>
									<label>USCitizen</label>
									<label data-bind="checked: usCitizen" type="checkbox" />
								</div>
								<div>
									<label>DOB:</label>
									<label data-bind="text: birthDate" class="datefield" />
								</div>
								<div>
									<label>New:</label>
									<label data-bind="checked: newRegistrant" type="checkbox" />
								</div>
								<div>
									<label>Level:</label>
									<label data-bind="text: level" />
								</div>
								<div>
									<label>Sessions:</label>
									<ul data-bind="template: {name: 'sessionConfirmTemplate', foreach: sessions}"></ul>
								</div>
							</li>
						</script>
						<script id="sessionConfirmTemplate" type="text/html">
							<li>
								<span data-bind="text: name" class="registration-session-name"></span>
							</li>
						</script>


						<div>
							<br />
							<a class="navlink" href="#sessions">&lt; Back</a>
						</div>
					</div>
				</fieldset>

			</div>
		</div>


		<div id="session-selector-dialog" title="Select a Session">
			<div>
				<div>
					<label>Session: </label><select data-bind="options: currentSessions, optionsText: 'name', value: selectedSession"></select>
				</div>
				<div>
					<label>Day of Week: </label><span data-bind="text: selectedSession().dayOfWeek"></span>
				</div>
				<div>
					<label>Start Time: </label><span data-bind="text: selectedSession().startTime"></span>
				</div>
				<div>
					<label>End Time: </label><span data-bind="text: selectedSession().endTime"></span>
				</div>
				<div>
					<label>Start Date: </label><span data-bind="text: selectedSession().startDate"></span>
				</div>
				<div>
					<label>Weeks Duration: </label><span data-bind="text: selectedSession().weeksDuration"></span>
				</div>
				<div>
					<label>Total Cost: </label>$<span data-bind="text: selectedSession().totalCost"></span>
				</div>
				<p data-bind="text: selectedSession().description"></p>
			</div>
		</div>

		<div id="error-dialog" title="Message">
		</div>

		<div id="confirm-submit-dialog" title="Confirmation">
			<p>Are you sure you wish to submit this registration?  Once submitted, changes can only be made in person.</p>
		</div>
	</template:put>
</template:insert>