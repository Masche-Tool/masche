<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Dozentenansicht</title>
<jsp:include page="misc/html-head.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$('.fancybox').fancybox({
			'width' : 405,
			'height' : 405,
			'autoSize' : false,
			helpers : {
				overlay : {
					css : {
						'background-color' : '#ffffff'
					}
				}
			}
		});
	});
</script>
<style type="text/css">
.fancybox-title {
	font-family: Lucida Sans Typewriter;
	font-size: 60px;
}

.fancybox-title-float-wrap .child {
	line-height: 60px;
	font-weight: normal;
}
.fancybox-title-float-wrap {
	margin-bottom: -68px
}
</style>
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
				<h1>Meine Evaluationen</h1>
			</div>
			<div class="col-sm-9">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Evaluationsname</th>
							<th>Zeitraum</th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
							String user = Datenbank.getUserOfSession(sessionId);
								String evaluationen[] = evDatenbank.getFrageboegenOfUser(user);
								for (int i = 0; i < evaluationen.length; i++) {
									out.print("<tr><td>");
									if (evDatenbank.getNumbersOfQuestions(evaluationen[i]) != 0
											&& dvDatenbank.freigeschaltet(evDatenbank.getDurchfuehrungID(evaluationen[i]))) {
										out.print("<a href=\"#code" + i + "\" class=\"fancybox\" title=\""
												+ evDatenbank.getDurchfuehrungID(evaluationen[i])
												+ "\"\"><i class=\"glyphicon glyphicon-qrcode\"></i> Durchführen</a>");
									}
									out.print("</td><td>" + evDatenbank.getEvalModName(evaluationen[i]) + "</td><td>"
											+ evDatenbank.getSemesterOfModul(evaluationen[i]) + "</td><td>");
									if (avDatenbank.getNumberOfParticipantForFirstQuestion(evaluationen[i]) > 5) {
										out.print("<a href=\"../AnalyseInit?id=" + evaluationen[i]
												+ "\" class=\"btn btn-primary\" role=\"button\" id=\"analyse" + i
												+ "\"><i class=\"glyphicon glyphicon-stats\"></i></a>");
									}
									out.print("</td><td>");
									if (dvDatenbank.freigeschaltet(evDatenbank.getDurchfuehrungID(evaluationen[i]))) {
										out.print("<a href=\"editEvaluation.jsp?id=" + evaluationen[i]
												+ "\" class=\"btn btn-primary\" role=\"button\"><i class=\"glyphicon glyphicon-pencil\"></i></a>");
									}else{
										out.print("<a href=\"../Duplicate?id=" + evaluationen[i]
												+ "\" class=\"btn btn-primary\" role=\"button\"><i class=\"glyphicon glyphicon-duplicate\"></i></a>");
									}
									out.print("</td><td><a href=\"ShareEvaluation.jsp?id=" + evaluationen[i]
											+ "\" class=\"btn btn-danger\" role=\"button\" id=\"sharee" + i
											+ "\"><i class=\"glyphicon glyphicon-share\"></a></td>"
											+ "<td><a href=\"../DeleteEvaluation?deleteevaluation=" + evaluationen[i]
											+ "\" class=\"btn btn-danger\" role=\"button\" id=\"delete" + i
											+ "\"><i class=\"glyphicon glyphicon-trash\"></i></a></td></tr>");
								}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-sm-3">
				<p>
					<a href="newEvaluation.jsp" class="btn btn-primary" role="button"
						style="width: 100%;">Neue Evaluation</a>
				</p>
				<p>
					<a href="import.jsp" class="btn btn-primary" role="button"
						style="width: 100%;">Evaluation importieren</a>
				</p>
			</div>
		</div>
		<jsp:include page="misc/footer.jsp" />
		<script type="text/javascript">
		<%for (int i = 0; i < evaluationen.length; i++) {%>
		$("#delete<%out.print(i);%>").popConfirm({
				title : "Evaluationen wirklich löschen?",
				content : "Diese Aktion ist unwiederbringlich!",
				placement : "bottom",
				yesBtn : "LÖSCHEN",
				noBtn : "nicht löschen"
			});
		<%}%>
		</script>
		<script type="text/javascript">
		<%for (int i = 0; i < evaluationen.length; i++) {%>
		$("#analyse<%out.print(i);%>").popConfirm({
				title : "Auswertung wirklich anzeigen?",
				content : "Wird die Auswertung angezeigt, so können keine Teilnehmer mehr neu an der Evaluation teilnehmen!",
				placement : "bottom",
				yesBtn : "ANZEIGEN",
				noBtn : "noch warten"
			});
		<%}%>
		</script>
		<script type="text/javascript">
		<%for (int i = 0; i < evaluationen.length; i++) {%>
		$("#sharee<%out.print(i);%>").popConfirm({
				title : "Evaluation wirklich teilen und einen Magic Key erzeugen?",
				content : "Wird die Evaluation geteilt, so können die einzelnen Fragen nicht mehr verändert werden!",
				placement : "bottom",
				yesBtn : "Ja, teilen",
				noBtn : "Nein, nicht teilen"
			});
		<%}%>
		</script>
		<%
			for (int i = 0; i < evaluationen.length; i++) {
		%>
		<div id="code<%out.print(i);%>" style="display: none;">
			<svg xmlns="http://www.w3.org/2000/svg"
				xmlns:xlink="http://www.w3.org/1999/xlink" width="400px"
				height="400px"> <g id="qrcode<%out.print(i);%>" /> </svg>
			<script type="text/javascript">
				var qrcode = new QRCode(document.getElementById("qrcode<%out.print(i);%>"), {
					width : 400,
					height : 400,
					useSVG : true
				});
				function makeCode() {
					qrcode.makeCode("<%out.print(Datenbank.getSystemURL());%>/masche/Runeval?id=<%out.print(evDatenbank.getDurchfuehrungID(evaluationen[i]));%>");
				}
				makeCode();
			</script>
		</div>
		<%
			}
		%>
	</div>
	<%
		}
	%>
</body>
</html>
