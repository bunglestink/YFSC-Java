<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="template" uri="/WEB-INF/views/shared/template.tld" %>
<template:insert template="/WEB-INF/views/shared/master.jsp">
    <template:put name="MainContent">
		<div class="formatted-content">
			<h2>Program</h2>
			<p>For more detailed session descriptions, refer to the <a href="<c:url value="/home/brochure.do" />">brochure</a><br /></p>
			<table border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td height="16">
						<strong> Snowplow Sam Lessons:</strong><br />
						For children who are 3 to 5 years old. These lessons use teaching techniques that appeal to the very young. Saturday lessons are for 3 and 4 year old skaters WITH a parent. &nbsp; Sunday lessons are for 4 and 5 year old children without parents.
						<ul>
							<li><b>Saturday</b>: 9:45am&ndash;10:45am</li>
							<li><b>Sunday</b>: 4:00pm&ndash;5:00pm</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td height="16">
						<strong>Basic Skills Lessons:</strong><br />
						Half-hour group lessons for ages 5 to adult and skill levels beginner to elementary freestyle. &nbsp; Private lessons may be arranged during the practice time that follows the lesson.
						<ul>
							<li><b>Saturday</b>: 9:45am&ndash;10:45am</li>
							<li><b>Sunday</b>: 4:00pm&ndash;5:00pm</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Bridge Lessons:</strong><br />
						For advancing skaters who have completed Badge 4 or Adult 2. Continuing enrollment in basic skills is expected. Skaters who have passed basic skills must join MFD1. These lessons cover the fundamental disciplines of figure skating: moves in the field, freestyle, dance, and synchronized skating. <br />
						<ul>
							<li><b>Sunday</b>: Lessson at 5:10pm&ndash;5:40pm</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Ice Dance:</strong><br />
						A small-group lesson offered for skaters who have completed Basic 6 and adults who have completed Adult 2. Good stroking and edge control are required. <br />
						<ul>
							<li><b>Sunday</b> : Lessson at 5:40pm&ndash;6:10pm</li>
						</ul>
					</td>
				</tr>
				<tr>
					<td>
						<strong>Combined Moves, Freestyle, and Dance (MFD) Sessions</strong><br />
						These sessions are intended for skaters working on Moves in the Field, Freestyle, and Dance. <br />
						<ul>
							<li><b>Saturday</b> : MFD, Session at 8:15am&ndash;9:35am</li>
							<li><b>Sunday</b>: MFD&ndash;1, Session at 5:10pm&ndash;6:10pm</li>
							<li><b>Sunday</b>: MFD&ndash;2, Session at 6:20pm&ndash;7:20pm</li>
							<li><b>Sunday</b>: MFD&ndash;3, Session at 7:30p&ndash;8:30pm</li>
						</ul>
					</td>
				</tr>
			</table>
		</div>
    </template:put>
</template:insert>