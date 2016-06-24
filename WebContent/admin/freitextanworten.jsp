<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Auswertung</title>
<jsp:include page="misc/html-head.jsp" />
<script src="../js/Chart.min.js"></script>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
		String sessionId = "0";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("masche_sessionID".equals(cookie.getName())) {
					sessionId = cookie.getValue();
				}
			}
			Cookie cookie = new Cookie("masche_sessionID", sessionId);
			cookie.setMaxAge(7200);//Gueltigkeit des Cookie in Sekunden. 2 Stunden.
			response.addCookie(cookie);
		}
		int sessionType = Datenbank.getSessionType(sessionId);

		String FrageId = request.getParameter("frageid");
		String durchfuehrungsID = request.getParameter("durchfID");
		String[] freitextanworten = avDatenbank.getAllFreitextantworten(FrageId, durchfuehrungsID);
		boolean nextQuestionExists = dvDatenbank.nextQuestionExists(FrageId);
		String nextQuestionID = "0";
		if (nextQuestionExists) {
			nextQuestionID = evDatenbank.getNextQuestionId(FrageId);
		}

		if (sessionType == 1 || sessionType == 2) {
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top"> <jsp:include
		page="misc/navbarPart1.jsp" /> <%
 	if (sessionType == 1) {
 	%>
	<li><a href="showUsers.jsp"> <span style="color: white"><i
				class="glyphicon glyphicon-asterisk"></i></span> Benutzerverwaltung
	</a></li>
	<%
		}
	%> <jsp:include page="misc/navbarPart2.jsp" /> </nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>Auswertung Freitextfrage</h1>
			</div>
			<div class="col-sm-9">
				<h3>
					<%
						out.print(evDatenbank.getQuestionOfID(FrageId));
					%>
				</h3>
				<ul type="disc">
					<%
						for (int i = 0; i < freitextanworten.length; i++) {
					%>
					<li>
						<%
							out.print(freitextanworten[i]);
						%>
					</li>
					<%
						}
					%>
				</ul>
			</div>
			<div class="col-sm-3">
				<p>
					<%
						if (nextQuestionExists) {
					%>
					<a
						href="<%out.print("analyse.jsp?frageid=" + nextQuestionID + "&durchfID=" + durchfuehrungsID);%>"
						class="btn btn-primary" role="button" style="width: 100%;">Weiter</a>
					<%
						} else {
					%>
					<a href="admin.jsp" class="btn btn-danger" role="button"
						style="width: 100%;">Abgeschlossen</a>
					<%
						}
					%>
				</p>
			</div>


		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>
