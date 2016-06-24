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
				<h1>Evaluation <i><%out.print(evDatenbank.getEvalModName(evalToEdit));%></i> <%out.print(evDatenbank.getSemesterOfModul(evalToEdit));%></h1>
				<h2>Frage hinzufügen</h2>
				<form action="../AddQuestion" method="post">
					<p>
						<select name="fragetyp" id="fragetyp" class="form-control"
							autofocus required>
							<option></option>
							<option value="0">Freitext-Frage</option>
							<option value="1">Ja/Nein-Frage (2 Optionen)</option>
							<option value="3"> Stimme zu. Stimme eher zu. Stimme eher nicht zu. Stimme nicht zu. (4 Optionen)</option>
							<option value="5">Stimme ich voll und ganz zu. Stimme ich voll zu. Stimme ich... (6 Optionen)</option>
						</select>
					</p>
					<p>
						<input type="hidden" name="evaluationID"
							value="<%out.print(evalToEdit);%>">
						<textarea id="frage" class="form-control" placeholder="Frage"
							name="frage" rows="3" required></textarea>
					</p>
					<p class="text-right">
						<button class="btn btn-primary" type="submit">Hinzufügen</button>
					</p>
				</form>
			</div>
		</div>
		<jsp:include page="misc/footer.jsp" />
	</div>
	<%
		}
	%>
</body>
</html>
