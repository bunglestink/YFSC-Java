<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <template:get name="head">
            <title>Yale Figure Skating</title>
    </template:get>		
    <link href="<c:url value="/content/Site.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/content/Site-print.css" />" rel="stylesheet" type="text/css" media="print" />
    <link href="<c:url value="/content/ui-lightness/jquery-ui-1.8.16.custom.css" />" rel="stylesheet" type="text/css" />
    <script src="<c:url value="/js/jquery-1.4.4.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/jquery-ui.min.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/jquery.tmpl.js" />" type="text/javascript"></script>
    <script src="<c:url value="/js/Util/alert.js" />" type="text/javascript"></script>
    <template:get name="AdditionalScripts"></template:get>
    
    <script type="text/javascript">
        $(function () {
            $('.date').datepicker();
            $('input:submit, button').button()

            var message = $('#page').data('message');
            if (message) {
                UTIL.alert(message);
            }
        });
    </script>
</head>
<body>
    <div id="page" class="page" data-message="${message}">

        <div id="header">
            <div id="title">
                <img src="<c:url value="/content/images/YFSC_emblem_header.png"/>" alt="YFSC" />
            </div>

            <div id="logindisplay">
				<sec:authorize access="isAuthenticated()">
					<text>Welcome <b><sec:authentication property="principal.username" /></b>!
					[ <a href="<c:url value="/membership/index.do" />">Member Area</a> | <a href="<c:url value="/account/logOff.do" />">Log Off</a> ]</text>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
				    [ <a href="<c:url value="/account/logOn.do" />">Log On</a> ] | [ <a href="<c:url value="/account/create.do" />">Create Account</a> ]
				</sec:authorize>
            </div>

            <div id="menucontainer">

                <ul id="menu">
                    <li><a href="<c:url value="/home/index.do" />">Home</a></li>
                    <li><a href="<c:url value="/home/program.do" />">Program</a></li>
                    <li><a href="<c:url value="/home/calendar.do" />">Calendar</a></li>
					<li><a href="<c:url value="/home/clubCoaches.do" />">Coaches</a></li>
                    <li><a href="<c:url value="/home/membership.do" />">Membership</a></li>
                    <li><a href="<c:url value="/home/brochure.do" />">Brochure</a></li>
                    <li><a href="<c:url value="/home/byLaws.do" />">ByLaws</a></li>
                    <li><a href="<c:url value="/home/contactInformation.do" />">Contact</a></li>
                </ul>

            </div>
        </div>

        <template:get name="RightBar">
                <div id="right-bar">
                        <h3>Yale Figure Skating Club</h3>
                        <img src="<c:url value="/content/images/whale.jpg" />" alt="Whale" />
                        <em>Yale Figure Skating Club, Inc. is a tax-exempt nonprofit organization.</em>
                </div>
        </template:get>

        <div id="main">
            <template:get name="MainContent"></template:get>
        </div>
        <div id="footer">
        </div>
    </div>
</body>
</html>


