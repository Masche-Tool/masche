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
		boolean showComparisonOption = false;
		boolean showComparison = false;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("masche_sessionID".equals(cookie.getName())) {
					sessionId = cookie.getValue();
				}
				if ("masche_show_comparison_option".equals(cookie.getName())) {
					if (cookie.getValue().equals("1")) {
						showComparisonOption = true;
					}
				}
				if ("masche_show_comparison".equals(cookie.getName())) {
					if (cookie.getValue().equals("1")) {
						showComparison = true;
					}
				}
			}
			Cookie cookie = new Cookie("masche_sessionID", sessionId);
			cookie.setMaxAge(7200);//Gueltigkeit des Cookie in Sekunden. 2 Stunden.
			response.addCookie(cookie);
		}
		int sessionType = Datenbank.getSessionType(sessionId);
		String FrageId = request.getParameter("frageid");
		String durchfuehrungsID = request.getParameter("durchfID");
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
				<h1>Auswertung</h1>
			</div>
			<div class="col-sm-9">
				<h3>
					<%
						out.print(evDatenbank.getQuestionOfID(FrageId));
					%>
				</h3>
				Es gab
				<%
					out.println(
								avDatenbank.getNumberOfQuestionParticipantsWithThisDurchfuehrung(FrageId, durchfuehrungsID));
				%>
				TeilnehmerInnen.
				<%
					if (showComparison) {
				%>(blaue Säule) In allen verwandten Evaluationen wurde diese Frage
				<%
					out.println(avDatenbank.getNumberOfQuestionParticipants(FrageId, durchfuehrungsID));
				%>
				mal beantwortet. (graue Säule)<%
					}
				%>
				<br> Angaben in Prozent:<br>
				<%
					if (evDatenbank.getQuestiontypeOfID(FrageId).equals("0")) {
				%>
				<p>Freitextfragen werden nicht öffentlich ausgewertet.</p>
				<p>
					<a
						href="freitextanworten.jsp?frageid=<%out.print(FrageId);%>&durchfID=<%out.print(durchfuehrungsID);%>"
						class="btn btn-default" role="button"><i
						class="glyphicon glyphicon glyphicon-list-alt"></i> Anworten
						auflisten</a>
				</p>
				<%
					}
				%>

				<%
					if (evDatenbank.getQuestiontypeOfID(FrageId).equals("1")) {
				%>
				<div style="width: 80%">
					<canvas id="canvas" height="450" width="600"></canvas>
				</div>
				<script>
					var barChartData = {
						labels : [ "ja", "nein" ],
						datasets : [
								{
									fillColor : "rgba(151,187,205,0.5)",
									strokeColor : "rgba(151,187,205,0.8)",
									highlightFill : "rgba(151,187,205,0.75)",
									highlightStroke : "rgba(151,187,205,1)",
									data : [
				<%out.print(avDatenbank.getType1Percent1WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType1Percent2WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					]
								}, 
								<%if (showComparison) {%>
								{
						            fillColor: "rgba(220,220,220,0.5)",
						            strokeColor: "rgba(220,220,220,0.8)",
						            highlightFill: "rgba(220,220,220,0.75)",
						            highlightStroke: "rgba(220,220,220,1)",
						            data: [<%out.print(avDatenbank.getType1Percent1(FrageId, durchfuehrungsID));%>, <%out.print(avDatenbank.getType1Percent2(FrageId, durchfuehrungsID));%>]
								} <%}%>
								]

					}
					window.onload = function() {
						var ctx = document.getElementById("canvas").getContext(
								"2d");
						window.myBar = new Chart(ctx).Bar(barChartData, {
							responsive : true
						});
					}
				</script>


				<%
					}
				%>

				<%
					if (evDatenbank.getQuestiontypeOfID(FrageId).equals("3")) {
				%>
				<div style="width: 80%">
					<canvas id="canvas" height="450" width="600"></canvas>
				</div>
				<script>
					var barChartData = {
						labels : [ "Stimme ich voll zu", "Stimme ich eher zu",
								"Stimme ich eher nicht zu",
								"Stimme ich eher nicht zu" ],
						datasets : [
								{
									fillColor : "rgba(151,187,205,0.5)",
									strokeColor : "rgba(151,187,205,0.8)",
									highlightFill : "rgba(151,187,205,0.75)",
									highlightStroke : "rgba(151,187,205,1)",
									data : [
				<%out.print(avDatenbank.getType3Percent1WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType3Percent2WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType3Percent3WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType3Percent4WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					]
								},<%if (showComparison) {%>
								{
						            fillColor: "rgba(220,220,220,0.5)",
						            strokeColor: "rgba(220,220,220,0.8)",
						            highlightFill: "rgba(220,220,220,0.75)",
						            highlightStroke: "rgba(220,220,220,1)",
						            data: [
						   				<%out.print(avDatenbank.getType3Percent1(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType3Percent2(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType3Percent3(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType3Percent4(FrageId, durchfuehrungsID));%>
										]
								} <%}%> ]

					}
					window.onload = function() {
						var ctx = document.getElementById("canvas").getContext(
								"2d");
						window.myBar = new Chart(ctx).Bar(barChartData, {
							responsive : true
						});
					}
				</script>



				<%
					}
				%>

				<%
					if (evDatenbank.getQuestiontypeOfID(FrageId).equals("5")) {
				%>
				<div style="width: 100%">
					<canvas id="canvas" height="450" width="600"></canvas>
				</div>
				<script>
					var barChartData = {
						labels : [ "Stimme ich voll und ganz zu",
								"Stimme ich voll zu", "Stimme ich eher zu",
								"Stimme ich eher nicht zu",
								"Stimme ich nicht zu",
								"Dem Widerspreche ich entschieden" ],
						datasets : [
								{
									fillColor : "rgba(151,187,205,0.5)",
									strokeColor : "rgba(151,187,205,0.8)",
									highlightFill : "rgba(151,187,205,0.75)",
									highlightStroke : "rgba(151,187,205,1)",
									data : [
				<%out.print(avDatenbank.getType5Percent1WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType5Percent2WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType5Percent3WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType5Percent4WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType5Percent5WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					,
				<%out.print(avDatenbank.getType5Percent6WithThisDurchfuehrung(FrageId, durchfuehrungsID));%>
					]
								}, <%if (showComparison) {%>
								{
						            fillColor: "rgba(220,220,220,0.5)",
						            strokeColor: "rgba(220,220,220,0.8)",
						            highlightFill: "rgba(220,220,220,0.75)",
						            highlightStroke: "rgba(220,220,220,1)",
						            data: [
						   				<%out.print(avDatenbank.getType5Percent1(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType5Percent2(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType5Percent3(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType5Percent4(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType5Percent5(FrageId, durchfuehrungsID));%>
										,
									<%out.print(avDatenbank.getType5Percent6(FrageId, durchfuehrungsID));%>
										]
								} <%}%>]

					}
					window.onload = function() {
						var ctx = document.getElementById("canvas").getContext(
								"2d");
						window.myBar = new Chart(ctx).Bar(barChartData, {
							responsive : true
						});
					}
				</script>
				<%
					}
				%>

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
				<p>
					<%
						if (!evDatenbank.getQuestiontypeOfID(FrageId).equals("0")) {
								if (showComparisonOption && !showComparison) {
					%>
					<a
						href="<%out.print("../SetComparisonOn?frageid=" + FrageId + "&durchfID=" + durchfuehrungsID);%>"
						class="btn btn-default" role="button" style="width: 100%;">Vergleich
						anzeigen</a>

					<%
						}
								if (showComparisonOption && showComparison) {
					%>
					<a
						href="<%out.print("../SetComparisonOff?frageid=" + FrageId + "&durchfID=" + durchfuehrungsID);%>"
						class="btn btn-default" role="button" style="width: 100%;">Vergleich
						ausblenden</a>
					<%
						}
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
