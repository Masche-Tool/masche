<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<title>Dozentenansicht - Evaluation bearbeiten</title>
<jsp:include page="misc/html-head.jsp" />
</head>
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
	String evalToEdit = request.getParameter("id");
	if (sessionType == 1 || sessionType == 2) {
		evDatenbank.updateEvalTimestamp(evalToEdit);
%>
<body>
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
				<h1>Evaluation bearbeiten</h1>
				<form action="../EditEvaluationMetadata" method="post">
					<table class="table">
						<tbody>
							<tr>
								<td>Evaluationsname</td>
								<td><input type="hidden" name="evaluationID"
									value="<%out.print(evalToEdit);%>"><input type="text"
									id="inputModulname" class="form-control"
									placeholder="Evaluationsname" name="modulname"
									value="<%out.print(evDatenbank.getEvalModName(evalToEdit));%>"
									required></td>
							</tr>
							<tr>
								<td>Zeitraum</td>
								<td><input type="text" id="inputSemester"
									class="form-control" placeholder="Zeitraum" name="semester"
									value="<%out.print(evDatenbank.getSemesterOfModul(evalToEdit));%>"
									required></td>
							</tr>
							<tr>
								<td>Beschreibung</td>
								<td><textarea id="inputShortDescription"
										class="form-control"
										placeholder="Eine kurze Beschreibung des Moduls (maximal 1024 Zeichen)"
										name="beschreibung" rows="3" required><%out.print(evDatenbank.getBeschreibungOfModul(evalToEdit));%></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<p class="text-right">
						<button class="btn btn-primary" type="submit">Speichern</button>
					</p>
				</form>
			</div>
			<%
				if (!evDatenbank.hasMagicKey(evalToEdit) && !dvDatenbank.istAbgeleitet(evalToEdit)) {
			%>
			<div class="col-sm-12">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Nr.</th>
							<th>Frage</th>
							<th>Optionen</th>
							<th></th>
							<th></th>
							<th></th>

						</tr>
					</thead>
					<tbody>
						<%
							String fragenID[] = evDatenbank.getAllQuestionsIDsOfEvaluation(evalToEdit);
									for (int i = 0; i < fragenID.length; i++) {
										out.println("<tr><td>" + (i + 1) + "</td><td>" + Helper.xssCleaner(evDatenbank.getQuestionOfID(fragenID[i])) 
												+ "</td><td>" + (Integer.parseInt(evDatenbank.getQuestiontypeOfID(fragenID[i])) + 1)
												+ "</td><td width=\"100px\"><a href=\"../QuestionUp?evaluationID=" + evalToEdit
												+ "&questionID=" + fragenID[i]
												+ "\" class=\"btn btn-primary\" role=\"button\"\"><i class=\"glyphicon glyphicon-chevron-up\"></i></a><a href=\"../QuestionDown?evaluationID="
												+ evalToEdit + "&questionID=" + fragenID[i]
												+ "\" class=\"btn btn-primary\" role=\"button\"\"><i class=\"glyphicon glyphicon-chevron-down\"></i></a></td><td><a href=\"editQuestion.jsp?evaluationID="
												+ evalToEdit + "&questionID=" + fragenID[i]
												+ "\" class=\"btn btn-primary\" role=\"button\"\"><i class=\"glyphicon glyphicon-pencil\"></i></a></td><td><a href=\"../DeleteQuestion?questionid="
												+ fragenID[i] + "&evaluationID=" + evalToEdit
												+ "\" class=\"btn btn-danger\" role=\"button\" id=\"delete" + i
												+ "\"><i class=\"glyphicon glyphicon-trash\"></i></a></td></tr>");
									}
						%>
					</tbody>
				</table>
			</div>

			<div class="col-sm-12">
				<a href="newQuestion.jsp?id=<%out.print(evalToEdit);%>"
					class="btn btn-primary" role="button" style="width: 100%;">Frage
					hinzufügen</a>
			</div>
			<%
				} else {
			%>	
			<div class="col-sm-12">
				<center>
					<a href="ShareEvaluation.jsp?id=<%out.print(evalToEdit);%>"
						class="btn btn-danger" role="button"><i
						class="glyphicon glyphicon-warning-sign"></i> Da diese Evaluation
						geteilt, kopiert oder mit einem Magic Key<br>erstellt wurde,
						können die Fragen nicht mehr bearbeitet werden.</a>
				</center>
			</div>
			<div class="col-sm-12">
				<h3>Fragenvorschau</h3>
				<ol>
					<%
						String fragenID[] = evDatenbank.getAllQuestionsIDsOfEvaluation(evalToEdit);
								for (int i = 0; i < fragenID.length; i++) {
									out.println("<li>" + evDatenbank.getQuestionOfID(fragenID[i]) + "</li>");
								}
					%>
				</ol>
			</div>
			<%
				}
			%>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>
